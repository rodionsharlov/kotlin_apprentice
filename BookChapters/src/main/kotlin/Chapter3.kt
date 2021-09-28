fun main() {
    /*
    // This is how to form a long string
    val bigString = """
      |You can have a string
      |that contains multiple
      |lines 
      |   !
      |by 
      |
      |doing this. 
      """.trimMargin() 
    println(bigString)
    
    */
    
    /* PAIR and TRIPLE */
    
	//val coords: Pair<Int, Int> = Pair(2, 3)
    val coords = 2 to 3 // the same as above
    val (x, y) = coords //  destructuring declaration
    println("${coords.first} ${coords.second}") // accessing elements of a pair
    println("$x $y")
    
    val coords3D = Triple(2, 3, 5) 
    println(coords3D)
    val (x2, y2, _) = coords3D // omitting the third element of a triple

}