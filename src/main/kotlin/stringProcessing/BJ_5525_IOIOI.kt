package stringProcessing

/**
 * IO가 n개, 마지막이 I
 */

fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    val m = readLine().toInt()
    val str = readLine()
    var prev = ' '
    var count = 0
    var answer = 0

    fun cal(){ // 정산
        if(count >=n) answer += count-n +1
        count = 0
    }

    str.forEach {
        if(it =='I' && prev == 'I') cal()
        else if(it == 'O'){
            if(prev == 'I') count++
            if(prev == 'O') {
                count--
                cal()
            }
        }
        prev = it
    }
    if(count >=n) {
        if(prev == 'O') count--
        cal()
    }
    println(answer)
}