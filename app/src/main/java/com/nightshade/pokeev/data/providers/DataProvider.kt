package com.nightshade.pokeev.data.providers

import android.content.res.Resources
import androidx.annotation.RawRes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nightshade.pokeev.R
import com.nightshade.pokeev.data.models.Pokemon
import com.nightshade.pokeev.data.models.PokemonEffortVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataProvider(private val resources: Resources) {

    fun getEffortsVOList(onComplete: (pokemonList: List<PokemonEffortVO>) -> Unit) {
        getEffortsList { pokemonList ->
            val pokeListVO = pokemonList.map { pokemon ->
                val effortStat = pokemon.getEffortStat()
                PokemonEffortVO(
                    pokemon.identifier,
                    pokemon.getEffortStat().type,
                    pokemon.getEffortValue(effortStat).toInt()
                )
            }
            onComplete(pokeListVO)
        }
    }

    private fun getEffortsList(onComplete: (pokemonList: List<Pokemon>) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            val pokemonList: List<Pokemon>
            withContext(Dispatchers.IO) {
                pokemonList = readRawJson(R.raw.pokemonv2)
            }
            onComplete(pokemonList)
        }
    }

    private inline fun <reified T> readRawJson(@RawRes rawResId: Int): T {
        resources.openRawResource(rawResId).bufferedReader().use {
            return Gson().fromJson(it, object : TypeToken<T>() {}.type)
        }
    }

}