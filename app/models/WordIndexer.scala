package models

import java.io.File

import org.apache.commons.io.FileUtils
import org.atilika.kuromoji.{Token, Tokenizer}
import org.jsoup.Jsoup
import scala.collection.JavaConversions._
import scala.io.Source

object WordIndexer {
  val tokenizer = Tokenizer.builder().build()
  val allUrlsPath = Factory.config.getString("all_urls_path")

  lazy val index: Map[String, Int] = {
    Source.fromFile(Factory.config.getString("word_indices"))
      .getLines()
      .toList
      .zipWithIndex
      .toMap
  }

  def createIndex(): Unit = {
    val urls = Source.fromFile(allUrlsPath).getLines().toList
    val words = urls.map { url =>
      val doc = Jsoup.connect(url).userAgent(Factory.userAgent).get()
      val tokens = tokenizer.tokenize(doc.text())
      tokens.collect {
        case t: Token if t.getPartOfSpeech.contains("名詞") => t.getSurfaceForm
      }
    }.flatten
    FileUtils.writeStringToFile(new File(Factory.config.getString("word_indices")),
      words.toSeq.distinct.mkString("\n"))
  }
}
