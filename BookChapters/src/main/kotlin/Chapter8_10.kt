fun main() {
	
    // Arrays
    val fiveFives = IntArray(5, { 5 })
    fiveFives.forEach{ print("$it ") }
    
    println()
    var oddNumbers = intArrayOf(1, 3, 5, 7)
    /* 
	But beware that you are not actually appending the value onto the existing array, but 
    instead creating an entirely new array that has the additional element and assigning 
	the new array to the original variable. */
    oddNumbers += 9 
    oddNumbers.forEach{ print("$it ") }
    println()
    
    // Map
    val map = mapOf("A" to 1, 2 to 2, null to 3, null to 4)
    println(map[null])
    
    var pairs = HashMap<Any?, Any?>()
    pairs["new"] = "here we go"
    println(pairs)
    
    // Set
    val hashSet = HashSet<Int>()
   	
    // String, Char
   	val str: String = "STRING"
    val chr: CharArray = str.toCharArray()
    println(chr)
    
    // Lambda
    fun addFunction(a: Int, b:Int) = a + b 
	// e.g. operateOnNumbers(4, 2, operation = ::addFunction) - this is how to pass function as a lambda parameter
	
    // Closure
    var counter = 0 
	val incrementCounter = { 
  		counter += 1 
	}
    repeat(50) {incrementCounter()}
    println(counter)
    
    // Yielding lambda
    fun countingLambda(): () -> Int { 
    	var counter = 0 
    	val incrementCounter: () -> Int = { 
        	counter += 1 
       	 	counter 
    	} 
    	return incrementCounter 
	}
    
    val counter1 = countingLambda() 
	val counter2 = countingLambda() 
 
    println(counter1()) // > 1 
    println(counter2()) // > 1 
    println(counter1()) // > 2 
    println(counter1()) // > 3 
    println(counter2()) // > 2
//     The two counters created by the function are mutually exclusive and count 
//     independently. Neat!
    
    // Filtering lambdas
    val names = arrayOf("ZZZZZZ", "BB", "A", "CCCC", "EEEEE") 
	names.sorted() // A, BB, CCCC, EEEEE, ZZZZZZ	
    
    val namesByLength = names.sortedWith(compareBy { -it.length }) 
	println(namesByLength) // > [ZZZZZZ, EEEEE, CCCC, BB, A]
    
    // Changing types of values
    // The map function can also be used to change the type. You can do that like so:
	val userInput = listOf("0", "11", "haha", "42") 
	val numbers = userInput.map {   it.toIntOrNull() } 
	println(numbers) // > [0, 11, null, 42]
    
    val numbers2 = userInput.mapNotNull { it.toIntOrNull() } 
	println(numbers2) // > [0, 11, 42]
    
}