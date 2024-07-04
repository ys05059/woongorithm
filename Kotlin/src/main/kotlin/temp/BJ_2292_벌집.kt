package temp
fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    if(n==1) {
        println(1)
        return
    }
    var result = 1
    var min = 2
    while(min <= n){
        min += (6*result)
        result++
    }
    println(result)
}