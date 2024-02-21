package graphSearch

import java.util.LinkedList
import java.util.StringTokenizer

fun main() = with(System.`in`.bufferedReader()){
    val (n,m) = readLine().split(" ").map { it.toInt() }
    val ladders = mutableMapOf<Int,Int>()
    val snakes = mutableMapOf<Int,Int>()
    val visited = IntArray(101){0}
    repeat(n){
        val st = StringTokenizer(readLine())
        ladders[st.nextToken().toInt()] = st.nextToken().toInt()
    }
    repeat(m){
        val st = StringTokenizer(readLine())
        snakes[st.nextToken().toInt()] = st.nextToken().toInt()
    }

    val queue = LinkedList<Pair<Int,Int>>()
    queue.add(1 to 0)
    outer@ while (queue.isNotEmpty()){
        val curr = queue.poll()
        for( i in 1..6){
            var next = curr.first + i
            if(ladders.containsKey(next)) next = ladders[next]!!
            else if(snakes.containsKey(next)) next = snakes[next]!!
            if(next == 100){
                println(curr.second +1)
                break@outer
            }
            if(visited[next] == 1) continue
            visited[next] = 1
            queue.add(next to curr.second +1)
        }
    }
}