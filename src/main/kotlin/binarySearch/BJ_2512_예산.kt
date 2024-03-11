package binarySearch

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val list = IntArray(n) { input() }
    val m = input()
    var l = 1
    var r = list.max()
    var sum = 0
    var result = r
    while (l <= r) {
        val mid = l + (r - l) / 2
        sum = 0
        list.forEach {
            sum += it.coerceAtMost(mid)
        }
        if (sum > m) r = mid -1
        else {
            l = mid + 1
            result = mid
        }
    }
    println(result)
}
