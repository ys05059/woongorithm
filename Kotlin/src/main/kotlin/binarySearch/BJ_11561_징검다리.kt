package binarySearch
/*
3/16일 2:47 시작

n이 10^16이다 너무 큼 -> log n으로 무조건 처리해야할듯?
이진탐색을 넣어야함

비교하는 함수는 어떻게?
x 까지의 합이 n보다 작은데 가장 큰 값이면 된다

3/16 3:47컷
추가적인 생각
최대를 10^16을 넣을 필요가 없다. n이 몇이 최대일까
 */
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.pow
import kotlin.math.sqrt

fun main ()  = with(BufferedReader(InputStreamReader(System.`in`))){

    val k = readLine().toInt()
    repeat(k){
        val n = readLine().toLong()
        var left = 0L
        var right = 2 * sqrt(10.0.pow(16.0).toDouble()).toLong()
        while(left <= right){
            val mid = (left +right) / 2
            if(isSmall(n, mid)) left = mid +1
            else right = mid -1
        }
        println(right)
    }

}

fun isSmall (n : Long, k : Long) : Boolean{
    return (k+1) * k/2 <= n
}

//        println("mid : $mid")
//        println("left : $left , right : $right")
