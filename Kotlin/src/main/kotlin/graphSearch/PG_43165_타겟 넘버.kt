package graphSearch

// 각 자리는 할 수 있는 것이 +/- 밖에 없음
// 다음 자리로 넘어가면서 두 케이스 다 넣어줘야함 -> bfs
// 현재 값 넘겨줘야함

import java.util.LinkedList

class Solution {
    fun solution(numbers: IntArray, target: Int): Int {
        val queue = LinkedList<Int>()
        var depth = 0
        queue.add(0)

        // 각 depth별 2의 depth승 pop
        while(depth < numbers.size){
            repeat(Math.pow(2.0,depth.toDouble()).toInt()){
                val temp = queue.poll()
                queue.add(temp + numbers[depth])
                queue.add(temp - numbers[depth])
            }
            depth++
        }

        return queue.count{it == target}
    }
}