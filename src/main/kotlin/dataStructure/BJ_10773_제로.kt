package dataStructure

import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    val list = LinkedList<Int>()
    repeat(n){
        val input = readLine().toInt()
        if(input == 0) list.removeLast()
        else list.add(input)
    }
    println(list.sum())
}