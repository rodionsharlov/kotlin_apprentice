/* 
Используем синглетон для создания единого уникального регистра объектов.
Используем паттерн фабрика для создания объектов и присвоения им инкрементного идентификатора.
Фабрика реализована в виде статического метода newScientist класса Scientist.
При этом конструктор класса Scientist является непубличным. 
Объект для реестра не нужно создавать отдельно, так как он существует с момента объявления 
*/

class Scientist private constructor( 
  val id: Int, 
  val firstName: String, 
  val lastName: String 
) { 
 
  companion object { 
    var currentId = 0 
 
    fun newScientist( 
      firstName: String, 
      lastName: String 
    ): Scientist { 
      currentId += 1 
      return Scientist(currentId, firstName, lastName) 
    } 
  } 
 
  var fullName = "$firstName $lastName" 
}

object ScientistRepository { 
  val allScientists = mutableListOf<Scientist>() 
 
  fun addScientist(scientist: Scientist) { 
    allScientists.add(scientist) 
  } 
 
  fun removeScientist(scientist: Scientist) { 
    allScientists.remove(scientist) 
  } 
 
  fun listAllScientists() { 
    allScientists.forEach { 
      println("${it.id}: ${it.fullName}") 
    } 
  } 
}

fun main() {
  val emmy = Scientist.newScientist("Emmy", "Noether")
  val isaac = Scientist.newScientist("Isaac", "Newton")
  val nick = Scientist.newScientist("Nikola", "Tesla")

  ScientistRepository.addScientist(emmy)
  ScientistRepository.addScientist(isaac)
  ScientistRepository.addScientist(nick)

  ScientistRepository.listAllScientists()

  // 1: Emmy Noether
  // 2: Isaac Newton
  // 3: Nikola Tesla
}
