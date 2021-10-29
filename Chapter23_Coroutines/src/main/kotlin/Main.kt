import kotlinx.coroutines.*

fun main() = runBlocking {
    BuildingYard().startProject("Smart house", 20)
}

/*
fun main() = runBlocking {
    launch(Dispatchers.Default) {
        (0..10).forEach {
            println("Message #$it from the ${Thread.currentThread().name}")
        }
    }

    (0..10).forEach {
        println("Message #$it from the ${Thread.currentThread().name}")
    }
    val cs: CoroutineScope
}
*/