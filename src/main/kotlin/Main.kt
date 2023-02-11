fun main() {

    val randNum = createRandom4Digit()
    var usrIn = ""

    println(randNum)

    while (usrIn != randNum) {
        print("User input: ")
        usrIn = readln()
        val pos = mastermindEval(usrIn, randNum)
        println("Output: ${pos[0]}:${pos[1]}")
    }
    println("Congratulations, you did it!! The solution was: $randNum")
}

fun createRandom4Digit(): String {
    var finalNum = ""
    val nums: MutableList<Int> = (0..9).toMutableList()

    for (i in 1..4) {
        finalNum += nums.removeAt((0 until nums.size).random())
    }
    return finalNum
}

fun mastermindEval(usrIn: String, sol: String): Array<Int> {
    var correctNumbers = 0
    var correctPositions = 0
    val distincts = usrIn.toList().distinct()
    if (usrIn.length != sol.length) println("Invalid Input!")
    else {
        for (index in usrIn.indices) if (sol[index] == usrIn[index]) correctPositions++
        for (num in distincts) if (sol.contains(num)) correctNumbers++
    }
    return arrayOf(correctNumbers, correctPositions)
}