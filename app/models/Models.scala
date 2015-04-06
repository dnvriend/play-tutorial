package models

import org.joda.time.LocalDate

case class User(name: String, age: Int)

case class UserMoreFields(firstName: String,
                          lastName: String,
                          age: Int,
                          tasks: Long,
                          salary: BigDecimal,
                          birthDate: java.util.Date,
                          now: LocalDate,
                          email: String,
                          employed: Boolean,
                          married: Boolean,
                          optIn: Option[Boolean])

case class PillChoice(name: String, pill: String)

case class Address(street: String, city: String)

case class UserAddress(name: String, address: Address)
