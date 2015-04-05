package controllers

import play.api.mvc._

object Results extends Controller {
  def html() = Action {
    Ok(<h1>Hello World</h1>).as(HTML)
  }

  def text() = Action {
    Ok(<h1>Hello World</h1>).as(TEXT)
  }

  def withCacheControl() = Action {
    Ok("WithCacheControl")
      .withHeaders(
        CACHE_CONTROL -> "max-age=3600"
      )
  }

  def withCookies() = Action {
    Ok("withCookies")
      .withCookies(
        Cookie("theme", "blue")
      )
  }
}