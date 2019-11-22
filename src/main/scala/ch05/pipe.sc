/* In theory this should work in some older version of Scala.
 * The JSON library was removed from the std-lib at some point. */

import scala.util.parsing.json._

object Pipeline {

  implicit class toPiped[V](value: V) {
    def |>[R](f: V => R) = f(value)
  }

}


import Pipeline._
import ch05.payroll.Employee
import ch05.payroll.Payroll._


val e = Employee("Buck Trends", 100000.0F, 0.25F, 200F, 0.10F, 0.05F)
val pay = start(e) |>
  minus401k |>
  minusInsurance |>
  minusTax |>
  minusFinalDeductions

val twoWeekGross = e.annualSalary / 26.0F
val twoWeekNet = pay.netPay
val percent = (twoWeekNet / twoWeekGross) * 100
println(s"For ${e.name}, the gross vs net pay is: ")
println(f" $$${twoWeekGross}%.2f vs $$${twoWeekNet}%.2f")

