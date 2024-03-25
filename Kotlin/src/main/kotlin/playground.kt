fun main() {
    var x = 9
    var y = 4
    x = x xor y
    y = x xor y
    x = x xor y
    println("x : $x, y : $y")
}
