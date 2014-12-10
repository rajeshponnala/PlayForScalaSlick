package models

import play.api.Play._
import Tables.profile.simple._

object utils  {
	def conn ={
		val config= current.configuration
		 Database.forURL(config.getString("db.default.url").get,driver=config.getString("db.default.slick.driver").get,user="root",password="root")
	}

}