package com.nightshade.pokeev.data.models

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val id: String?,
    val identifier: String,
    @SerializedName("species_id")
    val species_id: String?,
    val height: String?,
    val weight: String?,
    @SerializedName("base_experience")
    val baseExperience: String?,
    val order: Any?,
    @SerializedName("is_default")
    val isDefault: String?,
    val stats: Stats
) {
    companion object {
        const val HP = "HP"
        const val SPEED = "Speed"
        const val ATTACK = "Atk"
        const val DEFENSE = "Def"
        const val SP_ATTACK = "Sp.Atk"
        const val SP_DEF = "Sp.Def"
    }

    fun getEffortStat(): Stat {
        return when {
            stats.hp.last() != "0" -> Stat.HP
            stats.atk.last() != "0" -> Stat.ATTACK
            stats.def.last() != "0" -> Stat.DEFENSE
            stats.spAtk.last() != "0" -> Stat.SPATTACK
            stats.spDef.last() != "0" -> Stat.SPDEF
            stats.speed.last() != "0" -> Stat.SPEED
            else -> Stat.NONE
        }
    }

    fun getEffortValue(stat: Stat): String {
        return when (stat) {
            Stat.HP -> stats.hp.last()
            Stat.ATTACK -> stats.atk.last()
            Stat.DEFENSE -> stats.def.last()
            Stat.SPATTACK -> stats.spAtk.last()
            Stat.SPDEF -> stats.spDef.last()
            Stat.SPEED -> stats.speed.last()
            else -> "nil"
        }
    }
}

data class Stats(
    val hp: List<String>,
    val atk: List<String>,
    val def: List<String>,
    val spAtk: List<String>,
    val spDef: List<String>,
    val speed: List<String>
)