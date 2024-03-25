package stringProcessing
// 정규식으로 풀어보고 싶어서 이렇게 풀었지만 비효율적이다
// 그냥 문자하나씩 비교하는게 나을듯
fun main() = with(System.`in`.bufferedReader()){
    val input = Regex("(\\d+|[\\-+])").findAll(readLine()).map { it.value }.toList()

    var add = true
    var sum = 0
    input.forEach {
        when(it){
            "-" -> if(add) add = false
            "+" -> {}
            else -> if(add) sum += it.toInt() else sum -= it.toInt()
        }
    }
    println(sum)
}