fun main() {
    /*
    // Example of a Scope within run {}. I can't refer to b outside of this scope
	val a = 1
    run {
        val b = 2
        println("b: $b")
    }
    val c = 3
    println("$a $b $c")
    
    //Unresolved reference: b - whic is out of scope
    */
    
    // Ranges
    val closedRange = 0..5
    val halfOpenRange = 0 until 5
    val decreasingRange = 5 downTo 0 step 3
    
    // Loops
    repeat(3) { /*Do something here repeatedly*/ }
    
    var sum = 0 
	rowLoop@ for (row in 0 until 8) { 
  		columnLoop@ for (column in 0 until 8) { 
    		if (row == column) continue@rowLoop // Jump to the next iteration of the outer cycle
    		sum += row * column 
    	} 
	}
}