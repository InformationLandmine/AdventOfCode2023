import java.io.File

fun main(args: Array<String>) {
    println("2023 Advent of Code day 1")

    val calibrations = File("day01input").readLines()

    // Part 1 - Get the calibration values and sum them.
    val part1 = calibrations.map { calibration ->
        calibration.fold(arrayListOf<Int>()) { list, next ->
            list.apply { if (next.isDigit()) this += next.digitToInt() }
        }
    }.sumOf { it.first() * 10 + it.last() }

    println("The sum of the calibration values is $part1")

    // Part 2 - The calibration values can be spelled out.
    val part2 = calibrations.map { calibration ->
        stringToDigits(calibration)
    }.sumOf { it.first() * 10 + it.last() }

    println("The new sum of the calibration values is $part2")

}

fun stringToDigits(s: String): List<Int> {
    val numbers = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val result = ArrayList<Int>()
    for (i in 0..s.length - 1) {
        if (s[i].isDigit()) {
            result.add(s[i].digitToInt())
        } else {
            for ((j, num) in numbers.withIndex()) {
                if (s.substring(i).startsWith(num)) {
                    result.add(j + 1)
                    break
                }
            }
        }
    }
    return result.toList()
}
