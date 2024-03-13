/**
 * 이거 dfs 같은 그래프 탐색 딱히 안해도 되겠는데
 *
 * 치킨집은 어떻게 고를건데 -> 조합이 먼저 떠오르긴한다.
 */

import java.io.StreamTokenizer
import kotlin.math.abs

private val home = mutableListOf<Pair<Int,Int>>()
private val chicken = mutableListOf<Pair<Int,Int>>()
private var ans = Int.MAX_VALUE
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }
    val n = input()
    val m = input()

    repeat(n){ i ->
        repeat(n){ j ->
            val temp = input()
            if(temp == 1) home.add(j to i)
            if(temp == 2) chicken.add(j to i)
        }
    }
    combination(chicken.size, m, mutableListOf(), 0)
    println(ans)
}

fun combination(n : Int, r : Int, result : MutableList<Pair<Int,Int>>, index : Int){
    if(result.size == r){
        ans = ans.coerceAtMost(calculateCityMin(result))
        return
    }
    for( i in index until n){
        result.add(chicken[i])
        combination(n, r, result,i + 1)
        result.removeLast()
    }
}

fun calculateCityMin(chicken : MutableList<Pair<Int,Int>>) : Int{
    var distance = 0
    home.forEach {
        distance += calculateMin(it.first,it.second,chicken)
    }
    return distance
}

fun calculateMin(x : Int, y : Int, chicken : MutableList<Pair<Int,Int>>) : Int{
    //  각 치킨집까지의 거리 계산해서 최소 구하기
    var min = Int.MAX_VALUE
    chicken.forEach {
        min = min.coerceAtMost(abs(it.first-x)+abs(it.second-y))
    }
    return min
}

