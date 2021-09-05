package com.nightshade.pokeev.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nightshade.pokeev.R
import com.nightshade.pokeev.data.models.PokemonEffortVO
import com.nightshade.pokeev.data.models.Stat
import com.nightshade.pokeev.databinding.RowEvBinding

class EvRecyclerAdapter(
    private val context: Context,
    val pokemonEffortVOList: MutableList<PokemonEffortVO>
) :
    RecyclerView.Adapter<EvRecyclerAdapter.EVHolder>() {

    private lateinit var allPokemonEffortVO: List<PokemonEffortVO>

    inner class EVHolder(private val row: RowEvBinding) : RecyclerView.ViewHolder(row.root) {
        fun bind(pokemonEffortVO: PokemonEffortVO) {
            with(row) {
                tvName.text = pokemonEffortVO.name
                tvEffort.text = context.getString(
                    R.string.ev_value,
                    pokemonEffortVO.yield,
                    pokemonEffortVO.stat
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EVHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: RowEvBinding = RowEvBinding.inflate(inflater, parent, false)
        return EVHolder(view)
    }

    override fun onBindViewHolder(holder: EVHolder, position: Int) {
        holder.bind(pokemonEffortVOList[position])
    }

    override fun getItemCount(): Int = pokemonEffortVOList.size

    fun filter(stat: Stat) {
        if (!::allPokemonEffortVO.isInitialized) {
            allPokemonEffortVO = pokemonEffortVOList.toList()
        }
        pokemonEffortVOList.clear()
        val filtered = if (stat == Stat.ALL) {
            allPokemonEffortVO
        } else {
            allPokemonEffortVO.filter { it.stat == stat.type }.sortedBy { it.name }
        }
        pokemonEffortVOList.addAll(filtered)
        notifyDataSetChanged()
    }
}