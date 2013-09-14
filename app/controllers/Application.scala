package controllers

import play.api._
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._

import models.Task

object Application extends Controller {
  
  def index = Action {
    //Ok(views.html.index("Your new application is ready."))
    //Ok("hello world!")
    Redirect(routes.Application.task)
  }


  val taskForm = Form(
    "label" -> nonEmptyText
  )

  def task = Action {
    Ok(views.html.index(Task.all(), taskForm))
  }

  def newTask = Action { implicit request =>
    taskForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(Task.all(), errors)),
      label => {
        Task.create(label)
        Redirect(routes.Application.task)
      }
    )
  }

  def deleteTask(id: Long) = Action {
    Task.delete(id)
    Redirect(routes.Application.task)
  }

}


