import java.io.File

fun main(args: Array<String>) {
    println("2023 Advent of Code day 3")

    val lines = File("day03input").readLines()

    val partNums = ArrayList<Triple<Int, Char, Pair<Int, Int>>>()

    for ((i, line) in lines.withIndex()) {
        var currentLineIndex = 0
        while (true) {
            val startIndex = line.drop(currentLineIndex).indexOfFirst { it.isDigit() } + currentLineIndex
            if (startIndex - currentLineIndex == -1) break
            val endIndex = line.drop(startIndex).indexOfFirst { !it.isDigit() } + startIndex
            val partNum = line.substring(startIndex, endIndex).toInt()
            val symbol = getSurroundingSymbol(lines, i, startIndex..endIndex)
            partNums.add(Triple(partNum, symbol.first, symbol.second))
            currentLineIndex = endIndex
        }
    }

    // Part 1
    val part1 = partNums.filter { it.second != '.' }.sumOf { it.first }
    println(part1)

    // Part 2
    val gears = partNums.filter { it.second == '*' }
    var part2 = 0
    for (gear in gears) {
        if (gears.count { it.third == gear.third } == 2) {
            part2 += gears.filter { it.third == gear.third }.fold(1) { acc, g ->
                g.first * acc
            }
        }
    }
    part2 /= 2
    println(part2)

}

fun getSurroundingSymbol(lines: List<String>, lineIndex: Int, lineRange: IntRange):Pair<Char, Pair<Int, Int>> {
    if (lines[lineIndex][lineRange.first-1] != '.' && !lines[lineIndex][lineRange.first-1].isDigit())
        return Pair(lines[lineIndex][lineRange.first-1], Pair(lineIndex, lineRange.first-1))
    if (lines[lineIndex][lineRange.last] != '.' && !lines[lineIndex][lineRange.last].isDigit())
        return Pair(lines[lineIndex][lineRange.last], Pair(lineIndex, lineRange.last))

    val above = lines[lineIndex-1].subSequence(lineRange.first-1, lineRange.last+1)
    if (above.any { !it.isDigit() && it != '.'} ) {
        val index = above.indexOfFirst { !it.isDigit() && it != '.' }
        return Pair(above[index], Pair(lineIndex - 1, index + lineRange.first-1) )
    }

    val below = lines[lineIndex+1].subSequence(lineRange.first-1, lineRange.last+1)
    if (below.any { !it.isDigit() && it != '.'} ) {
        val index = below.indexOfFirst { !it.isDigit() && it != '.' }
        return Pair(below[index], Pair(lineIndex + 1, index + lineRange.first-1))
    }

    return Pair('.', Pair(0, 0))
}
