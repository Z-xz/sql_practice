// Purpose: learn to convert unix time to timestamp (and vice versa)

import spark.sqlContext.implicits._

//Convert Timestamp to Unix timestamp
val inputDF = (
    Seq(("2019-07-01 12:01:19.000",
          "07-01-2019 12:01:19.000", 
          "07-01-2019"))
    .toDF("timestamp_1", "timestamp_2", "timestamp_3")
)
inputDF.printSchema()
inputDF.show(false)


//Convert timestamp to unix timestamp
  val df = inputDF.select(
      unix_timestamp(col("timestamp_1")).as("timestamp_1"),
      unix_timestamp(col("timestamp_2"),
                    "MM-dd-yyyy HH:mm:ss").as("timestamp_2"),
      unix_timestamp(col("timestamp_3"),"MM-dd-yyyy").as("timestamp_3"),
      unix_timestamp().as("timestamp_4")
  )
df.printSchema()
df.show(false)


// Convert Unix timestamp to timestamp
val df2 = df.select(
  from_unixtime(col("timestamp_1")).as("timestamp_1"),
  from_unixtime(col("timestamp_2"),"MM-dd-yyyy HH:mm:ss").as("timestamp_2"),
  from_unixtime(col("timestamp_3"),"yyyy-MM-dd").as("timestamp_3"),
  from_unixtime(col("timestamp_4")).as("timestamp_4")
)
df2.printSchema()
df2.show(false)
