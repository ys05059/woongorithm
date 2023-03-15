package bruteForce

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val dx = listOf(-1,0,1)
var answer = Int.MAX_VALUE
lateinit var graph : Array<IntArray>
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val (n,m) = br.readLine()!!.split(' ').map { it.toInt()}

    graph = Array(n) { br.readLine()!!.split(" ").map{ it.toInt() }.toIntArray() }

    for(j in 0 until m){
        calSum(0,j,0,-1,n, m)
    }
    bw.write(answer.toString())
    bw.flush()
    bw.close()
}
fun calSum(i : Int, j : Int, tempSum : Int,dxIndex : Int,n:Int, m : Int){
    if(i == n){
        answer = answer.coerceAtMost(tempSum)
        return
    }

    for (k in 0 until 3){
        if(dxIndex != k && j+ dx[k] in 0 until m){
            calSum(i+1,j+ dx[k],tempSum + graph[i][j+ dx[k]],k,n,m)
        }
    }
}


/*
    최소값을 구해야함
    항상 최소를 찾아가는거 무리
    결국 끝까지 내려갔을 때 최소가 돼야함
    걍 모든 첫 시작점에서 가장 최소가 되는 값 구해서 그 중에서 최소값 구하면 될 것 같음
*
* */