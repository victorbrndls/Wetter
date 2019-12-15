package com.harystolho.wetter.core.domain

import com.harystolho.wetter.util.StringUtils

data class City(
    val id: Int,
    val name: String,
    val state: String
) {

    /**
     * If you need to filter cities use [searchName] instead of [name] because it returns a string
     * that matches closely to how humans filter things
     */
    val searchName by lazy { StringUtils.removeAccents(name) }

    fun getDisplayText() = name.plus(" - ").plus(state)

}