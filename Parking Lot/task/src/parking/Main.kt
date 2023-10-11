package parking

var size = 0
var parking = MutableList(size) { "free" }
var emptyParking = true

fun main() {
    while (true) {
        val input = readln().split(" ")
        when (input.first()) {
            "create" -> createParking(input)
            "park" -> park(input)
            "status" -> status()
            "leave" -> leave(input)
            "reg_by_color" -> regByColor(input)
            "spot_by_color" -> spotByColor(input)
            "spot_by_reg" -> spotByReg(input)
            "exit" -> break
        }
    }
}

fun regByColor(input: List<String>) {
    if (size == 0) printNoParking()
    else {
        val list = mutableListOf<String>()
        var count = 0
        for (i in parking) {
            val car = i.split(" ")
            if (car.last().lowercase() == input.last().lowercase()) { list.add(car.first()); count++ }
        }
        print(list.joinToString(", "))
        if (count == 0) println("No cars with color ${input.last()} were found.") else println()
    }
}

fun spotByColor(input: List<String>) {
    if (size == 0) printNoParking()
    else {
        val list = mutableListOf<Int>()
        var count = 0
        for (i in parking) {
            val car = i.split(" ")
            if (car.last().lowercase() == input.last().lowercase()) { list.add(parking.indexOf(i) + 1); count++ }
        }
        print(list.joinToString(", "))
        if (count == 0) println("No cars with color ${input.last()} were found.") else println()
    }
}

fun spotByReg(input: List<String>) {
    if (size == 0) printNoParking()
    else {
        var count = 0
        val list = mutableListOf<Int>()
        for (i in parking) {
            val car = i.split(" ")
            if (car.first() == input.last()) { list.add(parking.indexOf(i) + 1); count++ }
        }
        print(list.joinToString(", "))
        if (count == 0) println("No cars with registration number ${input.last()} were found.") else println()
    }
}

fun createParking(input: List<String>) {
    emptyParking = true
    size = input.last().toInt()
    parking = MutableList(size) { "free" }
    println("Created a parking lot with $size spots.")
}

fun park(input: List<String>) {
    if (size == 0) printNoParking()
    else if (!parking.contains("free")) println("Sorry, the parking lot is full.")
    else {
        for (i in parking.indices) {
            if (parking[i] == "free") { parking[i] = input[1] + " " + input[2]
                println("${input.last()} car parked in spot ${i + 1}."); emptyParking = false; break }
        }
    }
}

fun status() {
    if (size == 0) printNoParking()
    else if (emptyParking) println("Parking lot is empty.")
    else {
        for (i in parking.indices) { if (parking[i] != "free") println("${i + 1} ${parking[i]}") }
    }
}

fun leave(input: List<String>) {
    if (size == 0) printNoParking()
    for (i in parking.indices) {
        if (i + 1 == input.last().toInt()) { parking[i] = "free"; println("Spot ${i + 1} is free.") }
    }
}

fun printNoParking() = println("Sorry, a parking lot has not been created.")