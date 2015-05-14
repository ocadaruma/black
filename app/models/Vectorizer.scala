package models

import org.apache.spark.mllib.linalg.{Vector => MLVector, Vectors}
import org.atilika.kuromoji.{Token, Tokenizer}
import org.jsoup.Jsoup
import scala.collection.JavaConversions._

object Vectorizer {
  val tokenizer = Tokenizer.builder().build()

  def remoteResourceToVector(url: String): MLVector = {
    val tokens = tokenizer.tokenize(Jsoup.connect(url).userAgent(Factory.userAgent).get().text()).collect {
      case t: Token if t.getPartOfSpeech.contains("名詞") => t.getSurfaceForm
    }

    println(tokens)

    val indices = for {
      word <- tokens
      index <- WordIndexer.index.get(word)
    } yield index
    val map = indices.groupBy(identity).mapValues(_.size.toDouble)
    Vectors.sparse(WordIndexer.index.size, map.toSeq)
  }
}
