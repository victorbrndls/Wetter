package com.harystolho.wetter.util

interface Mapper<T, R> {

    fun map(obj: T): R

}