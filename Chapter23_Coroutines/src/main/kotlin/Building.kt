import kotlinx.coroutines.*

class Building(
    val name: String,
    var floors: Int = 0,
    private val scope: CoroutineScope
) {

    suspend fun makeFoundation() = scope.launch {
        delay(300)
        speakThroughBullhorn("[${Thread.currentThread().name}] The foundation is ready")
    }

    suspend fun buildFloor(floor: Int) = scope.launch {
        delay(100)
        speakThroughBullhorn("[${Thread.currentThread().name}] Floor number $floor floor is built")
        ++floors
    }

    suspend fun placeWindows(floor: Int) = scope.launch {
        delay(100)
        speakThroughBullhorn("[${Thread.currentThread().name}] Windows are placed on floor number $floor")
    }

    suspend fun installDoors(floor: Int) = scope.launch {
        delay(100)
        speakThroughBullhorn("[${Thread.currentThread().name}] Doors are installed on floor number $floor")
    }



    suspend fun provideElectricity(floor: Int) = scope.launch {
        delay(100)
        speakThroughBullhorn("[${Thread.currentThread().name}] Electricity is provided on floor number $floor")
    }

    suspend fun buildRoof() = scope.launch {
        delay(200)
        speakThroughBullhorn("[${Thread.currentThread().name}] The roof is ready")
    }

    suspend fun fitOut(floor: Int) = scope.launch {
        delay(200)
        speakThroughBullhorn("[${Thread.currentThread().name}] Floor number $floor is furnished")
    }

    fun speakThroughBullhorn(message: String) = println(message)
}