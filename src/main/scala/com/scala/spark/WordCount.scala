//package com.scala.spark
//
//import java.io.File
//import scala.io.Source
//
//object WordCount {
//  def main(args: Array[String]): Unit = {
//    val dirfile = new File("/Users/python/Desktop/sparrow-flink/src/files")
//    val files = dirfile.listFiles()
//    for(file <- files) println(file)
//    val listFiles = files.toList
//    val wordsMap = scala.collection.mutable.Map[String,Int]()
//    listFiles.foreach(file => Source.
//                              fromFile(file)
//                              .getLines()
//                              .foreach(
//                                line => line.split(" ")
//                                .foreach(
//                                  word => {
//                                    if (wordsMap.contains(word)){
//                                      wordsMap(word) += 1
//                                    }else{
//                                      wordsMap += (word->1)
//                                    }
//                                  }
//                                )
//                              )
//    )
//    println(wordsMap)
//    for((key,value)<-wordsMap) println(key+": "+value)
//  }
//}

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object WordCount {
  def main(args: Array[String]) {
    val inputFile =  "file:///Users/python/Desktop/sparrow-flink/src/files"
    val conf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val textFile = sc.textFile(inputFile)
    val wordCount = textFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey((a, b) => a + b)
    wordCount.foreach(println)
  }
}
