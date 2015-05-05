package fpinscala.datastructures

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.Checkers
import org.scalatest.exceptions.TestFailedException

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

object QuickCheckIntList extends QuickCheckList with List[Int]

@RunWith(classOf[JUnitRunner])
class ListSuite extends FunSuite with Checkers {
  def checkBogus(p: Prop) {
    var ok = false
    try {
      check(p)
    } catch {
      case e: TestFailedException =>
        ok = true
    }
    assert(ok, "A bogus list will NOT satisfy all properties. Try to find the bug!")
  }

  test("Binomial heap satisfies properties.") {
    check(new QuickCheckList with List[Int])
  }

}


abstract class QuickCheckList extends Properties("List") {
  
  lazy val genList: Gen[List[Int]] = for {
    n <- arbitrary[Int]  // Generate random n:Int to be inserted into list l:List[Int]
    l <- oneOf(const(Nil), genList)  // Generate list l:L to which random element n:Int will be added
  } yield Cons(n,l)  // return the list l updated with element n


}  
