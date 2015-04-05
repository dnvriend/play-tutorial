package controllers

import play.api.mvc._
import spray.json._
import spray.json.DefaultJsonProtocol._

import scala.util.Random

object Clients extends Controller {

  case class Client(id: Int, name: String, address: String)

  implicit val jsonClientFormat = jsonFormat3(Client)

  val rnd = new Random()

  val names = Seq(
    "Beckie Leaman",
    "Barrett Kram",
    "Cassi Tiffany",
    "Sharen Miller",
    "Soon Kanagy",
    "Ernie Maziarz",
    "Melani Ciampa",
    "Kate Petrik",
    "Eulalia Perea",
    "Viviana Newquist",
    "Marisela Rolando",
    "Joslyn Tingey",
    "Chanelle House",
    "Shaniqua Gage",
    "Allison Hurlbert",
    "Lue Zuckerman",
    "Deanna Ippolito",
    "Bernardina Leveque",
    "Lakeesha Northcutt",
    "Dierdre Vigue"
  )

  val streets = Seq(
    "Railroad Street",
    "Ridge Street",
    "Grove Avenue",
    "Spring Street",
    "Valley View Road",
    "Orange Street",
    "Bank Street",
    "Briarwood Drive",
    "Cross Street",
    "Pheasant Run",
    "Madison Court",
    "8th Street West",
    "Sherman Street",
    "Cambridge Road",
    "Mechanic Street",
    "Park Place",
    "Lantern Lane",
    "Forest Street",
    "Main Street North",
    "Winding Way",
    "Garden Street",
    "Magnolia Court",
    "Columbia Street",
    "Jefferson Avenue",
    "Broadway",
    "Central Avenue",
    "Mill Street",
    "Water Street"
  )

  val clients = (1 to 30).map { i =>
    i -> Client(i, names(rnd.nextInt(names.size)), streets(rnd.nextInt(streets.size)))
  }.toMap[Int, Client]

  def all() = Action {
    Ok(
      clients
        .values
        .toList
        .sortWith(_.id < _.id)
        .toJson
        .prettyPrint
    ).as(JSON)
  }

  def getById(id: Int) = Action {
    clients
      .get(id)
      .map(c => Ok(c.toJson.prettyPrint).as(JSON))
      .getOrElse(NotFound)
  }
}