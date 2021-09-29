fun add(a: Int, b: Int): Int { 
  return a + b 
}

fun main() {
	var function = ::add
    println(function(1,1))
    
    var int: Int? = 1 
    if (int != null) int += 1
	// int?.let{ int += 1 } // Or the next one (which is not safe)
    int = int!! + 1
    // int += 1 // Does not work because of the nullable type Int?
}