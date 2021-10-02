import kotlin.properties.Delegates

class LightBulb { 
  companion object { 
    const val maxCurrent = 40 
  } 
  var current by Delegates.vetoable(0) { 
    _, _, new -> 
    if (new > maxCurrent) { 
      println( 
        "Current too high, falling back to previous setting.") 
      false 
    } else { 
      true 
    } 
  } 
}

fun main(){

    val light = LightBulb() 
    light.current = 50 
    var current = light.current // 0 
    light.current = 40 
    current = light.current // 40
}