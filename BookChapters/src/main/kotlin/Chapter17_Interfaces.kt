interface Vehicle {
    fun accelerate()
    fun stop()
}

class Unicycle: Vehicle {
    var peddling = false

    override fun accelerate() {
        peddling = true
    }
    override fun stop() {
        peddling = false
    }
}

enum class Direction {
    LEFT, RIGHT
}

interface DirectionalVehicle {
    fun accelerate()
    fun stop()
    fun turn(direction: Direction)
    fun description(): String
}

interface OptionalDirectionalVehicle {
    fun turn(direction: Direction = Direction.LEFT)
}

class OptionalDirection: OptionalDirectionalVehicle {
    override fun turn(direction: Direction) {
        println(direction)
    }
}

interface SpaceVehicle {
    fun accelerate()
    // 1
    fun stop() {
        println("Whoa, slow down!")
    }
}

class LightFreighter: SpaceVehicle {
    // 2
    override fun accelerate() {
        println("Proceed to hyperspace!")
    }
}

class Starship: SpaceVehicle {
    override fun accelerate() {
        println("Warp factor 9 please!")
    }

    override fun stop() {
        super.stop()
        println("That kind of hurt!")
    }
}

interface VehicleProperties {
    val weight: Int // abstract
    val name: String
        get() = "Vehicle"
}

class Car: VehicleProperties {
    override val weight: Int = 1000
}

class Tank: VehicleProperties {
    override val weight: Int
        get() = 10000

    override val name: String
        get() = "Tank"
}

interface WheeledVehicle: Vehicle {
    val numberOfWheels: Int
    var wheelSize: Double
}

class Bike: WheeledVehicle {
    var peddling = false
    var brakesApplied = false

    override val numberOfWheels = 2
    override var wheelSize = 622.0

    override fun accelerate() {
        peddling = true
        brakesApplied = false
    }

    override fun stop() {
        peddling = false
        brakesApplied = true
    }
}

interface Wheeled {
    val numberOfWheels: Int
    val wheelSize: Double
}

//class Tricycle: Wheeled, Vehicle {
//    // Implement both Vehicle and Wheeled
//}

interface SizedVehicle {
    var length: Int
}

class Boat: SizedVehicle, Comparable<Boat> {
    override var length: Int = 0
    override fun compareTo(other: Boat): Int {
        return when {
            length > other.length -> 1
            length == other.length -> 0
            else -> -1
        }
    }
}

fun main() {
    val car = OptionalDirection()
    car.turn() // > LEFT
    car.turn(Direction.RIGHT) // > RIGHT
    //
    val falcon = LightFreighter()
    falcon.accelerate() // > Proceed to hyperspace!
    falcon.stop() // > "Whoa, slow down!
    //
    val enterprise = Starship()
    enterprise.accelerate()
// > Warp factor 9 please!
    enterprise.stop()
// > Whoa, slow down!
// > That kind of hurt!"

    val titanic = Boat()
    titanic.length = 883
    val qe2 = Boat()
    qe2.length = 963
    println(titanic > qe2) // > false
}
