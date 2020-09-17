package hoge.example.tdd2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Fizz Buzz 数列と変換規則を扱うFizzBuzzクラス")
internal class FizzBuzzTest {

  lateinit var fizzBuzz: FizzBuzz

  @BeforeEach
  fun setUp() {
    // 準備
    fizzBuzz = FizzBuzz()
  }

  @Nested
  inner class convertメソッドは数を文字列に変換する {

    @Nested
    inner class _3か5の倍数でない場合はそのまま数を文字列に変換する {
      @Test
      fun _1を渡すと文字列1を返す() {
        // 実行 & 検証：ここから書く！ゴールから書くことで作業や処理がぶれない
        assertThat(fizzBuzz.convert(1)).isEqualTo("1")
      }

      @Test
      fun _2を渡すと文字列2を返す() {
        // 実行 & 検証：ここから書く！ゴールから書くことで作業や処理がぶれない
        assertThat(fizzBuzz.convert(2)).isEqualTo("2")
      }
    }

    @Nested
    inner class _3の倍数の時は数の代わりにFizzに変換する {
      @Test
      fun _3を渡すと文字列Fizzを返す() {
        // 実行 & 検証：ここから書く！ゴールから書くことで作業や処理がぶれない
        assertThat(fizzBuzz.convert(3)).isEqualTo("Fizz")
      }
    }

    @Nested
    inner class _5の倍数の時は数の代わりにBuzzに変換する {
      @Test
      fun _5を渡すと文字列Bizzを返す() {
        // 実行 & 検証：ここから書く！ゴールから書くことで作業や処理がぶれない
        assertThat(fizzBuzz.convert(5)).isEqualTo("Buzz")
      }
    }
  }
}