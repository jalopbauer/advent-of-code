package com.indigo.fai.juan

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {

    Path("src/main/resources/fai/actual")
        .readLines()
        .fold(Triple(emptyList<Pair<Long,Long>>(), emptyList<Long>(), false)) {
            (freshIngredientIds, availableIngredientIds, lineReached), string ->
            when {
                lineReached -> Triple(
                    freshIngredientIds,
                    availableIngredientIds + string.toLong(),
                    true)
                string.isEmpty() -> Triple(freshIngredientIds, availableIngredientIds, true)
                else -> {
                    val ids = string.split("-").let {
                        it.first().toLong() to it[1].toLong()
                    }
                    Triple(freshIngredientIds + ids, availableIngredientIds, false)
                }
            }
        }
        .apply(::println)
        .let { (freshIngredientIds, availableIngredientIds, _) ->
            availableIngredientIds.filter { availableIngredientId ->
                freshIngredientIds.any { (min, max) ->
                    availableIngredientId in min .. max
                }
            }
        }
        .apply(::println)
        .count()
        .apply(::println)
}