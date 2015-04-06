package models

import org.joda.time.LocalDate

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