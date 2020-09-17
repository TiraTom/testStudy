package hoge.example.tdd2

class FizzBuzz {
  fun convert(value: Int): String {
    return when {
      (value % 3 == 0) -> "Fizz"
      (value % 5 == 0) -> "Buzz"
      else -> value.toString()
    }
  }

}