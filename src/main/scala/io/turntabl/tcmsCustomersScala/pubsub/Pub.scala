package io.turntabl.tcmsCustomersScala.pubsub

import redis.clients.jedis.Jedis

object Pub {
  def publis(channel: String, message: String): Unit = {
    val jedis = new Jedis(System.getenv("REDIS_URL"))
    jedis.publish(channel, message)
//    jedis.ping
    jedis.close()
  }
}
