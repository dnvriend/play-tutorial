package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future

object Application extends Controller {
  import Forms._

  def editPrime() = Action {
    val filledForm = primeForm.fill(50000)
    Ok(views.html.primeForm(filledForm))
  }

  def showPrime() = Action.async { implicit request =>
    primeForm.bindFromRequest().fold(
      (formWithErrors: Form[Int]) => {
        Future(BadRequest(views.html.primeForm(formWithErrors)))
      },
      (max: Int) => {
        Prime.primesUnder(max).map { xs =>
          Ok(views.html.showPrime(max, xs))
        }
      }
    )
  }
}

object Forms {
  val primeForm: Form[Int] = Form(
    single(
      "max" -> number(min = 2, max = 200000)
    )
  )
}