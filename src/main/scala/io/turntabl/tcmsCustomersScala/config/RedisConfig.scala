package io.turntabl.tcmsCustomersScala.config

import redis.clients.jedis.{Jedis, JedisPubSub}

object RedisConfig {
  def redisConfiguration(): Unit = {
    val jedis = new Jedis(System.getenv("REDIS_URL"))
    try {
      val jedisPubSub = new JedisPubSub() {
        override def onMessage(channel: String, message: String): Unit = {
          System.out.println("Channel: " + channel + " Message: " + message)
        }

        override def onSubscribe(channel: String, subscribedSchannels: Int): Unit = {
          System.out.println("Subscribed to: " + channel)
        }
      }
//      jedis.ping
      jedis.subscribe(jedisPubSub, "customers", "customer", "new_customer", "update", "remove")
      jedis.close()
    } catch {
      case ex: Exception =>
        ex.printStackTrace()
    }
  }

}
