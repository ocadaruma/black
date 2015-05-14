package models

import org.apache.spark.mllib.classification.NaiveBayesModel

sealed trait Prediction
case object Black extends Prediction
case object White extends Prediction

object Predictor {
  val sc = Factory.sparkContext
  val model = NaiveBayesModel.load(sc, Factory.config.getString("naive_bayse_model"))

  def predict(url: String): Prediction = {
    val vec = Vectorizer.remoteResourceToVector(url)
    if (model.predict(vec) == 1.0) Black else White
  }
}
