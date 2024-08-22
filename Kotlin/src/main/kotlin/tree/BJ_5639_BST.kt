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

//
//import java.io.StreamTokenizer
//
//fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
//    fun input() : Int? {
//        return when (nextToken()){
//            StreamTokenizer.TT_NUMBER -> nval.toInt()
//            else -> null
//        }
//    }
//    val tree = Tree
//    while(true){
//        val next = input() ?: break
//        tree.insert(next)
//    }
//    tree.postorderTraversal()
//}
//
//private object Tree{
//    data class TreeNode<T>(
//        val value: T,
//        var left: TreeNode<T>? = null,
//        var right: TreeNode<T>? = null
//    )
//    private var root : TreeNode<Int>? = null
//    private val sb = StringBuilder()
//
//    fun insert(value: Int){
//        root = insert(root, value)
//    }
//
//    private fun insert(node : TreeNode<Int>?, value: Int) : TreeNode<Int>{
//        if(node == null) {
//            return TreeNode(value)
//        }
//        when{
//            value < node.value -> node.left = insert(node.left,value)
//            value > node.value -> node.right = insert(node.right,value)
//        }
//        return node
//    }
//
//    fun postorderTraversal(){
//        postorderTraversal(root)
//        println(sb.toString())
//    }
//
//    private fun postorderTraversal(node : TreeNode<Int>?){
//        if(node != null){
//            postorderTraversal(node.left)
//            postorderTraversal(node.right)
//            sb.append(node.value).append("\n")
//        }
//    }
//}