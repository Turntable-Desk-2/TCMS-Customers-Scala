package io.turntabl.tcmsCustomersScala

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2
import io.turntabl.tcmsCustomersScala.config.RedisConfig

@EnableSwagger2
@SpringBootApplication
class TcmsCustomersApplication

object SampleWebApplication extends App {
  SpringApplication.run(classOf[TcmsCustomersApplication]);
  RedisConfig.redisConfiguration()
}