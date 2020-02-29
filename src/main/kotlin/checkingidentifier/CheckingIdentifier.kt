package checkingidentifier

fun isValidIdentifier(s: String): Boolean {
    if (s.isEmpty()) {
        return false;
    }
    val firstChar = s[0];
    if (firstChar !in 'a'..'z' && firstChar !in 'A'..'Z' && firstChar != '_') {
        return false
    }
    if (s.length > 1) {
        for(i in 1 until s.length) {
            val iChar = s[i]
            if (iChar !in 'a'..'z' && iChar !in 'A'..'Z' && iChar !in '0'..'9') {
                return false
            }
        }
    }
    return true
}

fun main(args: Array<String>) {
    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false
}