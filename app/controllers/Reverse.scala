package controllers

import play.api.mvc._

object Reverse extends Controller {
  def toHelloWorld() = Action {
    Redirect(routes.StaticRouting.hello())
  }
}