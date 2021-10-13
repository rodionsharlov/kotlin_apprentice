import java.util.Calendar;

enum class DayOfTheWeek(val isWeekend: Boolean = false) {
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday(true),
    Sunday(true);

    companion object {
        fun today(): DayOfTheWeek {
            // 1
            val calendarDayOfWeek =
                Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
            // 2
            var adjustedDay = calendarDayOfWeek - 2
            // 3
            val days = DayOfTheWeek.values()
            if (adjustedDay < 0) {
                adjustedDay += days.count()
            }
            // 4
            val today = days.first { it.ordinal == adjustedDay }
            return today
        }
    }
}

fun main(){
    for (day in DayOfTheWeek.values()) {
        println(
            "Day ${day.ordinal}: ${day.name}, is weekend: ${day.isWeekend}")
    }

    val dayIndex = 0
    val dayAtIndex = DayOfTheWeek.values()[dayIndex]
    println("Day at $dayIndex is $dayAtIndex")
    /**
     * Here we have something interesting: [tuesday]
     * */
    val tuesday = DayOfTheWeek.valueOf("Tuesday")
    println("Tuesday is day ${tuesday.ordinal}")
}