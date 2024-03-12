package bruteForce

/**
 * 접근방법
 * 1. 문자열 비교 + 브루트포스
 * 2. 조합 문제 + 비트마스크 -> 0과 1로 처리 -> xor 연산
 * 결론,, 그냥 브루트포스로 푸는게 맞다
 * 굳이 조합을 써야하는 이유가 없다
 * 핵심은 비둘기집의 원리
 */

import java.util.StringTokenizer

fun main() = with(System.`in`.bufferedReader()){
    val t = readLine().toInt()
    repeat(t){
        val n = readLine().toInt()
        val st = StringTokenizer(readLine())
        if(n>32){
            println(0)
            return@repeat
        }
        val ary = Array(n){st.nextToken()}
        var ans = Int.MAX_VALUE

        for(i in 0 until n-2){
            for(j in i +1 until  n-1){
                for(k in j+1 until n){
                    ans = ans.coerceAtMost(distance(ary[i],ary[j]) +  distance(ary[i],ary[k]) + distance(ary[j],ary[k]) )
                    if(ans == 0){
                        println(0)
                        return@repeat
                    }
                }
            }
        }

        println(ans)
    }
}
fun distance(s1 : String, s2 : String) : Int {
    var count = 0
    for(i in s1.indices){
        if(s1[i] != s2[i]) count++
    }
    return count
}

