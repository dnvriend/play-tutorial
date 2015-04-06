package controllers

import play.api.mvc._

object Main extends Controller {

  def index() = Action {
    Ok(views.html.Main.index())
  }

  def sidebar() = Action {
    Ok(views.html.Main.sidebar())
  }
}
