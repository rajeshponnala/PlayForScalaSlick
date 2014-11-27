package 
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = scala.slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: scala.slick.driver.JdbcProfile
  import profile.simple._
  import scala.slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import scala.slick.jdbc.{GetResult => GR}
  
  /** DDL for all tables. Call .create to execute. */
  lazy val ddl = Products.ddl
  
  /** Entity class storing rows of table Products
   *  @param ean Database column ean DBType(BIGINT), PrimaryKey
   *  @param name Database column name DBType(VARCHAR), Length(100,true)
   *  @param description Database column description DBType(VARCHAR), Length(500,true) */
  case class ProductsRow(ean: Long, name: String, description: String)
  /** GetResult implicit for fetching ProductsRow objects using plain SQL queries */
  implicit def GetResultProductsRow(implicit e0: GR[Long], e1: GR[String]): GR[ProductsRow] = GR{
    prs => import prs._
    ProductsRow.tupled((<<[Long], <<[String], <<[String]))
  }
  /** Table description of table products. Objects of this class serve as prototypes for rows in queries. */
  class Products(_tableTag: Tag) extends Table[ProductsRow](_tableTag, "products") {
    def * = (ean, name, description) <> (ProductsRow.tupled, ProductsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (ean.?, name.?, description.?).shaped.<>({r=>import r._; _1.map(_=> ProductsRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column ean DBType(BIGINT), PrimaryKey */
    val ean: Column[Long] = column[Long]("ean", O.PrimaryKey)
    /** Database column name DBType(VARCHAR), Length(100,true) */
    val name: Column[String] = column[String]("name", O.Length(100,varying=true))
    /** Database column description DBType(VARCHAR), Length(500,true) */
    val description: Column[String] = column[String]("description", O.Length(500,varying=true))
  }
  /** Collection-like TableQuery object for table Products */
  lazy val Products = new TableQuery(tag => new Products(tag))
}