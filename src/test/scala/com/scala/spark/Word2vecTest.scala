package com.scala.spark




object Word2vecTest {
  def main(args: Array[String]): Unit = {
    import org.apache.spark.sql.SparkSession
//    import spark.implicits._
    import org.apache.spark.ml.feature.Word2Vec
    val spark = SparkSession.builder().
      master("local").
      appName("my App Name").
      getOrCreate()
    val documentDF = spark.createDataFrame(Seq("Hi I heard about Spark".split(" "), "I wish Java could use case classes".split(" "), "Logistic regression models are neat".split(" ")).map(Tuple1.apply)).toDF("text")
    val word2Vec = new Word2Vec().setInputCol("text").setOutputCol("result").setVectorSize(3).setMinCount(0)
    val model = word2Vec.fit(documentDF)
    val result = model.transform(documentDF)
    result.select("result").take(3).foreach(println)

  }
}
