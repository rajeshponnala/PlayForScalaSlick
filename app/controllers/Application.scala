package controllers

import play.api._
import play.api.mvc._
import scala.slick.codegen.SourceCodeGenerator
import Tables._
import Tables.profile.simple._

object Application extends Controller {

  def index = Action {
     
  var x=""
  //Database.forURL("jdbc:mysql://localhost:3306/inventory", driver = "scala.slick.driver.MySQLDriver",user="root",password="root") withSession {
 // implicit session =>
  // <- write queries here
 // var list =for(product <- Products) yield  product
 // list foreach { product =>
   //  x+=","+product.ean
 // }
  Ok(views.html.index())
  }
 }
