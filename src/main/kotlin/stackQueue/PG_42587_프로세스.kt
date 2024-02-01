package stackQueue

import java.util.LinkedList
/*
    핵심은 LinkedList를 queue로 사용, index 사용
 */
class Solution {
    fun solution(priorities: IntArray, location: Int): Int {
        val queue = LinkedList<Int>()
        for (i in priorities.indices){
            queue.add(i)
        }
        var answer = 1
        while(true){
            val temp = queue.poll()
            if(queue.any{ priorities[it] > priorities[temp]}) queue.add(temp)
            else if(temp == location) break
            else answer++
        }
        return answer
    }
}