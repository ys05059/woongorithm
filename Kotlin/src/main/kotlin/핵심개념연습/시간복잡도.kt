package 핵심개념연습

fun main() {
    val n = 50000
    var count = 0
    for(i in 0 until n ){
        for(j in i+1 until n){
            count++
//            for (k in j+1 until n){
//            }
        }

    }
    println(count)
    println("${count/1000000},${count%1000000/1000},${count%1000}")
}