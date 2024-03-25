package binarySearch

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n,t) = br.readLine().split(" ").map { it.toInt()}
    val pairlist = ArrayList<Pair<Int,Int>>()

    repeat(n){
        val (l,r) = br.readLine().split(" ").map { it.toInt()}
        pairlist.add(Pair(l,r))
    }

    val maxpOfL = pairlist.maxBy { it.first }
    val maxpOfR = pairlist.maxBy { it.second}
    var s = maxpOfL.first
    var tempSum = 0
    while(true){
        tempSum = 0
        pairlist.forEach{
            if(it.second > s ){
                tempSum += s
            }else{
                tempSum += it.second
            }
        }
        if(tempSum >= t){
            if (pairlist.sumOf { it.first } > t){
                bw.write("-1")
            }else{
                bw.write(s.toString())
            }
            break
        }else if(s > maxpOfR.second){
            bw.write("-1")
            break
        }else{
            s+=1
        }
    }
    bw.flush()
    bw.close()
}