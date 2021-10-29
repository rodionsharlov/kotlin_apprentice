import kotlin.reflect.KProperty

class Company(val name: String) {
    private val departments: ArrayList<Department> = arrayListOf()

    val allEmployees: List<Employee>
        get() = arrayListOf<Employee>().apply {
            departments.forEach { addAll(it.employees) }
            sort()
        }

    operator fun plusAssign(department: Department) {
        departments.add(department)
    }

    operator fun minusAssign(department: Department) {
        departments.remove(department)
    }
}

class Department(val name: String) : Iterable<Employee> {
    val employees: ArrayList<Employee> = arrayListOf()
    operator fun plusAssign(employee: Employee) {
        employees.add(employee)
        println("${employee.name} hired to $name department")
    }

    operator fun minusAssign(employee: Employee) {
        if (employees.contains(employee)) {
            employees.remove(employee)
            println("${employee.name} fired from $name department")
        }
    }

    operator fun get(index: Int): Employee? {
        return if (index < employees.size) {
            employees[index]
        } else {
            null
        }
    }

    operator fun set(index: Int, employee: Employee) {
        if (index < employees.size) {
            employees[index] = employee
        }
    }

    operator fun contains(employee: Employee) =
        employees.contains(employee)

    override fun iterator() = employees.iterator()
}

data class Employee(val company: Company, val name: String, var salary: Int): Comparable<Employee> {
    operator fun inc(): Employee {
        salary += 5000
        println("$name got a raise to $$salary")
        return this
    }
    operator fun dec(): Employee {
        salary -= 5000
        println("$name's salary decreased to $$salary")
        return this
    }
    operator fun plusAssign(increaseSalary: Int) {
        salary += increaseSalary
        println("$name got a raise to $$salary")
    }

    operator fun minusAssign(decreaseSalary: Int) {
        salary -= decreaseSalary
        println("$name's salary decreased to $$salary")
    }

    override operator fun compareTo(other: Employee): Int {
        return when (other) {
            this -> 0
            else -> name.compareTo(other.name)
        }
    }

    operator fun rangeTo(other: Employee): List<Employee> {
        val currentIndex = company.allEmployees.indexOf(this)
        val otherIndex = company.allEmployees.indexOf(other)

        // start index cannot be larger or equal to the end index
        if (currentIndex >= otherIndex) {
            return emptyList()
        }

        // get all elements in a list from currentIndex to otherIndex
        return company.allEmployees.slice(currentIndex..otherIndex)
    }
}

fun main() {
    // your company
    val company = Company("MyOwnCompany")
    val developmentDepartment = Department("Development")
    val qaDepartment = Department("Quality Assurance")
    val hrDepartment = Department("Human Resources")

    // employees
    var Julia = Employee(company, "Julia", 100_000)
    var John = Employee(company, "John", 86_000)
    var Peter = Employee(company, "Peter", 100_000)

    var Sandra = Employee(company, "Sandra", 75_000)
    var Thomas = Employee(company, "Thomas", 73_000)
    var Alice = Employee(company, "Alice", 70_000)

    var Bernadette = Employee(company, "Bernadette", 66_000)
    var Mark = Employee(company, "Mark", 66_000)

    Julia++
    Mark += 2500
    Alice -= 2000

    company += developmentDepartment
    company += qaDepartment
    company += hrDepartment

    developmentDepartment += Julia
    developmentDepartment += John
    developmentDepartment += Peter

    qaDepartment += Sandra
    qaDepartment += Thomas
    qaDepartment += Alice

    hrDepartment += Bernadette
    hrDepartment += Mark

    qaDepartment -= Thomas

    if (Thomas !in qaDepartment) {
        println("${Thomas.name} no longer works here")
    }

    print((Alice..Mark).joinToString { it.name }) // prints "Alice, Bernadette, John, Julia, Mark"
}

/*
// Delegated properties as conventions
class NameDelegate {
    operator fun getValue(
        thisRef: Any?,
        property: KProperty<*>
    ): String {
        // return existing value
    }
    operator fun setValue(
        thisRef: Any?,
        property: KProperty<*>,
        value: String
    ) {
        // set received value
    }
}

fun main() {
    var name: String by NameDelegate()
}
*/