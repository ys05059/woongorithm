package main.kotlin.tree

import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    fun input() : Int? {
        return when (nextToken()){
            StreamTokenizer.TT_NUMBER -> nval.toInt()
            else -> null
        }
    }
    while(true){
        val next = input() ?: break
    }
}

private object Tree{
    data class TreeNode<T>(
        val value: T,
        var left: TreeNode<T>? = null,
        var right: TreeNode<T>? = null
    )
    private var root : TreeNode<Int>? = null
    private val sb = StringBuilder()

    fun insert(value: Int){
        root = insert(root, value)
    }

    private fun insert(node : TreeNode<Int>?, value: Int) : TreeNode<Int>{
        if(node == null) {
            return TreeNode(value)
        }
        when{
            value < node.value -> node.left = insert(node.left,value)
            value > node.value -> node.right = insert(node.right,value)
        }
        return node
    }

    fun postorderTraversal(){
        postorderTraversal(root)
        println(sb.toString())
    }

    private fun postorderTraversal(node : TreeNode<Int>?){
        if(node != null){
            postorderTraversal(node.left)
            postorderTraversal(node.right)
            sb.append(node.value).append("\n")
        }
    }
}