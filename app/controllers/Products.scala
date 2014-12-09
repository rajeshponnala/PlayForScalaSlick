package controllers

import play.api._
import play.api.mvc.{Action, Controller}
import play.api.libs.json._
import Tables._
import Tables.profile.simple._
import play.api.libs.functional.syntax._


object Prod extends Controller {
  //implicit val locationWrites: Writes[ProductsRow] = ((JsPath \ "ean").write[Long]and (JsPath \ "name").write[String]and  (JsPath \ "description").write[String])(unlift(ProductsRow.unapply))
 implicit val productWrites = new Writes[ProductsRow] {
  def writes(p: ProductsRow) = Json.obj(
    "ean" -> p.ean,
    "name" -> p.name,
    "description"->p.description
  )
}

implicit val productReads: Reads[ProductsRow] = (
(JsPath \ "ean").read[Long] and
(JsPath \ "name").read[String] and
(JsPath \ "description").read[String]
)(ProductsRow.apply _)


   
 def list = Action{
  	 var data= Database.forURL("jdbc:mysql://localhost:3307/inventory", driver = "scala.slick.driver.MySQLDriver",user="root",password="root") withSession {
       implicit session =>
      //(for(product <- Products) yield  product.ean).list
       Products.list
    }
    Ok(Json.toJson(data))
    
  }

  def details(ean: Long) = Action{
    var product= Database.forURL("jdbc:mysql://localhost:3306/inventory", driver = "scala.slick.driver.MySQLDriver",user="root",password="root") withSession {
       implicit session =>
      //(for(product <- Products) yield  product.ean).list
       Products.filter(p=>p.ean===ean).firstOption  
    }
    product.map{p=> Ok(Json.toJson(p))}.getOrElse(NotFound)
    
  }

  def save(ean: Long)=Action(parse.json){ request=>
    val productJson=request.body
    val product=productJson.as[ProductsRow]
     var data= Database.forURL("jdbc:mysql://localhost:3306/inventory", driver = "scala.slick.driver.MySQLDriver",user="root",password="root") withSession {
       implicit session =>
      //(for(product <- Products) yield  product.ean).list
       Products.filter(p=>p.ean===ean).update(product)
    }
   if(data==1) Ok("Saved") else BadRequest("Product not found")
 }
  
}

