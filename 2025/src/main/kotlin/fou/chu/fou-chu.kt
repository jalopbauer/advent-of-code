package com.indigo.fou.chu

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {

    Path("src/main/resources/fou/actual")
        .readLines()
        .map { it.toList() }
        .let { table ->
            var previousTable = table
            var newTable = removeRolls(table)
            while (previousTable != newTable) {
                previousTable = newTable
                newTable = removeRolls(newTable)
            }
            newTable
        }
        .flatten()
        .count{ it == 'x'}
        .apply(::println)
}

private fun removeRolls(table: List<List<Char>>): List<List<Char>> = table.mapIndexed { rowIndex, rowRoll ->
    rowRoll.mapIndexed { columnIndex, roll ->
        if (roll == '@') {
            buildList {

                if (rowIndex != 0 && columnIndex != 0) add(Pair(rowIndex - 1, columnIndex - 1))
                if (rowIndex != 0) add(Pair(rowIndex - 1, columnIndex))
                if (rowIndex != 0 && columnIndex != rowRoll.size - 1) add(Pair(rowIndex - 1, columnIndex + 1))

                if (columnIndex != 0) add(Pair(rowIndex, columnIndex - 1))
                if (columnIndex != table.size - 1) add(Pair(rowIndex, columnIndex + 1))

                if (rowIndex != table.size - 1 && columnIndex != 0) add(Pair(rowIndex + 1, columnIndex - 1))
                if (rowIndex != table.size - 1) add(Pair(rowIndex + 1, columnIndex))
                if (rowIndex != table.size - 1 && columnIndex != rowRoll.size - 1) add(
                    Pair(
                        rowIndex + 1,
                        columnIndex + 1
                    )
                )

            }.count { index -> table[index.first][index.second] == '@' }
                .let { count ->
                    if (count < 4) 'x'
                    else '@'
                }
        } else {
            roll
        }
    }
}