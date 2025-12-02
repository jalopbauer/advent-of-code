package com.indigo.chu.chu

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
        .filter { (string, _) ->
            (1..string.length / 2)
                .map { splitPoint ->
                    string.chunked(splitPoint)
                }
                .map { it.first() to it }
                .any { (first: String, second: List<String>) ->
                    second.all { it == first }
                }
        }
        .map { it.second }
        .sum()
        .let(::println)
}