package com.nightshade.pokeev.data.models

data class PokemonEffort(
    val name: String,
    val hp: Int?,
    val speed: Int?,
    val attack: Int?,
    val defense: Int?,
    val spAtk: Int?,
    val spDef: Int?
) {

    companion object {
        const val HP = "HP"
        const val SPEED = "Speed"
        const val ATTACK = "Atk"
        const val DEFENSE = "Def"
        const val SP_ATTACK = "Sp.Atk"
        const val SP_DEF = "Sp.Def"
    }

    fun getStat(): String {
        return when {
            hp != null -> HP
            speed != null -> SPEED
            attack != null -> ATTACK
            defense != null -> DEFENSE
            spAtk != null -> SP_ATTACK
            spDef != null -> SP_DEF
            else -> ""
        }
    }

    fun getYield(): Int {
        return when {
            hp != null -> hp
            speed != null -> speed
            attack != null -> attack
            defense != null -> defense
            spAtk != null -> spAtk
            spDef != null -> spDef
            else -> 0
        }
    }

}