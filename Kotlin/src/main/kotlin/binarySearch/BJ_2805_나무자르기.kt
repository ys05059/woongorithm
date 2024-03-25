package binarySearch

/**
 * 기본 이진 탐색으로 최댓값 구하는 문제
 * 매개변수 탐색 들어가야제
 * 0~최대 높이
 * sum을 Long으로 처리하기, mid도 Int범위 넘지 않도록 주의
 */


import java.util.StringTokenizer
fun main() = with(System.`in`.bufferedReader()){
    val input = readLine().split(" ")
    val n = input[0].toInt()
    val m = input[1].toLong()

    val st = StringTokenizer(readLine())
    val ary = IntArray(n)
    var max = 0
    repeat(n) {
        ary[it]= st.nextToken().toInt()
        max = max.coerceAtLeast(ary[it])
    }
    fun search() : Int{
        var l = 0
        var r = max
        var sum = 0L
        while (l <= r){
            val mid = l + (r-l)/2
            sum = 0L
            for (i in ary.indices){
                if(ary[i] > mid) sum += ary[i] - mid
                if(sum > m) break
            }
            if(sum == m) return mid
            if(sum < m) r = mid - 1
            else l = mid + 1
        }
        return r
    }
    println(search())
}