package io.turntabl.tcmsCustomersScala.dao

import java.util.List

import io.turntabl.tcmsCustomersScala.models.CustomerTO

trait CustomerDAO {
  def addNewCustomer(customer: CustomerTO)

  def getAllCustomers

  def updateCustomerInfo(id: Integer, customer: CustomerTO)

  def searchForCustomerByID(id: Integer): List[CustomerTO]

  def searchForCustomerByName(name: String): List[CustomerTO]

  def searchForCustomerByLevel(level: String): List[CustomerTO]

  def deleteCustomer(id: Integer)

}
