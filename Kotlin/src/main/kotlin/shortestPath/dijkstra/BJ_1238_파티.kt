package shortestPath.dijkstra

/**
 * BFS 돌기
 * x까지 이동 + 돌아오기
 */
import java.io.StreamTokenizer
import java.util.PriorityQueue

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val n = input()
    val m = input()
    val x = input()
    data class Node(val index: Int, val dist: Int)
    val adjList = Array(n+1){ arrayListOf<Node>() }
    val adjListReverse = Array(n+1){ arrayListOf<Node>() }
    repeat(m) {
        val a = input()
        val b = input()
        val c = input()
        adjList[a].add(Node(b,c))
        adjListReverse[b].add(Node(a,c))
    }

    fun dijkstra(start: Int, map : Array<ArrayList<Node>>): IntArray {
        val queue = PriorityQueue<Node>(compareBy{it.dist})
        val visited = BooleanArray(n+1){false}
        val dist = IntArray(n+1){Int.MAX_VALUE}

        dist[start] = 0
        queue.add(Node(start,0))

        while (queue.isNotEmpty()){
            val curr = queue.poll().index

            if(visited[curr]) continue
            visited[curr] = true

            for(next in map[curr]){
                if(!visited[next.index]){
                    val tempDist = dist[curr] + next.dist
                    if(tempDist < dist[next.index]){
                        dist[next.index] = tempDist
                        queue.add(Node(next.index,tempDist))
                    }
                }
            }
        }
        return dist
    }
    val minDist = dijkstra(x, adjList)
    val minDistReverse = dijkstra(x, adjListReverse)

    var ans = 0
    for(i in 1..n){
        ans = maxOf(ans, minDist[i] + minDistReverse[i])
    }
    println(ans)
}
