package `dp(dynamic programming)`

// 여러개 들어가 있는 물건들 묶어주기
// 핵심 아이디어 -> 2진수로 바꿔서 생각하기 ex) 19는 10011로 비트 5개 조합해서 만들 수 있다.
// 1,2,4,8,4 가 있으면 1~19까지 다 나타낼 수 있다.

// 그냥 1,2,4,8,16으로 계속 시도하다가 오래걸림 -> 이렇게 생각하고 하면 19까지가 아닌 31까지 다 조합가능해서 틀림
import java.io.StreamTokenizer
fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    data class Thing(val w : Int, val v : Int)

    val newThings = mutableListOf<Thing>()
    repeat(n){
        val w = input()
        val v = input()
        var cnt = input()

        val len = Integer.toBinaryString(cnt).length
        for(i in  0 until  len-1){
            val temp = 1 shl i
            cnt -= temp
            newThings.add(Thing(w * temp, v * temp))
        }
        if(cnt>0) newThings.add(Thing(w * cnt, v * cnt))
    }

    val dp = IntArray(m+1){0}
    newThings.forEach { t ->
        for(c in m downTo t.w){
            dp[c] = maxOf(dp[c], t.v + dp[c-t.w])
        }
    }
    println(dp[m])
}
//
//        var j = 0
//        while (cnt > 0) {
//            val tmp = minOf(1 shl j, cnt)
//            newThings.add(Thing(w *tmp, v * tmp))
//            cnt -= tmp
//            j++
//        }

//    val ns = newThings.size
//    val dp = Array(ns+1){IntArray(m+1){0}}
//
//    for(i in 1.. ns){
//        val t = newThings[i-1]
//        for(j in 1.. m){
//            if(j >= t.w){
//                dp[i][j] = maxOf(dp[i-1][j], t.v + dp[i-1][j-t.w])
//            }else{
//                dp[i][j] = dp[i-1][j]
//            }
//        }
//    }
//    println(dp[ns][m])

//    things.forEach { t ->
//        for(k in 1..t.cnt){
//            val tw = t.w * k
//            val tv = t.v * k
//
//            if(tw > m) break
//            for(c in m downTo tw){
//                dp[c] = maxOf(dp[c], tv + dp[c-tw])
//            }
//        }
//    }
