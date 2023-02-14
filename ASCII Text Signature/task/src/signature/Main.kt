package signature

import java.io.File

fun main() {
    print("Enter name and surname: ")
    val readName = readln()
    print("Enter person's status: ")
    val readStatus = readln().lowercase()

    val fileName = "C:\\Users\\user\\Desktop\\roman.txt"
    val lines = File(fileName).readLines()

    val symbolsNumber = getSymbolNumbersOnFile(readName, lines)

    val exitStringsRoman = getRomanLines(lines, symbolsNumber)

    val exitStringsMedium = getMediumLines(readStatus)

    println(printFirstLine(exitStringsRoman[0], exitStringsMedium[0]))
    printRomanAndMediumLines(exitStringsRoman, exitStringsMedium)
    println(printFirstLine(exitStringsRoman[0], exitStringsMedium[0]))


}

fun returnSymbol(char: Char): Array<String> {
    return when(char) {
        'a' -> (arrayOf("____", "|__|", "|  |"))
        'b' -> (arrayOf("___ ", "|__]", "|__]"))
        'c' -> (arrayOf("____", "|   ", "|___"))
        'd' -> (arrayOf("___ ", "|  \\", "|__/"))
        'e' -> (arrayOf("____", "|___", "|___"))
        'f' -> (arrayOf("____", "|___", "|   "))
        'g' -> (arrayOf("____", "| __", "|__]"))
        'h' -> (arrayOf("_  _", "|__|", "|  |"))
        'i' -> (arrayOf("_", "|", "|"))
        'j' -> (arrayOf(" _", " |", "_|"))
        'k' -> (arrayOf("_  _", "|_/ ", "| \\_"))
        'l' -> (arrayOf("_   ", "|   ", "|___"))
        'm' -> (arrayOf("_  _", "|\\/|", "|  |"))
        'n' -> (arrayOf("_  _", "|\\ |", "| \\|"))
        'o' -> (arrayOf("____", "|  |", "|__|"))
        'p' -> (arrayOf("___ ", "|__]", "|   "))
        'q' -> (arrayOf("____", "|  |", "|_\\|"))
        'r' -> (arrayOf("____", "|__/", "|  \\"))
        's' -> (arrayOf("____", "[__ ", "___]"))
        't' -> (arrayOf("___", " | ", " | "))
        'u' -> (arrayOf("_  _", "|  |", "|__|"))
        'v' -> (arrayOf("_  _", "|  |", " \\/ "))
        'w' -> (arrayOf("_ _ _", "| | |", "|_|_|"))
        'x' -> (arrayOf("_  _", " \\/ ", "_/\\_"))
        'y' -> (arrayOf("_   _", " \\_/ ", "  |  "))
        'z' -> (arrayOf("___ ", "  / ", " /__"))
        ' ' -> (arrayOf("    ", "    ", "    "))
        else -> arrayOf("", "", "")
    }
}

fun printRomanAndMediumLines(nameMutList: MutableList<String>, statusMutList: MutableList<String>) {
    for (i in 0..9) {
        if (nameMutList[0].length >= statusMutList[0].length) {
            println ("88  ${nameMutList[i]}  88")
        } else {
            val space = (statusMutList[0].length + 4 - nameMutList[0].length) / 2
            if ((statusMutList[0].length + 4 - nameMutList[0].length) % 2 == 0) {
                println ("88" + " ".repeat(space) + nameMutList[i] + " ".repeat(space) + "88")
            } else
                println ("88" + " ".repeat(space) + nameMutList[i] + " ".repeat(space) + " 88")
        }
    }
    for (i in 0..2) {
        if (statusMutList[0].length >= nameMutList[0].length) {
            println ("88  ${statusMutList[i]}  88")
        } else {
            val space = (nameMutList[0].length + 4 - statusMutList[0].length) / 2
            if ((nameMutList[0].length + 4 - statusMutList[0].length) % 2 == 0) {
                println ("88" + " ".repeat(space) + statusMutList[i] + " ".repeat(space) + "88")
            } else
                println ("88" + " ".repeat(space) + statusMutList[i] + " ".repeat(space) + " 88")
        }
    }
}

fun printFirstLine(name: String, status: String):String{
    var finalString = ""
    if (name.length >= status.length)
        finalString += "8".repeat(name.length + 8)
        else finalString += "8".repeat(status.length + 8)
    return finalString
}

fun getSymbolNumbersOnFile(readNameF: String, linesF: List<String>):MutableList<Int> {
    val regex = Regex("[a-zA-Z] [0-9][0-9]?")
    val symbolNumberF = mutableListOf<Int>()

    for (symbol in readNameF) {
        if (symbol == ' ') {
            symbolNumberF += 999
        } else {
            for (line in 0..linesF.size - 1) {
                val currentLine = linesF[line]
                if (regex.matches(linesF[line]) && symbol == currentLine[0]) {
                    symbolNumberF += line
                    break
                }
            }
        }
    }
    return symbolNumberF
}

fun getRomanLines(linesF: List<String>, symbolNumberF: MutableList<Int>): MutableList<String> {
    val exitStrings = mutableListOf<String>("","","","","","","","","","")
    for (line in 0..9) {
        for (lineNumber in 0 until symbolNumberF.size) {
            val fileLineNumber = symbolNumberF[lineNumber]
            if (fileLineNumber == 999) {
                exitStrings[line] += "          "
            } else exitStrings[line] += (linesF[fileLineNumber+line+1])
        }
    }
    return exitStrings
}

fun getMediumLines(readNameF: String): MutableList<String> {
    val exitString = mutableListOf<String>("","","")
        for (line in 0..2) {
            for (i in readNameF) {
                val symbol = returnSymbol(i)
                exitString[line] += "${symbol[line]} "
            }
        }
    return exitString
}