package main.kotlin.twoPointer

/**
 * 시간복잡도 : 최악은 N^2이긴한데 거의 NlogN
 * 핵심은 1개를 선택하고 투포인터를 돌린다는 것
 * 처음에 양쪽 2개를 선택하고 중간에 하나를 추가로 찾을려고 했는데 엣지케이스가 너무 많아서 계속 틀림
 */

import java.io.StreamTokenizer
import kotlin.math.abs

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun input() : Long{
        nextToken()
        return nval.toLong()
    }
    val n = input().toInt()
    val ary = Array(n){input()}
    var result = 3_000_000_001L
    ary.sort()
    val ans = arrayOf(ary[0], ary[1], ary[2])
    for(k in 0 until n){
        var l = k+1
        var r = n-1
        while(l < r){
            val sum = ary[k]+ ary[l] + ary[r]
            if(abs(sum) < result){
                result = abs(sum)
                ans[0] = ary[k]
                ans[1] = ary[l]
                ans[2] = ary[r]
            }
            if(sum < 0) l++ else r--
        }
    }
    println("${ans[0]} ${ans[1]} ${ans[2]}")
}