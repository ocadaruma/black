package models

import org.apache.spark.mllib.classification.NaiveBayes
import org.apache.spark.mllib.regression.LabeledPoint

object Trainer {
  val sc = Factory.sparkContext

  def train(): Unit = {
    val whiteFile = Factory.config.getString("white_urls_path")
    val blackFile = Factory.config.getString("black_urls_path")
    val saveTo = Factory.config.getString("naive_bayse_model")

    val whiteData = sc.textFile(whiteFile).map { url =>
      LabeledPoint(-1, Vectorizer.remoteResourceToVector(url))
    }

    val blackData = sc.textFile(blackFile).map { url =>
      LabeledPoint(1, Vectorizer.remoteResourceToVector(url))
    }

    val model = NaiveBayes.train(whiteData ++ blackData)
    model.save(sc, saveTo)
  }
}
