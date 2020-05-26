package com.scala.flink

import org.apache.flink.api.scala.ExecutionEnvironment

object BatchWordCount {
  def main(args: Array[String]): Unit = {
    //1.初始化flink的环境
    val env:ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment

    //2.导入隐式转换，建议写在这里，可以防止IDEA代码提示出错的问题
    import org.apache.flink.api.scala._

    //3.读取数据
    val dataURL = getClass.getResource("./word-count.csv")//使用相对路径来得到完整的文件路径
    val data:DataSet[String] = env.readTextFile(dataURL.getPath)

    //4.计算
    data.flatMap(_.split(" "))
      .map((_,1))
      .groupBy(0)
      .sum(1)
      .print() // 5.输出
  }
}
