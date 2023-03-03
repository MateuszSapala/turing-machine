package pl.lodz.uni.project1

import pl.lodz.uni.project1.turing.Turing

fun main() {
    println("Enter the number you want to check if it is a prime number:")
    val number = readln().toInt()
    Turing(number).checkIfNumberIsPrime()
    println("Your number is prime")
}