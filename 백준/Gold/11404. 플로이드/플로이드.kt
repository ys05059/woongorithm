import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    fun input() : Int{
        nextToken()
        return nval.toInt()
    }
    val n = input()
    val m = input()

    val mtx = Array(n){i ->
        IntArray(n){ j -> if(i == j) 0 else Int.MAX_VALUE }
    }
    repeat(m){
        val a = input()-1
        val b = input()-1
        val c = input()
        if(mtx[a][b] > c) mtx[a][b] = c
    }

    for (k in 0 until n){
        for ( i in 0 until n){
            if(k == i || mtx[i][k] == Int.MAX_VALUE) continue
            for ( j in 0 until n){
                if(i == j || mtx[k][j] == Int.MAX_VALUE) continue
                val sum = mtx[i][k] + mtx[k][j]
                if(mtx[i][j] > sum) mtx[i][j] = sum
            }
        }
    }
    val sb = StringBuilder()
    mtx.forEach { ary ->
        ary.forEachIndexed { index, value ->
            if(value == Int.MAX_VALUE) sb.append(0) else sb.append(value)
            if (index < ary.lastIndex) sb.append(" ")
        }
        sb.append('\n')
    }
    println(sb)
}