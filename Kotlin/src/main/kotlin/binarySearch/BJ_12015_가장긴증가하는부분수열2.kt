package main.kotlin.binarySearch

/**
 * 1과 다른 점 n이 1_000_000이므로 N^2해결이 안됨 -> NlogN으로 최적화
 */

import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val n = input()
    val ary = IntArray(n){input()}
    val dp = mutableListOf(ary[0])


    for(i in 1 until n){
        if(ary[i] > dp[dp.size-1]){           // 값 포함 가능
            dp.add(ary[i])
        }else{                              // 값 포함 불가능
            // 현재 값이 dp에 들어갈 수 있는 idx 찾기 (이진 탐색)
            val idx = binarySearchLeft(dp,ary[i])
            dp[idx] = ary[i]
        }
    }
    println(dp.size)
}

fun binarySearchLeft(arr : MutableList<Int>, target: Int) : Int{
    var left = 0
    var right = arr.size

    while(left < right){
        val mid = left + (right-left)/2
        if(arr[mid] < target){
            left = mid + 1
        }else right = mid
    }
    return left
}

// binarySearch 함수 사용한 풀이
// 코틀린의 binarySearch 함수는 반환값이 값을 찾지 못했을 때 삽입 가능위치가 -idx -1 임
//            val temp = dp.binarySearch { it.compareTo(ary[i]) }
//            val idx = if (temp >= 0) temp else -(temp+1)
