package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    var rightPosition = 0
    var wrongPosition = 0
    val secretUsage = mutableMapOf<Char, Int>()
    secret.zip(guess).count { pair -> pair.first == pair.second }
    for (i in secret.indices) {
       secretUsage.put(secret[i], secretUsage.getOrDefault(secret[i], 0) + 1);
    }
    for (i in guess.indices) {
        if (guess[i] == secret[i]) {
            rightPosition++
            secretUsage.put(secret[i], secretUsage.getOrDefault(guess[i], 1) - 1)
        }
    }
    for (i in guess.indices) {
        if (guess[i] != secret[i]) {
            val charUsage = secretUsage.getOrDefault(guess[i], 0)
            if (charUsage > 0) {
                wrongPosition++
                secretUsage.put(guess[i], charUsage - 1)
            }
        }
    }
    return Evaluation(rightPosition, wrongPosition)
}
