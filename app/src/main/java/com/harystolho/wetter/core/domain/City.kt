package com.harystolho.wetter.core.domain

data class City(
    val id: Int,
    val name: String,
    val state: String
) {

    fun getDisplayText() = name.plus(" - ").plus(state)

}