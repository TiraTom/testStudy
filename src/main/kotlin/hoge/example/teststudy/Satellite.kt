package hoge.example.teststudy

/**
 * 元々のSatelliteクラス
 */
//class Satellite {
//  fun getWeather(): Weather {
//   return when((1..9).shuffled().first()) {
//     in 1..3 -> Weather.SUNNY
//     in 4..6 -> Weather.CLOUDY
//     in 7..9 -> Weather.RAINY
//     else -> Weather.RAINY
//   }
//  }
//}

/**
 * ===============================
 * Satelliteクラスをスタブ化した例
 */
open class Satellite {
  open fun getWeather(): Weather {
    return when ((1..9).shuffled().first()) {
      in 1..3 -> Weather.SUNNY
      in 4..6 -> Weather.CLOUDY
      in 7..9 -> Weather.RAINY
      else -> Weather.RAINY
    }
  }
}

class StubSatellite(private val anyWeather: Weather) : Satellite() {
  override fun getWeather(): Weather {
    return anyWeather
  }
}
/**
 * ===============================
 */
