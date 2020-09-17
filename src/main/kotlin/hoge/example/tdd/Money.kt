package hoge.example.tdd

open class Money(
  open val amount: Int,
  open val currency: String
) : Expression {
  open fun times(multiplier: Int): Money {
    return Money(amount * multiplier, currency)
  }

  companion object {
    fun dollar(amount: Int): Money {
      return Money(amount, "USD")
    }

    fun franc(amount: Int): Money {
      return Money(amount, "CHF")
    }
  }

  override fun equals(other: Any?): Boolean {
    return other is Money && this.amount == other.amount && this.currency == other.currency
  }

  fun plus(addend: Money): Expression {
    return Sum(this, addend)
  }
}