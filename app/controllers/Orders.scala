package controllers

import domain.{Order, Customer}
import play.api._
import play.api.mvc._

object Orders extends Controller {

  def orders() = Action {
    Ok(views.html.Orders.orders
      (
        Customer("John"),
        Seq(Order("book"), Order("dvd"), Order("game"))
      )
    )
  }

  def empty() = Action {
    Ok(views.html.Orders.orders
      (
        Customer("John"),
        Nil
      )
    )
  }
}