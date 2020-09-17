package hoge.example.teststudy

/**
 * 単純でないテスト対象の例。内部で自身あるいは他のオブジェクトのメソッドを呼び出したり、プロパティを参照してたりしている
 * こういう場合はテストダブルと呼ばれるテクニックが用いられる。
 * テストダブルを利用すると、このクラスの例だと任意のweatherを返却することができる
 * double：影武者（テストダブルの起源は、スタントダブル（スタントマン）らしいけど一応英語の勉強として）
 * ユニットテストの場合、各モジュールの振る舞いのみフォーカスを当ててテストを書くという性質上、依存コンポネまで完全に動作するかは
 * 確認の対象外。
 *
 * テストダブルの５つの役割
 * - スタブ：事前定義した任意の値をテスト対象に与えるテストダブルのこと。
 * - モック：テスト対象メソッド実行時に、テスト対象が依存コンポネに与える値や挙動（出力）を検証するために使われる。
 * モックするメソッドが戻り値を持つ場合にはスタブとしての機能を備える場合もあるが、本質の役割はテスト対象から依存コンポネに対する出力を検証すること
 * - スパイ：スタブの上位互換的な。テスト対象に値を与えるのが主な責務。モックと同じくテスト対象が依存コンポネに与える値を記録する機能も持つ
 * テスト対象に与える値のコントロールに重きが置かれている
 * - フェイク：実装のコンポネと同等か極めてそれに近い挙動を持つ実装オブジェクト。
 * SQLiteに対して全く同じ機能をHashTableで実装したインメモリのDB等。
 * - ダミー：テスト対象メソッドとは依存関係はないけどコンパイルなどの都合上用意しないといけないオブジェクト。
 *
 * テストダブルを用いる目安としては、「外界とのやり取り(ネットワークやディスクIO)」濃霧で判断する方法もある
 * あくまでダブルはハリボテなので、正しく使わないとテストの信頼性が下がってしまう
 *
 */
// 元々のクラス
//class WeatherForecast {
//  private val satellite = Satellite()
//
//  // このメソッドはSatellite#getWeather()の返す値に依存している
//  // SatelliteをWeatherForecast#shouldBringUmbrella()の依存コンポーネントと呼ぶ
//  fun shouldBringUmbrella(): Boolean {
//    return when (satellite.getWeather()) {
//      Weather.SUNNY, Weather.CLOUDY -> false
//      Weather.RAINY -> true
//    }
//  }
//}

/**
 * ===============================
 * Satelliteクラスをスタブ化した例
 */
//class WeatherForecast(private val satellite: Satellite) {
//
//  // このメソッドはSatellite#getWeather()の返す値に依存している
//  // SatelliteをWeatherForecast#shouldBringUmbrella()の依存コンポーネントと呼ぶ
//  fun shouldBringUmbrella(): Boolean {
//    return when (satellite.getWeather()) {
//      Weather.SUNNY, Weather.CLOUDY -> false
//      Weather.RAINY -> true
//    }
//  }
//}
/**
 * ===============================
 */

/**
 * ===============================
 * モックの例
 */
//class WeatherForecast(private val satellite: Satellite) {
//
//  val recorder = WeatherRecorder()
//
//  fun shouldBringUmbrella(): Boolean {
//    return when (satellite.getWeather()) {
//      Weather.SUNNY, Weather.CLOUDY -> false
//      Weather.RAINY -> true
//    }
//  }
//
//  /**
//   * このメソッドは戻り値を持たない！ので外部からは処理が正しく完了したかはわからない、、
//   * ==> WeatherRecorder#record()メソッドが内部で呼ばれたことを確認してテスト成功とする
//   * 　　　依存コンポネであるWeatherRecorderはモックに差し替える。
//   */
//  fun recordCurrentWeather() {
//    val weather = satellite.getWeather()
//    recorder.record(weather)
//  }
//}
//
//class WeatherRecorder {
//  fun record(weather: Weather) {
//    // 現在の天気を端末内DBに記録するメソッドの体
//  }
//}
/**
 * ===============================
 * モックの例：WeatherRecorderクラスをモックオブジェクトにした例
 */
