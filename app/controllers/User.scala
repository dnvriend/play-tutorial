package controllers

import java.util.TimeZone

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

object User extends Controller {
  import UserForms._

  def editUserFirst() = Action {
    Ok(views.html.first.userForm(userForm))
  }

  def postUserFirst() = Action { implicit request =>
    // the .bindFromRequest needs an implicit request to
    // bind the userData from the posted values
    userForm.bindFromRequest().fold(
      (formWithErrors: Form[forms.UserData]) => {
        BadRequest(views.html.first.userForm(formWithErrors))
      },
      (userData: forms.UserData) => {
        val newUser = models.User(userData.name, userData.age)
        Ok(views.html.first.userDetail(newUser))
      }
    )
  }

  def editUserSecond() = Action {
    Ok(views.html.second.userForm(userFormWithConstraints))
  }

  def postUserSecond() = Action { implicit request =>
    userFormWithConstraints.bindFromRequest().fold(
      formWithErrors => {
        BadRequest(views.html.second.userForm(formWithErrors))
      },
      userData => {
        val newUser = models.User(userData.name, userData.age)
        Ok(views.html.second.userDetail(newUser))
      }
    )
  }

  def editUserThird() = Action {
    Ok(views.html.third.userForm(userFormWithMoreFields))
  }
  def postUserThird() = Action { implicit request =>
    userFormWithMoreFields.bindFromRequest().fold(
      formWithErrors => {
        BadRequest(views.html.third.userForm(formWithErrors))
      },
      (ud: forms.UserDataMoreFields) => {
        val newUser = models.UserMoreFields(
          ud.firstName,
          ud.lastName,
          ud.age,
          ud.tasks,
          ud.salary,
          ud.birthDate,
          ud.now,
          ud.email,
          ud.employed,
          ud.married,
          ud.optIn
        )
        Ok(views.html.third.userDetail(newUser))
      }
    )
  }
}

object UserForms {
  val userForm: Form[forms.UserData] = Form(
    mapping (
      "name" -> text,
      "age" -> number
    )(forms.UserData.apply)(forms.UserData.unapply)
  )

  val userFormWithConstraints: Form[forms.UserData] = Form(
    mapping (
      "name" -> nonEmptyText,
      "age" -> number(min = 0, max = 120)
    )(forms.UserData.apply)(forms.UserData.unapply)
  )

  val userFormWithMoreFields: Form[forms.UserDataMoreFields] = Form(
      mapping(
        "firstName" -> nonEmptyText(maxLength = 50),
        "lastName" -> text(minLength = 0, maxLength = 50),
        "age" -> number(min = 0, max = 120),
        "tasks" -> longNumber(min = 0),
        "salary" -> bigDecimal(precision = 10, scale = 2),
        "birthDate" -> date(pattern = "yyyy-MM-dd"),
        "now" -> jodaLocalDate(pattern = "yyyy-MM-dd"),
        "email" -> email,
        "employed" -> boolean,
        "married" -> checked(msg = "should be checked"),
        "optIn" -> optional(boolean)
      )(forms.UserDataMoreFields.apply)(forms.UserDataMoreFields.unapply)
    )
}