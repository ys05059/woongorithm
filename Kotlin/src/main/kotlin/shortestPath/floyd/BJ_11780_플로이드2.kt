package shortestPath.floyd


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
    val rmtx = Array(n){ Array(n){ listOf<Int>() } }

    repeat(m){
        val a = input()-1
        val b = input()-1
        val c = input()
        if(mtx[a][b] > c) {
            mtx[a][b] = c
            rmtx[a][b] = listOf(a+1,b+1)
        }
    }

    for (k in 0 until n){
        for ( i in 0 until n){
            if(k == i || mtx[i][k] == Int.MAX_VALUE) continue
            for ( j in 0 until n){
                if(i == j || mtx[k][j] == Int.MAX_VALUE) continue
                val sum = mtx[i][k] + mtx[k][j]
                if(mtx[i][j] > sum) {
                    mtx[i][j] = sum
                    rmtx[i][j] = rmtx[i][k].dropLast(1) + rmtx[k][j]
                }
            }
        }
    }
    val sb = StringBuilder()
    mtx.forEach { ary ->
        ary.forEach {
            if(it == Int.MAX_VALUE) sb.append("0 ") else sb.append("$it ")
        }
        sb.append('\n')
    }

    for ( i in 0 until n){
        for (j in 0 until n){
            if(mtx[i][j] == 0) sb.append(0)
            else{
                sb.append("${rmtx[i][j].size} ")
                sb.append(rmtx[i][j].joinToString(" "))
            }
            sb.append('\n')
        }
    }

    println(sb)
}