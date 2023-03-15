package twoPointer

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n,m) = br.readLine()!!.split(" ").map { it.toInt() }

    val listA = br.readLine()!!.split(" ").map { it.toInt() }
    val listB = br.readLine()!!.split(" ").map { it.toInt() }

    var result = mutableListOf<Int>()
    var (a, b) = listOf(0,0)

    while(!(a == n && b == m)) {
        if (a == n ){
            result.add(listB[b++])
        }else if (b == m){
            result.add(listA[a++])
        }else{
            if(listA[a] <= listB[b]) result.add(listA[a++]) else result.add(listB[b++])
        }
//        println("$result")
//        println("a :$a b : $b")
    }
    bw.write(result.joinToString(" "))
    bw.flush()
    bw.close()
}