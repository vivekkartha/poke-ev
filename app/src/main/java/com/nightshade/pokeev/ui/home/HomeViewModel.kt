package com.nightshade.pokeev.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nightshade.pokeev.data.models.PokemonEffortVO
import com.nightshade.pokeev.data.providers.DataProvider

class HomeViewModel(private val dataProvider: DataProvider) : ViewModel() {

    private val _effortValues = MutableLiveData<List<PokemonEffortVO>>()

    val effortValues: LiveData<List<PokemonEffortVO>> = _effortValues

     fun getEvData() {
        dataProvider.getEffortsVOList { pokeListVO ->
            _effortValues.value = pokeListVO
        }
    }
}