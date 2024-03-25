package binarySearch

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val l = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    println(n)
    var (start, end) = arrayOf(l.max()-1, l.sum())          // 범위 설정에 주의하자
    while (start + 1 < end) {
        var mid :Int = (start + end) / 2
//        println("mid : $mid")
        val result = check(l, mid)
//        println("핵심개념연습.getResult : $핵심개념연습.getResult m : $m")
        if ( result > m) {                     // m개 이상의 그룹이 됨 => mid 값이 작다
            start = mid
        } else {
            end = mid
        }
    }
    bw.write(end.toString())
    bw.flush()
    bw.close()
}

// 배열 앞에서부터 순회하면서 해당 값 넘으면 끊는다. 그룹이 총 몇 개인지 반환
fun check(l : IntArray, value : Int) : Int{
    var groupCnt = 1
    var temp = 0
    l.forEach {
        if (temp + it > value) {
            groupCnt += 1
            temp = it
        }else{
            temp += it
        }
    }
    return groupCnt
}