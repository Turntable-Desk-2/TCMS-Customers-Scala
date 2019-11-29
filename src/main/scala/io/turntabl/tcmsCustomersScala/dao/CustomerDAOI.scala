package io.turntabl.tcmsCustomersScala.dao

import java.sql.ResultSet
import java.util.List

import io.swagger.annotations.{Api, ApiOperation}
import io.turntabl.tcmsCustomersScala.extras.DBVars
import io.turntabl.tcmsCustomersScala.models.CustomerTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.{BeanPropertyRowMapper, JdbcTemplate, RowMapper}
import org.springframework.web.bind.annotation._
import com.google.gson.Gson
import io.turntabl.tcmsCustomersScala.pubsub.Pub
import org.springframework.context.annotation.Bean
import org.json4s._
import org.json4s.native.Serialization

@Api
@RestController
class CustomerDAOI(@Autowired val template: JdbcTemplate ){
  implicit val format = Serialization.formats(NoTypeHints)

  @ApiOperation("Add a New Customer")
  @PostMapping(Array("/api/v1/customers"))
  def addNewCustomer(@RequestBody customer: CustomerTO): Unit = {
    //    template.update(DBVars.ADD_NEW_CUSTOMER, customer.customer_id, customer.customer_name, customer.customer_email, customer.customer_address, customer.customer_telephone, customer.customer_level)
    //    Pub.publis("new_customer", "New Customer: " + customer.getCustomer_name + " added")
  }

  @ApiOperation("Get all Customers")
  @GetMapping(Array("/api/v1/customers"))
  def getAllCustomers = {
      Serialization.write(this.template.query(DBVars.GET_CUSTOMERS, CustomerMapper))
//      Pub.publis("customers", "Getting all Customers")
    }


  @ApiOperation("Update a Customer Info")
  @PutMapping(Array("/api/v1/customers/{id}"))
  def updateCustomerInfo(@PathVariable("id") id: Integer, customer: CustomerTO): Unit = {
    //    val status = template.update(DBVars.UPDATE_CUSTOMER, customer.customer_id, customer.customer_name, customer.customer_email, customer.customer_address, customer.customer_telephone, customer.customer_level)
    //    Pub.publis("updater", "Customer: " + customer.getCustomer_id + " Updated")
  }

  @ApiOperation("Get For Customer by ID")
  @GetMapping(Array("/api/v1/customers/{id}"))
  def searchForCustomerByID(@PathVariable("id") id: Integer)= {
    Serialization.write(this.template.query(DBVars.GET_CUSTOMER_BY_ID, Array[AnyRef](id), CustomerMapper))
//    Pub.publis("customer", s"Getting a Customer with Id $id")
  }

  @ApiOperation("Search Customer By Name")
  @GetMapping(Array("/api/v1/customers/search"))
  def searchForCustomerByName(@RequestParam(value = "name") name: String) = {
    this.template.query(DBVars.GET_CUSTOMER_BY_NAME, Array[AnyRef](name + "%"), CustomerMapper)
//    Pub.publis("customers", "Getting Customers by name " + name)
  }

  @ApiOperation("Search Customer By Level")
  @GetMapping(Array("/api/v1/customers/searchLevel"))
  def searchForCustomerByLevel(@RequestParam(value = "level") level: String)= {
    this.template.query(DBVars.GET_CUSTOMER_BY_LEVEL, Array[AnyRef](level), CustomerMapper)
//    Pub.publis("customers", "Getting Customers with level " + level)
  }

  @ApiOperation("Delete a Customer")
  @DeleteMapping(Array("/api/v1/customers/{id}"))
  def deleteCustomer(@PathVariable("id") id: Integer): Unit = {
    this.template.update(DBVars.DELETE_CUSTOMER, id)
    //    Pub.publis("remove", "Removed a Customer with id " + id)
  }

}

@Bean
object CustomerMapper extends RowMapper[CustomerTO] {
  def mapRow(rs: ResultSet, rowNum: Int): CustomerTO = {
    val id =rs.getInt("customer_id")
    val name = rs.getString("customer_name")
    val email = rs.getString("customer_email")
    val address = rs.getString("customer_address")
    val phone = rs.getString("customer_telephone")
    val level = rs.getString("customer_level")

    CustomerTO(id,name,email,address, phone, level)
  }
}
