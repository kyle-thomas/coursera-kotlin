package nicestring

fun String.isNice(): Boolean {
    var nice = 0
    val pairs = this.zipWithNext()
    if (!(pairs.contains('b' to 'u') || pairs.contains('b' to 'a') || pairs.contains('b' to 'e')))
        nice++
    if (this.filter { "aeiou".contains(it) }.length >= 3)
        nice++
    if (pairs.any { it.first == it.second })
        nice++
    return nice >= 2
}