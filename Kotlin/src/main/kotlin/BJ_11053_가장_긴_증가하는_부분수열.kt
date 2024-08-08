package main.kotlin


import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val ary = IntArray(n){input()}
    val list = IntArray(n)

    for(i in 0 until n){
        var temp = 0
        for(j in 0 until i){
            if(ary[i] > ary[j] && temp < list[j]){
                temp = list[j]
            }

        }
        list[i] = temp + 1
    }
    println(list.max())
}

/**
 * dp 문제
 * 1. 자신포함 - 자신값 저장
 * 2. 자신포함x - 이전값 2가지 중 작은 값
 */
