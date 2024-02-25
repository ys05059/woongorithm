package twoPointer

/**
 * 투포인터 문제
 */

import java.io.StreamTokenizer
import kotlin.math.abs

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    val readInt = { nextToken() ; nval.toInt() }
    val n = readInt()
    val ary = IntArray(n)
    repeat(n){ ary[it] = readInt()}
    ary.sort()
    var l = 0
    var r = ary.lastIndex
    var min = Int.MAX_VALUE
    val ans = IntArray(2)

    while(l < r){
        val sum = ary[l] + ary[r]
        if (abs(sum) < min){
            min = abs(sum)
            ans[0] = ary[l]
            ans[1] = ary[r]
        }
        if(sum == 0) break
        if(sum < 0) l++
        else r--
    }
    println("${ans[0]} ${ans[1]}")
}