import kotlin.math.PI
import kotlin.math.sqrt

class Circle(var radius: Double = 0.0) {
  var area: Double
    get() {
      return PI * radius * radius
    }
    /* Если закомментировать метод set(), то класс будет работать, но 
     * radius не будет обновляться, а, следовательно, не будет меняться
     * и вычисленное значение area*/
    set(value) {
      radius = sqrt(value / PI)
    }

  fun grow(factor: Double) {
    area *= factor /* Здесь вызывается метод set(), 
      который в свою очередь устанавливает новое значение свойства radius,
      основываясь на результате вычисления area * factor. Таким образом, 
      в следующий раз, когда будет считано свойство area, автоматически 
      будет вызыван метод get(), который вернёт уже другое значение area, 
      вычисленное на основе обновлённого значения свойства radius */
  }
  /* Вывод: значениями свойств с кастомными методами set() и get() можно 
   * манипулировать через другие методы, но должны быть реализованы 
   * оба set() и get() */
}

fun main(){
	var my = Circle(3.0)
    println("Radius: ${my.radius} Area: ${my.area}")
    my.grow(1.1)
    println("Radius: ${my.radius} Area: ${my.area}")
}