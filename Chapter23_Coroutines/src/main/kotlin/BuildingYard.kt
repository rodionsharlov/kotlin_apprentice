import kotlinx.coroutines.*

class BuildingYard {
    suspend fun startProject(name: String, floors: Int) {
        val building = withContext(Dispatchers.Default) {
            val building = Building(name, scope = this)
            val cores = Runtime.getRuntime().availableProcessors()
            building.speakThroughBullhorn(
                "The building of $name is started with $cores building machines engaged")
            building.makeFoundation().join()
            (1..floors).forEach {
                // A floor should be raised before we can decorate it
                building.buildFloor(it).join()

                // These decorations could be made at the same time
                building.placeWindows(it)
                building.installDoors(it)
                building.provideElectricity(it)
                building.fitOut(it)
            }

            building.buildRoof().join()
            building
        }
        if (building.floors == floors) {
            building.speakThroughBullhorn("${building.name} is ready!")
        }
    }
}