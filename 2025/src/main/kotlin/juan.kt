package com.indigo

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    val initialDialPosition = 50
    val initialPassword = 0
    val strings = Path("src/main/resources/juan/test").readLines()
    val rotations = strings
        .map(::Rotation)

    rotations
        .fold(Pair(initialDialPosition, initialPassword),::rotate)
        .let(::println)

}

data class Rotation(val direction: Char, val distance: Int) {
    constructor(
        rotation: String
    ) : this (
        direction = rotation[0],
        distance = rotation.drop(1).toInt()
    )
}

typealias DialPosition = Int
typealias Password = Int

fun rotate(dialPositionAndPassword : Pair<DialPosition, Password>, rotation: Rotation) : Pair<DialPosition, Int> {
    val (dialPosition, password) = dialPositionAndPassword
    val newDialPosition = rotate(dialPosition, rotation)
    return newDialPosition to if (newDialPosition == 0) {
        password + 1
    }  else password
}

fun rotate(dialPosition : DialPosition, rotation: Rotation) : DialPosition =
    (dialPosition + when (rotation.direction) {
        'R' -> rotation.distance
        else -> -rotation.distance
    }).mod(100)