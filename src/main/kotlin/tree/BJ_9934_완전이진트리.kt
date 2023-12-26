package tree

import java.io.BufferedReader
import java.io.InputStreamReader


lateinit var tree : MutableList<MutableList<Int>>
fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    tree = MutableList(n){ mutableListOf() }
    val input = readLine().split(" ").map { it.toInt() }
    makeTree(input,0)
    tree.forEach{ levelList ->
        levelList.forEach {
            print("$it ")
        }
        println()
    }
}

private fun makeTree(list : List<Int>, level : Int){
    val mid = list.size /2
    tree[level].add(list[mid])
    if(list.size == 1) return
    makeTree(list.subList(0,mid), level+1)
    makeTree(list.subList(mid+1, list.size), level+1)
}