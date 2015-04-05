package controllers

import play.api._
import play.api.libs.iteratee.Enumerator
import play.api.mvc._

object StaticRouting extends Controller {
  def hello() = Action {
    Ok("Hello world")
  }

  def withRequest() = Action { request =>
    Ok(s"Got request: [$request]")
  }

  def withImplicitRequest() = Action { implicit request =>
    Ok(s"Got implicit request: [$request]")
  }

  def withBodyParser() = Action(parse.json) { implicit request =>
    Ok(s"Got implicit request with bodyparser: [$request]")
  }

  def explicitResult() = Action {
    Result(
      header = ResponseHeader(200, Map(CONTENT_TYPE -> "text/plain")),
      body = Enumerator("Hello World!".getBytes)
    )
  }

  def notFound() = Action {
    NotFound
  }

  def pageNotFound() = Action {
    NotFound(<h1>Page not found</h1>)
  }

  def badRequest() = Action {
    BadRequest(<h1>Bad request</h1>)
  }

  def oops() = Action {
    InternalServerError("Oops")
  }

  def anyStatus() = Action {
    Status(488)("Strange response type")
  }

  def redirect() = Action {
    Redirect("http://www.google.nl")
  }

  def movedPermanently() = Action {
    Redirect("http://www.google.nl", MOVED_PERMANENTLY)
  }

  def dummy() = play.mvc.Results.TODO
}