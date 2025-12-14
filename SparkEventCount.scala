import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object SparkEventCount {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("EventHub-Spark-Streaming")
      .master("local[*]")
      .getOrCreate()

    spark.sparkContext.setLogLevel("WARN")

    val df = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "events")
      .option("startingOffsets", "latest")
      .load()

    val events = df.selectExpr("CAST(value AS STRING)")

    val counts = events
      .withColumn("timestamp", current_timestamp())
      .groupBy(window(col("timestamp"), "10 seconds"))
      .count()

    val query = counts.writeStream
      .outputMode("complete")
      .format("console")
      .option("truncate", "false")
      .start()

    query.awaitTermination()
  }
}
