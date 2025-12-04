package com.indigo.free.juan

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {

    Path("src/main/resources/free/actual").readLines()
        .map { it.map { num -> num.digitToInt() } }
        .apply(::println)
        .map { list ->
            val highest = list.max().apply(::println)
            val indexOfHighest = list.indexOf(highest).apply(::println)
            when {
                indexOfHighest + 1 == list.size -> Pair( list.dropLast(1).max(), highest)
                else -> Pair(highest,list.subList(indexOfHighest+1, list.size).max())
            }
        }
        .apply(::println)
        .map { it.first * 10 + it.second }
        .apply(::println)
        .sum()
        .apply(::println)
// 98 + 89 + 78 + 92
}