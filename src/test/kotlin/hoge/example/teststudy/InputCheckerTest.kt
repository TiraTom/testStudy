package hoge.example.teststudy

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.*


internal class InputCheckerTest {

  lateinit var target: InputChecker

  @BeforeEach
  fun setUp() {
    target = InputChecker()
  }

  @AfterEach
  fun tearDown() {
  }

  @Test
  fun isValid_givenAlphaNumeric_returnsTrue() {
    val actual = target.isValid("abc123")
    assertThat(actual).isTrue()
  }

  // 例外がスローされることのテスト
  @Test
  fun isValid_givenNull_throwsIllegalArgumentException() {
    Assertions.assertThrows(IllegalArgumentException::class.java) {
      target.isValid(null)
    }
  }

  @Test
  fun exceptionThrowTest() {
    assertThatExceptionOfType(RuntimeException::class.java)
      .isThrownBy { target.isValid(null) }
      .withMessage("Aborted!")
      .withNoCause()  // この例外が他の例外経由で送出されていないことを検証する
  }

  // テストケースを一時的にスキップする
  @Test
  @Disabled("スキップ")
  fun temporarliSkipThisTest() {
    /* 省略　*/
    target.isValid("hugaa")
  }

  @Test
  fun chainTest() {
    assertThat(3.14159)
      .isNotZero()  // ()無しだと型推論でエラーになってテストイベントが発生しない
  }

  @Test
  fun chainTest2() {
    assertThat("TOKYO")
      .`as`("TEXT CHECK TOKYO")  // アサーションにわかりやすいラベルを付与する。テストが失敗した際にはこのラベルが表示される
      .isNotEqualTo("HOKKAIDO")
      .isEqualToIgnoringCase("tokyo")
      .isNotEqualTo("KYOTO")
      .isNotBlank() // ()無しだと型推論でエラーになってテストイベントが発生しない
      .startsWith("TO")
      .endsWith("YO")
      .contains("OKY")
      .matches("[A-Z]{5}")
      .isInstanceOf(String::class.java)
  }

  @Test
  fun listTest() {
    val target = listOf("Tsubakurou", "Tsubami", "Torukuya")

    assertThat(target)
      .hasSize(3)
      .contains("Torukuya")
      .containsOnly("Tsubakurou", "Torukuya", "Tsubami")
      .containsExactly("Tsubakurou", "Tsubami", "Torukuya")
      .doesNotContain("Gabit")
  }

  @Test
  fun filteringTest() {
    val target = listOf(
      BallTeam("Giants", "San Francisco", "AT&T Park"),
      BallTeam("Dodgers", "Los Angels", "Dodger Stadium"),
      BallTeam("Angels", "Los Angels", "Angel Stadium"),
      BallTeam("Athletics", "Oakland", "Oakland Coliseum"),
      BallTeam("Padres", "San Diego", "Petco Part")
    )
    assertThat(target)
      .filteredOn { team -> team.city.startsWith("San") }
      .filteredOn { team -> team.city.endsWith("Francisco") }
      .extracting("name", String::class.java)
      .containsExactly("Giants")

    assertThat(target)
      .filteredOn { team -> team.city == "Los Angels" }
      .extracting("name", "stadium")
      .containsExactly(
        tuple("Dodgers", "Dodger Stadium"),
        tuple("Angels", "Angel Stadium")
      )
  }


}