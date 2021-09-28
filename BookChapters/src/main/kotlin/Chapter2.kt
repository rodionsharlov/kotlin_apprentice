import kotlin.math.*

// compile-time constant:
const val reallyConstant: Int = 42

fun main() {
    println("%.0f".format(28.0 % 10.0))

    // bitwise operations
    println(1 shl 3)
    println(32 shr 2)

    //Some mathematical operatins
    sin(45 * PI / 180)
    // 0.7071067811865475
    cos(135 * PI / 180)
    // -0.7071067811865475
    sqrt(2.0)
    // 1.414213562373095

    // Funny way of defining a number
    var a = 1_000_000
    println(a)
}