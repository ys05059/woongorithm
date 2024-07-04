package temp

// 정렬해서 양 끝값
class Solution {
    fun solution(s: String): String {
        val list = s.split(" ").map{it.toInt()}.sorted()
        return "${list.first()} ${list.last()}"
    }
}