package hoge.example.tdd

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MoneyTest {

  @Test
  fun testMultiplication() {
    assertThat(Money.dollar(10)).isEqualTo(
      Money.dollar(
        5
      ).times(2)
    )
    assertThat(Money.dollar(10)).isNotEqualTo(
      Money.dollar(
        6
      ).times(2)
    )
    assertThat(Money.franc(10)).isEqualTo(
      Money.franc(
        5
      ).times(2)
    )
    assertThat(Money.franc(10)).isNotEqualTo(
      Money.franc(
        6
      ).times(2)
    )
  }

  @Test
  fun testEquality() {
    assertThat(Money.dollar(5)).isEqualTo(
      Money.dollar(
        5
      )
    )
    assertThat(Money.dollar(5)).isNotEqualTo(
      Money.dollar(
        6
      )
    )
    assertThat(Money.franc(5)).isNotEqualTo(
      Money.dollar(
        6
      )
    )
  }

  @Test
  fun testFrancMultiplication() {
    assertThat(Money.franc(10)).isEqualTo(
      Money.franc(
        5
      ).times(2)
    )
    assertThat(Money.franc(10)).isNotEqualTo(
      Money.franc(
        5
      ).times(3)
    )
  }

  @Test
  fun testSimpleAddition() {
    val sum: Expression = Money.dollar(
      5
    ).plus(Money.dollar(5))
    val reduced: Money = Bank().reduce(sum, "USD")
    assertThat(reduced).isEqualTo(Money.dollar(10))
  }

  @Test
  fun testReduceSum() {
    val sum: Expression = Sum(
      Money.dollar(5),
      Money.dollar(4)
    )
    val result: Money = Bank().reduce(sum, "USD")
    assertThat(Money.dollar(7)).isEqualTo(result)
  }

}