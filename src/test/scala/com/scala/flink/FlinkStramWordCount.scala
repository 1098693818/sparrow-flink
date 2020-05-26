package com.scala.flink

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object FlinkStramWordCount {
  def main(args: Array[String]): Unit = {

    //1.初始化Flink的Streaming（流计算）上下文执行环境
    val streamEnv: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    // 修改并行度
    streamEnv.setParallelism(20)

    //2.导入隐式转换，建议写在这里，可以防止IDEA代码提示出错的问题
    import org.apache.flink.streaming.api.scala._

    //3.读取数据,读取sock流中的数据 nc -lk 9000
    val stream: DataStream[String] = streamEnv.socketTextStream("116.62.46.107",9000)

    //4.转换计算
    val result: DataStream[(String,Int)] = stream.flatMap(_.split(","))
          .map((_,1))
          .keyBy(0) //分组算子：0或者1代表前面的DataStream[二元组]下标，0：代表单词，1：代表单词的出现次数
          .sum(1) //聚合累加算子

    //5.打印结果到控制台
    result.print("结果")

    //启动流式处理，如果没有该行代码上面的程序不会运行
    streamEnv.execute("wordcount")
  }
}
