package com.harystolho.wetter.util

import java.text.Normalizer

object StringUtils {

    private val accentsRegex = "[^\\p{ASCII}]".toRegex()

    fun removeAccents(str: String): String {
        val strNormalizer = Normalizer.normalize(str, Normalizer.Form.NFD)
        return strNormalizer.replace(accentsRegex, "")
    }

}