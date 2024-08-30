package main.kotlin.twoPointer

import java.io.StreamTokenizer
import kotlin.math.abs

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun input() : Long{
        nextToken()
        return nval.toLong()
    }
    val n = input().toInt()
    val ary = Array(n){input()}
    var l = 0
    var r = n-1
    var result = 2_000_000_001L
    var ans  = ary[0] to ary[n-1]
    while(l < r){
        val temp = ary[l] + ary[r]
        if(abs(temp) < result){
            result = abs(temp)
            ans = ary[l] to ary[r]
        }
        if(temp == 0L) break
        if(temp > 0L) r-- else l++
    }
    println("${ans.first} ${ans.second}")
}