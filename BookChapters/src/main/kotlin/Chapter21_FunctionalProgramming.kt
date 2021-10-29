import java.util.*

class Robot(val name: String) {
    var strength: Int = 0
    private var health: Int = 100

    fun report(message: String) {
        println("$name: \t$message")
    }

    // 1
    var isAlive: Boolean = true

    private var random: Random = Random()

    init {
        strength = random.randomStrength()
        report("Created (strength $strength)")
    }

    fun damage(damage: Int) {
        val blocked = random.randomBlock()

        if (blocked) {
            report("Blocked attack")
            return
        }

        health -= damage
        report("Damage -$damage, health $health")

        if (health <= 0) {
            isAlive = false
        }
    }

    infix fun attack(robot: Robot) {
        val damage = random.randomDamage(strength)
        robot.damage(damage)
    }
}

object Battlefield {
    // 1
    inline fun beginBattle(
        firstRobot: Robot,
        secondRobot: Robot,
        onBattleEnded: Robot.() -> Unit
    ) {
        var winner: Robot? = null
        battle(firstRobot, secondRobot)
        winner = if (firstRobot.isAlive) firstRobot else secondRobot
        winner.onBattleEnded()
    }

    tailrec fun battle(firstRobot: Robot, secondRobot: Robot) {
        // 5
        firstRobot attack secondRobot
        // 6
        if (secondRobot.isAlive.not()) {
            return
        }
        // 7
        secondRobot attack firstRobot

        if (firstRobot.isAlive.not()) {
            return
        }
        // 8
        battle(firstRobot, secondRobot)
    }
}

fun Random.randomStrength(): Int {
    return nextInt(100) + 10
}

fun Random.randomDamage(strength: Int): Int {
    return (strength * 0.1 + nextInt(10)).toInt()
}

fun Random.randomBlock(): Boolean {
    return nextBoolean()
}

// Anonymous function (can be used as a parameter or value of a variable)
//fun(robot: Robot) {
//    robot.report("Win!")
//}

fun main() {
    val firstRobot = Robot("Experimental Space Navigation Droid")
    val secondRobot = Robot("Extra-Terrestrial Air Safety Droid")
    Battlefield.beginBattle(firstRobot, secondRobot) {
        this.report("Win!")
    }

    val participants = arrayListOf<Robot>(
        Robot("Extra-Terrestrial Neutralization Bot"),
        Robot("Generic Evasion Droid"),
        Robot("Self-Reliant War Management Device"),
        Robot("Advanced Nullification Android"),
        Robot("Rational Network Defense Droid"),
        Robot("Motorized Shepherd Cyborg"),
        Robot("Reactive Algorithm Entity"),
        Robot("Ultimate Safety Guard Golem"),
        Robot("Nuclear Processor Machine"),
        Robot("Preliminary Space Navigation Machine")
    )

//    val topCategory = participants.filter { it.strength > 80 }
    val topCategory = participants
        .filter { it.strength > 80 }
        .take(3)
        .sortedBy { it.name }

    // Example of sequence usage
    val random = Random()
    val sequence = generateSequence {
        random.nextInt(100)
    }

    sequence
        .take(15)
        .sorted()
        .forEach { println(it) }
}

fun onBattleEnded(winner: Robot) {
    winner.report("Win!")
}