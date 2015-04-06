package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

object User extends Controller {

  val userForm: Form[forms.UserData] = Form(
    mapping(
      "name" -> text,
      "age" -> number
    )(forms.UserData.apply)(forms.UserData.unapply)
  )

  def editUser() = Action {
    Ok(views.html.userForm(userForm))
  }

  def postUser() = Action { implicit request =>
    // the .bindFromRequest needs an implicit request to
    // bind the userData from the posted values
    userForm.bindFromRequest().fold(
      formWithErrors => {
        BadRequest(views.html.userForm(formWithErrors))
      },
      userData => {
        val newUser = models.User(userData.name, userData.age)
        Ok(views.html.userDetail(newUser))
      }
    )
  }
}