// An extension function
fun <T> List<T>.toBulletedList(): String {
    val separator = "\n - "
    return this.map { "$it" }.joinToString(separator, prefix =
    separator, postfix = "\n")
}

interface Checkable {
    fun checkIsOK(): Boolean
}

// 1
class Mover<T: Checkable>(
    // 2
    thingsToMove: List<T>,
    val truckHeightInInches: Int = (12 * 12)
) {

    // 3
    private var thingsLeftInOldPlace = mutableListOf<T>()
    private var thingsInTruck = mutableListOf<Any>()
    private var thingsInNewPlace = mutableListOf<T>()
    private var thingsWhichFailedCheck = mutableListOf<T>()

    // 4
    init {
        thingsLeftInOldPlace.addAll(thingsToMove)
    }

    // 5
    fun moveEverythingToTruck(startingContainer: Container<T>?) {
        var currentContainer = startingContainer
        while (thingsLeftInOldPlace.count() > 0) {
            val item = thingsLeftInOldPlace.removeAt(0)

            if (item.checkIsOK()) {
                // 1
                if (currentContainer != null) {
                    // 2
                    if (!currentContainer.canAddAnotherItem()) {
                        moveContainerToTruck(currentContainer)
                        currentContainer = currentContainer.getAnother()
                    }
                    // 3
                    currentContainer.addItem(item)
                    println("Packed your $item!")
                } else {
                    // 4
                    thingsInTruck.add(item)
                    println("Moved your $item to the truck!")
                }
            } else {
                thingsWhichFailedCheck.add(item)
                println("Could not move your $item to the truck :[")
            }
        }
        currentContainer?.let { moveContainerToTruck(it) }
    }

    private fun moveContainerToTruck(container: Container<T>) {
        thingsInTruck.add(container)
        println("Moved a container with your ${container.contents().toBulletedList()} to the truck!")
    }

    private fun tryToMoveItemIntoNewPlace(item: T) {
        if (item.checkIsOK()) {
            thingsInNewPlace.add(item)
            println("Moved your $item into your new place!")
        } else {
            thingsWhichFailedCheck.add(item)
            println("Could not move your $item into your new place :[")
        }
    }

    // 6
    fun moveEverythingIntoNewPlace() {
        val containers = thingsInTruck.filterIsInstance<Container<T>>()
        for (container in containers) {
            thingsInTruck.remove(container)
            while (container.canRemoveAnotherItem()) {
                val itemInContainer = container.removeItem()
                println("Unpacked your $itemInContainer!")
                tryToMoveItemIntoNewPlace(itemInContainer)
            }
        }
        while (thingsInTruck.count() > 0) {
            val item = thingsInTruck.removeAt(0) as? T
            if (item != null) {
                tryToMoveItemIntoNewPlace(item)
            } else {
                println("Something in the truck was not of the expected generic type: $item")
            }
        }
    }

    // 7
    fun finishMove() {
        println("OK, we finished! We were able to move your:${thingsInNewPlace.toBulletedList()}")
        if (thingsWhichFailedCheck.isNotEmpty()) {
            println("But we need to talk about your:${thingsWhichFailedCheck.toBulletedList()}")
        }
    }
}

class CheapThing(val name: String): Checkable {
    override fun toString(): String {
        return name
    }
    override fun checkIsOK(): Boolean = true
}

class BreakableThing(
    val name: String,
    var isBroken: Boolean = false
): Checkable {
    fun smash() {
        isBroken = true
    }

    override fun toString(): String {
        return name
    }

    override fun checkIsOK(): Boolean {
        return !isBroken
    }
}

// 1
interface Container<T> {
    // 2
    fun canAddAnotherItem(): Boolean
    fun addItem(item: T)
    // 3
    fun canRemoveAnotherItem(): Boolean
    fun removeItem(): T
    // 4
    fun getAnother(): Container<T>
    // 5
    fun contents(): List<T>
}

// 1
class CardboardBox: Container<BreakableThing> {
    //2
    private var items = mutableListOf<BreakableThing>()

    override fun contents(): List<BreakableThing> {
        // 3
        return items.toList()
    }

    // 4
    override fun canAddAnotherItem(): Boolean {
        return items.count() < 2
    }

    override fun addItem(item: BreakableThing) {
        // 5
        items.add(item)
    }

    override fun canRemoveAnotherItem(): Boolean {
        // 6
        return items.count() > 0
    }

    override fun removeItem(): BreakableThing {
        // 7
        val lastItem = items.last()
        items.remove(lastItem)
        return lastItem
    }

    override fun getAnother(): Container<BreakableThing> {
        // 8
        return CardboardBox()
    }
}

fun main () {
    val names: List<String> = listOf("Bob", "Carol", "Ted", "Alice")
    println("Names: ${names.toBulletedList()}")
    val firstName = names.first()
    println(firstName)

    val things = mutableListOf<Any>(1, 2)
    things.add("Steve")
    println("Things: ${things.toBulletedList()}")

    val map = mapOf(
        Pair("one", 1),
        Pair("two", "II"),
        Pair("three", 3.0f)
    )

    val valuesForKeysWithE = map.keys
        .filter { it.contains("e") }
        .map { "Value for $it: ${map[it]}" }
    println("Values for keys with E: ${valuesForKeysWithE.toBulletedList()}")

    val cheapThings = listOf(
        CheapThing("Cinder Block table"),
        CheapThing("Box of old books"),
        CheapThing("Ugly old couch")
    )
    val cheapMover = Mover(cheapThings)
    cheapMover.moveEverythingToTruck(null)
    cheapMover.moveEverythingIntoNewPlace()
    cheapMover.finishMove()

    val television = BreakableThing("Flat-Screen Television")
    val breakableThings = listOf(
        television,
        BreakableThing("Mirror"),
        BreakableThing("Guitar")
    )
    val expensiveMover = Mover(breakableThings)
    expensiveMover.moveEverythingToTruck(CardboardBox())
    television.smash()
    expensiveMover.moveEverythingIntoNewPlace()
    expensiveMover.finishMove()

    // Covariant <out T>
    // List<out T>
    val ints = listOf(1, 2, 3)
    val numbers: List<Number> = ints
//    val moreInts: List<Int> = numbers // causes an error (expectedly)

    // Invariant <T>
    // MutableList<T>, which has neither an in nor an out modiﬁer for its generic type.
//    val mutableInts = mutableListOf(1, 2, 3)
//    val mutableNumbers: MutableList<Number> = mutableInts // another error (expectedly)

    // Contravariant <in T>
    // interface Comparable<in T> { operator fun compareTo(other: T): Int }

    fun compare(comparator: Comparable<Number>) {
        val int: Int = 1
        comparator.compareTo(int)
        val float: Float = 1.0f
        comparator.compareTo(float)
        val intComparable: Comparable<Int> = comparator
        intComparable.compareTo(int)
//        intComparable.compareTo(float) // causes an error (expectedly)
    }
}