import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    repeat(n){
        val (node, left, right) = br.readLine().split(" ")
        Tree.add(node, left, right)
    }
    Tree.preOrder()
    println()
    Tree.inOrder()
    println()
    Tree.postOrder()
}

data class TreeNode<T>(
    var data : T,
    var left : TreeNode<T>?  = null,
    var right : TreeNode<T>? = null
)

private object Tree {
    private var root : TreeNode<String>?  = null
    fun add(data : String, left : String, right : String){
        if(root == null){
            if(data != ".") root = TreeNode(data)
            if(left != ".") root!!.left = TreeNode(left)
            if(right != ".") root!!.right = TreeNode(right)
        }
        else {
            searchAdd(root!!,data, left, right)
        }
    }

    private fun searchAdd(node: TreeNode<String>, data : String, left : String, right : String){
        if(node.data == data){
            if(left != ".") node.left = TreeNode(left)
            if(right != ".") node.right = TreeNode(right)
        }
        else{
            if(node.left != null) searchAdd(node.left!!, data,left, right)
            if(node.right != null) searchAdd(node.right!!, data,left, right)
        }
    }
    fun preOrder(node : TreeNode<String> = root!!){
        print(node.data)
        if(node.left != null) preOrder(node.left!!)
        if(node.right != null) preOrder(node.right!!)
    }
    fun inOrder(node : TreeNode<String> = root!!){
        if(node.left != null) inOrder(node.left!!)
        print(node.data)
        if(node.right != null) inOrder(node.right!!)
    }
    fun postOrder(node : TreeNode<String> = root!!){
        if(node.left != null) postOrder(node.left!!)
        if(node.right != null) postOrder(node.right!!)
        print(node.data)
    }
}
