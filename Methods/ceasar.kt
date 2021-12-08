object Cypher {
    fun encrypt(str: String, key: Int): String {
        val offset = key % 26
        if (offset == 0) return str
        var shifted: Char
        val result = CharArray(str.length) 
        for ((index, character) in str.withIndex()) {
            if (character in 'A'..'Z') {
                shifted = character + offset
                if (shifted > 'Z') shifted -= 26
            }
            else if (character in 'a'..'z') {
                shifted = character + offset
                if (shifted > 'z') shifted -= 26
            }
            else
                shifted = character
            result[index] = shifted
        } 
        return result.joinToString("")
    }
 
    fun decrypt(str: String, key: Int): String {
        return encrypt(str, 26 - key)
    }
}
 
fun main(args: Array<String>) {
    val encoded = Cypher.encrypt("The quick brown fox jumped over the lazy dog." +
     "A wise man once said to eat my vegetables", 8)
    println(encoded)
    val decoded = Cypher.decrypt(encoded, 8)
    println(decoded)
}