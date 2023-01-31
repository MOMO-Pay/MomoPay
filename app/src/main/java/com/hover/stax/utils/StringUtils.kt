/*
 * Copyright 2022 Stax
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hover.stax.utils

import android.text.TextUtils
import java.util.*
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.round

fun String.splitCamelCase(): String {
    return StringUtils.splitCamelCase(this)
}

fun String.toHni(): String {
    return this.replace("[", "").replace("]", "").replace("\"", "")
}

fun String.toFilteringStandard(): String {
    return this.lowercase().replace(" ", "")
}

fun String.isAbsolutelyEmpty(): Boolean {
    return TextUtils.isEmpty(this.replace(" ", ""))
}

private object StringUtils {
    fun splitCamelCase(s: String): String {
        val camelCased: String = s.replace(
            String.format(
                "%s|%s|%s",
                "(?<=[A-Z])(?=[A-Z][a-z])",
                "(?<=[^A-Z])(?=[A-Z])",
                "(?<=[A-Za-z])(?=[^A-Za-z])"
            ).toRegex(),
            " "
        )
        return capitalize(camelCased)
    }

    private fun capitalize(str: String): String {
        return if (str.isEmpty()) { str } else str.substring(0, 1).uppercase(Locale.ROOT) + str.substring(1).lowercase(Locale.ROOT)
    }
}

fun Double.toString(precision: Int): String {
    val leftShifted = (round(abs(this) * 10.0.pow(precision))).toInt()
    val s = StringBuilder(leftShifted.toString())

    // left-pad with 0's to ensure enough digits
    (1..(precision + 1 - s.length)).forEach { _ ->
        s.insert(0, "0")
    }

    // insert decimal point
    if (precision != 0)
        s.insert(s.lastIndex - (precision - 1), ".")

    // (re)insert negative sign
    if (this < 0) {
        s.insert(0, "-")
    }

    return s.toString()
}

val Double.negativeSignOrEmpty: String
    get() = if (this < 0.0) "-" else ""