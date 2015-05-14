package models

import com.typesafe.config.ConfigFactory
import org.apache.spark.SparkContext

object Factory {
  lazy val sparkContext = new SparkContext("local", "black")
  lazy val config = ConfigFactory.load()
  val userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36"
}
