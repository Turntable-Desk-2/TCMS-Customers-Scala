package io.turntabl.tcmsCustomersScala.extras

object DBVars {
  val ADD_NEW_CUSTOMER: String = "insert into customers " + "(customer_id, customer_name, customer_email, customer_address, " + "customer_telephone, customer_level) values (?, ?, ?, ?, ?, ?)"
  val GET_CUSTOMERS = "select * from customers"
  val GET_CUSTOMER_BY_ID = "select * from customers where customer_id = ? "
  val GET_CUSTOMER_BY_NAME = "select * from customers where customer_name like ? "
  val GET_CUSTOMER_BY_LEVEL = "select * from customers where customer_level = ? "
  val DELETE_CUSTOMER = "delete from customers where customer_id = ? "
  val UPDATE_CUSTOMER: String = "update customers set customer_name = ?, customer_email = ?, customer_address = ?, customer_telephone = ?, customer_level=? " + " where customer_id = ? "
}
