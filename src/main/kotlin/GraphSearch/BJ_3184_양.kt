package GraphSearch

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private lateinit var map : Array<CharArray>
private lateinit var visited : Array<BooleanArray>
private val dx = arrayOf(0,0,1,-1)
private val dy = arrayOf(1,-1,0,0)
private var tempResultOfDfs = arrayOf(0,0) // sheep, wolf
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    // initialize BruteForce.getGraph
    val (n,m) = br.readLine().split(" ").map { it.toInt()}
    map = Array(n){
        br.readLine().toCharArray()
    }
    visited = Array(n){BooleanArray(m)}
    for(i in map.indices){
        for(j in map[i].indices){
            if(map[i][j] == '#')
                visited[i][j]= true
        }
    }

    // test initialized BruteForce.getGraph
    for(i in map.indices){    // indices는 0부터 리스트의 크기까지를 0..size 이런식으로 나타내주는 메소드
        println(map[i].contentToString())
    }

    // Algorithm
    var sheep = 0
    var wolf = 0
    for (i in map.indices){
        for(j in map[i].indices){
            if(!visited[i][j]){
                println("i : $i j : $j")
                dfs(i,j,n,m)
                println("temp ${tempResultOfDfs.contentToString()}")
                if(tempResultOfDfs[0]> tempResultOfDfs[1]){
                    sheep += tempResultOfDfs[0]
                }else{
                    wolf += tempResultOfDfs[1]
                }
                tempResultOfDfs = arrayOf(0,0)
            }
        }
    }
    bw.write("$sheep $wolf")
    bw.flush()
    bw.close()
}

private fun dfs(x : Int,y:Int, n :Int, m : Int){
    println("GraphSearch.dfs 들어옴  x : $x y : $y ")
    visited[x][y] = true
    when(map[x][y]){
        'o' -> tempResultOfDfs[0] +=1
        'v' -> tempResultOfDfs[1] +=1
    }
    for (d in 0 until 4 ){
        val next = Pair(x+ dx[d],y+ dy[d])
        if(next.first < 0 || next.first >= n || next.second < 0 || next.second >= m) {
            continue
        }
        if(!visited[next.first][next.second]){
            dfs(next.first,next.second,n,m)
        }
    }
}