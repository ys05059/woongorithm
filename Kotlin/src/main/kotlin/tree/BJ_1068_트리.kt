package tree

import java.io.BufferedReader
import java.io.InputStreamReader

data class TreeNode<T>(
    val data : T,
    val left : TreeNode<T>? = null,
    val right : TreeNode<T>? = null,
)
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    repeat(n){

    }
}


private fun countLeafNode(){

}