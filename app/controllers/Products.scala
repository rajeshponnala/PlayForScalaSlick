package controllers

import play.api._
import play.api.mvc.{Action, Controller}
import play.api.libs.json.Json
import Tables._
import Tables.profile.simple._


object Prod extends Controller {
   def list = Action{
  	 var data= Database.forURL("jdbc:mysql://localhost:3306/inventory", driver = "scala.slick.driver.MySQLDriver",user="root",password="root") withSession {
       implicit session =>
      (for(product <- Products) yield  product.ean).list
      
    }
    Ok(Json.toJson(data))
  }
}

