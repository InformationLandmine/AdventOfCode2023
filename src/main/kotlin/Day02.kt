import java.io.File

fun main(args: Array<String>) {
    println("2023 Advent of Code day 2")

    val gamesInput = File("day02input").readLines()

    val games = ArrayList<CubeGame>()
    for (game in gamesInput) {
        val gameNum = game.split(":").first().split(" ").last().toInt()
        val roundsInput = game.split(":").last().trim().split(";")
        val rounds = ArrayList<CubeGameRound>()
        for (round in roundsInput) {
            val cubes = round.trim().split(",")
            var red = 0
            var green = 0
            var blue = 0
            for (cube in cubes) {
                when (cube.trim().split(" ").last()) {
                    "red" -> red = cube.trim().split(" ").first().toInt()
                    "green" -> green = cube.trim().split(" ").first().toInt()
                    "blue" -> blue = cube.trim().split(" ").first().toInt()
                }
            }
            rounds.add(CubeGameRound(redCubes = red, greenCubes = green, blueCubes = blue))
        }
        games.add(CubeGame(number = gameNum, rounds = rounds.toList()))
    }

    val part1 = games.filter { game ->
        game.rounds.maxOf { it.redCubes } <= 12 &&
        game.rounds.maxOf { it.greenCubes } <= 13 &&
        game.rounds.maxOf { it.blueCubes } <= 14
    }.sumOf { it.number }
    println("Part 1: The sum of valid game numbers is $part1")

    val part2 = games.sumOf { game ->
        game.rounds.maxOf { it.redCubes } * game.rounds.maxOf { it.greenCubes } * game.rounds.maxOf { it.blueCubes }
    }
    println("Part 2: The sum of the game powers is $part2")
}

data class CubeGameRound (val redCubes: Int, val greenCubes: Int, val blueCubes: Int)
class CubeGame (val number: Int, val rounds: List<CubeGameRound>)