//class WeatherForecast(
//    private val satellite: Satellite,
//    private val recorder: WeatherRecorder
//) {
//
//  fun shouldBringUmbrella(): Boolean {
//    return when (satellite.getWeather()) {
//      Weather.SUNNY, Weather.CLOUDY -> false
//      Weather.RAINY -> true
//    }
//  }
//
//  fun recordCurrentWeather() {
//    val weather = satellite.getWeather()
//    recorder.record(weather)
//  }
//}
//
//open class WeatherRecorder {
//  open fun record(weather: Weather) {
//    // 現在の天気を端末内DBに記録するメソッドの体
//  }
//}
//
//class MockWeatherRecorder : WeatherRecorder() {
//  var weather: Weather? = null
//
//  /**
//   * record()メソッドが呼び出されたことを確認するためのフラグ
//   */
//  var isCalled = false
//
//  override fun record(weather: Weather) {
//    this.weather = weather
//    isCalled = true
//  }
//}
/**
 * ===============================
 */

/**
 * ===============================
 * スパイの例
 */
//class WeatherFormatter {
//  /**
//   * 副作用のないピュアな関数。テストケースでも実インスタンスのメソッドをそのまま呼び出して利用できる。
//   * したがって、依存コンポネのメソッド呼び出しさえ記録できればよく、スパイで置き換えるのが適切
//   */
//  fun format(weather: Weather): String = "Weather is $weather"
//}
//
//class WeatherForecast(
//    private val satellite: Satellite,
//    private val recorder: WeatherRecorder,
//    private val formatter: WeatherFormatter
//) {
//
//  fun recordCurrentWeather() {
//    val weather = satellite.getWeather()
//    val formatted = formatter.format(weather)
//    recorder.record(formatted)
//  }
//
//  fun shouldBringUmbrella(): Boolean {
//    return when (satellite.getWeather()) {
//      Weather.SUNNY, Weather.CLOUDY -> false
//      Weather.RAINY -> true
//    }
//  }
//}
//
//open class WeatherRecorder {
//  open fun record(weather: Weather) {
//    // 現在の天気を端末内DBに記録するメソッドの体
//  }
//  open fun record(weather: String) {
//    // 現在の天気を端末内DBに記録するメソッドの体
//  }
//}
//
//class MockWeatherRecorder : WeatherRecorder() {
//  var weather: Weather? = null
//
//  /**
//   * record()メソッドが呼び出されたことを確認するためのフラグ
//   */
//  var isCalled = false
//
//  override fun record(weather: Weather) {
//    this.weather = weather
//    isCalled = true
//  }
//}
/**
 * ===============================
 */

/**
 * ===============================
 * スパイの例：WeatherFormatterをスパイにする
 */
open class WeatherFormatter {
  /**
   * 副作用のないピュアな関数。テストケースでも実インスタンスのメソッドをそのまま呼び出して利用できる。
   * したがって、依存コンポネのメソッド呼び出しさえ記録できればよく、スパイで置き換えるのが適切
   */
  open fun format(weather: Weather): String = "Weather is $weather"
}

class SpyWeatherFormatter : WeatherFormatter() {
  var weather: Weather? = null
  var isCalled = false

  override fun format(weather: Weather): String {
    this.weather = weather
    isCalled = true
    return super.format(weather)
  }
}

class WeatherForecast(
  private val satellite: Satellite,
  private val recorder: WeatherRecorder,
  private val formatter: WeatherFormatter
) {

  fun recordCurrentWeather() {
    val weather = satellite.getWeather()
    val formatted = formatter.format(weather)
    recorder.record(formatted)
  }

  fun shouldBringUmbrella(): Boolean {
    return when (satellite.getWeather()) {
      Weather.SUNNY, Weather.CLOUDY -> false
      Weather.RAINY -> true
    }
  }
}

open class WeatherRecorder {
  open fun record(weather: Weather) {
    // 現在の天気を端末内DBに記録するメソッドの体
  }

  open fun record(weather: String) {
    // 現在の天気を端末内DBに記録するメソッドの体
  }
}

class MockWeatherRecorder : WeatherRecorder() {
  var weather: Weather? = null

  /**
   * record()メソッドが呼び出されたことを確認するためのフラグ
   */
  var isCalled = false

  override fun record(weather: Weather) {
    this.weather = weather
    isCalled = true
  }
}


/**
 * ===============================
 */
