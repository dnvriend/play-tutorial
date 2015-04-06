package controllers

import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

/**
 * Shamelessly copied from:
 * <a href="http://stackoverflow.com/questions/9711785/find-prime-numbers-using-scala-help-me-to-improve">stackoverflow:</a>
 */
object Prime {

  def primesUnder(n: Int): Future[(List[Int], Long)] = Future {
    require(n >= 2)
    val start = System.currentTimeMillis()

    def rec(i: Int, primes: List[Int]): List[Int] = {
      if (i >= n) primes
      else if (prime(i, primes)) rec(i + 1, i :: primes)
      else rec(i + 1, primes)
    }

    (rec(2, List()).reverse, System.currentTimeMillis() - start)
  }

  def prime(num: Int, factors: List[Int]): Boolean = factors.forall(num % _ != 0)

}
