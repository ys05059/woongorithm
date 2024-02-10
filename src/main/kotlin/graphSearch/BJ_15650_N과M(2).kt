package graphSearch
// 조합을 순서대로 출력
fun main() = with(System.`in`.bufferedReader()){
    val (n, m) = readLine().split(" ").map { it.toInt() }
    fun combination(curr : MutableList<Int>, start : Int){
        if(curr.size == m){
            println(curr.joinToString(" "))
            return
        }
        for(i in start until n){
            curr.add(i+1)
            combination(curr, i+1)
            curr.removeLast()
        }
    }
    combination(mutableListOf(),0)
}

