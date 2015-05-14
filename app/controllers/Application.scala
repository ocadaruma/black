package controllers

import models.Predictor
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

object Application extends Controller {
  val urlForm = Form("url" -> text)

  def index = Action {
    Ok(views.html.index())
  }

  def result = Action { implicit request =>
    val url = urlForm.bindFromRequest().get
    val prediction = Predictor.predict(url)
    Ok(views.html.result(prediction))
  }
}
