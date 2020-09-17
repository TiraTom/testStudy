package hoge.example.teststudy

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class WeatherForecastTest {

  lateinit var weatherForecast: WeatherForecast
  lateinit var recorder: MockWeatherRecorder
  lateinit var formatter: SpyWeatherFormatter

  /**
   * スタブを利用したテスト
   */
  @Test
  fun shouldBringUmbrella() {
    val satellite =
      StubSatellite(Weather.SUNNY)
    weatherForecast = WeatherForecast(satellite, recorder, formatter)

    val actual = weatherForecast.shouldBringUmbrella()
    assertThat(actual).isFalse()
  }

//  /**
//   * モックを利用したテスト
//   */
//  @BeforeEach
//  fun setUp() {
//    val satellite = Satellite()
//    recorder = MockWeatherRecorder()
//    weatherForecast = WeatherForecast(satellite, recorder, formatter)
//  }

  /**
   * モックを利用したテスト
   */
  @Test
  fun recordCurrentWeather_assertCalled() {
    weatherForecast.recordCurrentWeather()
    val isCalled = recorder.isCalled
    assertThat(isCalled).isTrue()

    val weather = recorder.weather
    assertThat(weather)
      .isIn(
        Weather.SUNNY,
        Weather.CLOUDY,
        Weather.RAINY
      )
  }


  /**
   * スパイを利用したテスト
   */
  @BeforeEach
  fun setUp() {
    val satellite = Satellite()
    val recorder = MockWeatherRecorder()
    formatter = SpyWeatherFormatter()
    weatherForecast = WeatherForecast(satellite, recorder, formatter)
  }

  /**
   * スパイを利用したテスト
   * WeatherFormatterをスパイにする
   */
  @Test
  fun recordCurrentWeather_assertFormatterCalled() {
    weatherForecast.recordCurrentWeather()

    val isCalled = formatter.isCalled
    assertThat(isCalled).isTrue()

    val weather = formatter.weather
    assertThat(weather)
      .isIn(
        Weather.SUNNY,
        Weather.CLOUDY,
        Weather.RAINY
      )
  }
}