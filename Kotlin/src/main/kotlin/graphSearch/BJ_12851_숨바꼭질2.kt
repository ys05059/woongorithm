package graphSearch


import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    if(n>=k){
        println(n-k)
        println(1)
        return
    }
    data class Node(val i: Int, val w: Int)

    val queue = LinkedList<Node>()
    val visited = BooleanArray(100_001)
    queue.add(Node(n,0))
    visited[n] = true

    var ans = -1
    var cnt = 1

    while (queue.isNotEmpty()) {
        val (curr,dist) = queue.poll()
        if(ans in 0 until dist) break
        visited[curr] = true
        if(curr == k && ans != -1 && ans == dist) cnt++
        if (curr == k && ans == -1) {
            ans = dist
        }

        for (next in arrayOf(curr +1, curr -1, 2 * curr)){
            if(next in 0..100_000 && !visited[next]) {
                queue.add(Node(next, dist + 1))
            }
        }
    }
    println(ans)
    println(cnt)
}

