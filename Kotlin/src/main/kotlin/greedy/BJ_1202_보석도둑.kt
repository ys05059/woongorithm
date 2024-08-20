package main.kotlin.greedy

/**
 * 정렬을 잘해야한다 nlogn까지만 가능
 * 보석 -> 무게는 낮은 것부터, 가치는 가장 높은 것부터 넣기
 * 가방 -> 크기가 작은 것부터
 * pq 따로 유지
 */

import java.io.StreamTokenizer
import java.util.PriorityQueue

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    data class Jewel (val m : Int, val v : Int) : Comparable <Jewel> {
        override fun compareTo(other: Jewel): Int {
            return if(m == other.m) other.v - v
            else m - other.m
        }
    }
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }
    val n = input()
    val k = input()
    var result = 0L
    val jewels = PriorityQueue<Jewel>(compareBy())
    repeat(n){jewels.add(Jewel(input(),input()))}
    val bags = Array(k){input()}
    val pq = PriorityQueue<Int>(Comparator.reverseOrder())

    bags.sort()
    for(i in 0 until k){                            // 가방 순회
        while(jewels.isNotEmpty() && jewels.peek().m <= bags[i]){
            pq.offer(jewels.poll().v)
        }
        if(pq.isNotEmpty()){
            result += pq.poll()
        }
    }
    println(result)
}