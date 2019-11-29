package io.turntabl.tcmsCustomersScala.models

case class CustomerTO(val customer_id: Int,
                 val customer_name: String,
                 val customer_email: String,
                 val customer_address: String,
                 val customer_telephone: String,
                 val customer_level: String)
{
  def this() = this(0, "","", "", "", "")
}


