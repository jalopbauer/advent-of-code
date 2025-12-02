package com.indigo.chu.juan

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    Path("src/main/resources/chu/chu").readLines()
        .first()
        .split(",")
        .asSequence()
        .map { it.split("-") }
        .map { (firstId, secondId) ->
            firstId.toLong()..secondId.toLong()
        }
        .flatten()
        .map { "$it" to it }
        .map { (string, number) ->
            val midpoint = string.length / 2
            Pair(string.substring(0, midpoint), string.substring(midpoint)) to number
        }
        .filter { (string, _) ->
            val (firstHalf, secondHalf) = string
            firstHalf == secondHalf
        }
        .map { it.second }
        .sum()
        .let(::println)
}