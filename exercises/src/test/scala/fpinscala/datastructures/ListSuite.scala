package fpinscala.datastructures

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._



abstract class QuickCheckList extends Properties("List") {
  
  lazy val genList: Gen[List[Int]] = for {
    n <- arbitrary[Int]  // Generate random n:Int to be inserted into list l:List[Int]
    l <- oneOf(const(Nil), genList)  // Generate list l:L to which random element n:Int will be added
  } yield Cons(n,l)  // return the list l updated with element n


}  
