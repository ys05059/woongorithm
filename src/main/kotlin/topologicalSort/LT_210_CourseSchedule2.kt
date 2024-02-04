import java.util.LinkedList

// bfs로 풀면서 하나씩 배열에 넣자 그냥
fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
    val graph :MutableMap<Int,MutableList<Int>> = mutableMapOf()
    val indegree = IntArray(numCourses){0}
    val queue = LinkedList<Int>()
    val result = IntArray(numCourses)
    var cnt = 0

    for (i in 0 until numCourses){
        graph[i] = mutableListOf()
    }
    prerequisites.forEach{
        graph[it[1]]!!.add(it[0])
        indegree[it[0]]++
    }

    for (i in 0 until numCourses){
        if(indegree[i] == 0) queue.add(i)
    }

    while(queue.isNotEmpty()){
        val k = queue.removeFirst()
        result[cnt++] = k
        graph[k]!!.forEach{
            indegree[it]--
            if(indegree[it] == 0){
                queue.add(it)
            }
        }
    }
    return if(cnt == numCourses) result else intArrayOf()

}