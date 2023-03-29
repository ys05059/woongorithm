/*
* 합이 s다
* 부분수열 다 구해서 더해본다
* 2의 20승 -> 얼마안됨 그냥 브루트포스 가능
* */

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() =with(BufferedReader(InputStreamReader(System.`in`))){
    val (n,s) = readLine().split(" ").map{ it.toInt() }
    val stk = StringTokenizer(readLine())
    val numbers = IntArray(n){0}
    repeat(n){
        numbers[it] = stk.nextToken().toInt()
    }

    var answer = 0
    for (i in 0 until (1 shl numbers.size)) {
        var count = 0
        var tempSum = 0
        for (j in numbers.indices) {
            if ((i and (1 shl j)) != 0) {
                count++
                tempSum += numbers[j]
            }
        }
        if(count >=1 && tempSum == s) answer++
    }
    println(answer)
}