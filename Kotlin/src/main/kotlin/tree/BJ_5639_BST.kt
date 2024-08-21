package main.kotlin.tree

import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    fun input() : Int? {
        return when (nextToken()){
            StreamTokenizer.TT_NUMBER -> nval.toInt()
            else -> null
        }
    }
    val sb = StringBuilder()
    val ary = ArrayList<Int>()

    while(true){
        val next = input() ?: break
        ary.add(next)
    }

    fun bs(s : Int, e : Int){
        if(s > e) return
        var mid = s + 1
        while(mid <= e && ary[s] > ary[mid]) {      // 전위라서 ary[s]랑 비교
            mid++
        }
        bs(s+1, mid -1)
        bs(mid,e)
        sb.appendLine(ary[s])
    }
    bs(0,ary.size-1)
    print(sb)
}
