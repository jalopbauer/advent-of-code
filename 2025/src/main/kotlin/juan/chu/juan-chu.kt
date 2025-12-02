package com.indigo.juan.chu

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    val initialDialPosition = 50
    val initialPassword = 0
    val strings = Path("src/main/resources/juan/juan").readLines()
    val rotations = strings
        .map(::Rotation)

    rotations
        .fold(Pair(initialDialPosition, initialPassword),::rotate)
        .let(::println)

}

data class Rotation(val direction: Char, val distance: Int) {
    constructor (
        rotation: String
    ) : this (
        direction = rotation[0],
        distance = rotation.drop(1).toInt()
    )
}

typealias DialPosition = Int
typealias Password = Int

fun rotate(dialPositionAndPassword : Pair<DialPosition, Password>, rotation: Rotation) : Pair<DialPosition, Int> =
    (1 .. rotation.distance).fold(dialPositionAndPassword) { currentDialPositionAndPassword, _ ->
        val (currentDialPosition, currentPassword) = currentDialPositionAndPassword
        val nextDialPosition = when (rotation.direction) {
            'R' -> currentDialPosition - 1
            else -> currentDialPosition + 1
        }.let {
            when(it){
                -1 -> 99
                100 -> 0
                else -> it
            }
        }
        val nextPassword = when (nextDialPosition) {
            0 -> currentPassword + 1
            else -> currentPassword
        }
        nextDialPosition to nextPassword
    }