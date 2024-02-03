package topologicalSort


class Solution {

    // DFS 풀이
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val graph: MutableMap<Int, MutableList<Int>> = mutableMapOf()
        val visited = mutableSetOf<Int>()

        prerequisites.forEach{
            if(!graph.containsKey(it[0])) graph[it[0]] = mutableListOf()
            graph[it[0]]!!.add(it[1])
        }

        fun dfs(i : Int) : Boolean{
            if(visited.contains(i)) return false
            if(graph[i].isNullOrEmpty()) return true
            visited.add(i)
            graph[i]!!.forEach{
                if (!dfs(it)) return false
            }
            visited.remove(i)
            graph[i]!!.clear()
            return true
        }

        prerequisites.forEach{
            if(!dfs(it[0])) return false
            visited.clear()
        }

        return true
    }
    // BFS 풀이
//    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
//        val psize = prerequisites.size
//        if(psize == 0) return true
//
//        val adjList = mutableListOf(mutableListOf<Int>())
//        for (i in 0 until numCourses) {
//            adjList.add(mutableListOf())
//        }
//
//        prerequisites.forEach{
//            adjList[it[0]].add(it[1])
//        }
//
//        val indegree = IntArray(numCourses)
//        for(i in 0 until numCourses){
//            adjList[i].forEach{
//                indegree[it]++
//            }
//        }
//
//        var queue = LinkedList<Int>()
//        indegree.forEachIndexed{ idx , it ->
//            if(it == 0) queue.offer(idx)
//        }
//
//        var course = 0
//        while(!queue.isEmpty()){
//            val k = queue.poll()
//            course++
//            adjList[k].forEach{
//                indegree[it]--
//                if(indegree[it] == 0)
//                    queue.offer(it)
//            }
//        }
//
//        return course == numCourses
//    }
}