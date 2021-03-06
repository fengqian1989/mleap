package org.apache.spark.ml.parity.feature

import org.apache.spark.ml.feature.{VectorAssembler, VectorSlicer}
import org.apache.spark.ml.{Pipeline, Transformer}
import org.apache.spark.ml.parity.SparkParityBase
import org.apache.spark.sql._

/**
  * Created by hollinwilkins on 12/28/16.
  */
class VectorSlicerParitySpec extends SparkParityBase {
  override val dataset: DataFrame = baseDataset.select("dti", "loan_amount")
  override val sparkTransformer: Transformer = new Pipeline().setStages(Array(new VectorAssembler().
    setInputCols(Array("dti", "loan_amount")).
    setOutputCol("features"),
    new VectorSlicer().
      setIndices(Array(1)).
      setInputCol("features").
      setOutputCol("scaled_features"))).fit(dataset)
}
