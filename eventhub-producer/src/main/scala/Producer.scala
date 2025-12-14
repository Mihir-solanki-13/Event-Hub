import org.apache.kafka.clients.producer._
import java.util.Properties
import java.time.Instant

object Producer extends App {

  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

  val producer = new KafkaProducer[String, String](props)

  while (true) {
    val event =
      s"""{
         |  "eventType": "CLICK",
         |  "userId": "u1",
         |  "timestamp": "${Instant.now}"
         |}""".stripMargin

    producer.send(new ProducerRecord("events", event))
    println(s"Sent: $event")

    Thread.sleep(2000)
  }
}
