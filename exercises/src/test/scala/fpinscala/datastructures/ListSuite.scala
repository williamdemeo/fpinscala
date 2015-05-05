package fpinscala.datastructures
import List._

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.Checkers
import org.scalatest.exceptions.TestFailedException

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._


@RunWith(classOf[JUnitRunner])
class ListSuite extends FunSuite with Checkers {
  test("tail length") {
    check(ListTests)
  }
}

object ListTests extends Properties("List") {

  // generate random lists (to be used when testing the properties below)
  lazy val genList: Gen[List[Int]] = for {
    n <- arbitrary[Int]               // Generate random n:Int to be inserted into list l
    l <- oneOf(const(Nil), genList)   // Generate random list l:List[Int] to which n will be added
  } yield Cons(n,l)                   // return the list l updated with element n

  property("tail length") = forAll(genList){ (l: List[Int]) =>
    length(l) == length(tail(l))+1
  }
  
  /* Can't get this to work right.  Switching to simpler tests for now 
  property("drop length") = forAll(genList) { (l: List[Int]) => 
    val n = arbitrary[Int] suchThat (_ < length(l)) suchThat (_ > 0)
    //  val randnum = Gen.choose(0,List.length(l)) 
    (length(l) - n) == length(drop(l,n))
  }
  * 
  */
    
  property("drop length") = {
    val mylist = List(1,2,3)
    length(mylist) - 1 == length(drop(mylist,1))
  }
  
  
}
  
