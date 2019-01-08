package cn.xiaows.logs.collect.util;

import cn.xiaows.logs.common.AppLogger;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 *
 *
 * @author: xiaows
 * @create: 2019-01-08 14:47
 * @version: v1.0
 */
@Slf4j
public class KafkaUtils {

    /**
     * 向 kafka 发送消息
     *  @param topic
     * @param logs*/
    public static void sendLog(String topic, List<AppLogger> logs) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "kafka-1:9092,kafka-2:9092,kafka-3:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        sendSingleMessage(producer, topic, logs);

        producer.close();
    }

    /**
     * 向同一类型的 topic 发送消息
     * @param producer
     * @param topic
     * @param logs
     */
    private static void sendSingleMessage(Producer<String, String> producer, String topic, List<AppLogger> logs) {
        for (AppLogger logger : logs) {
            String json = JSONObject.toJSONString(logger);
            // System.out.println(json);
            long start = System.currentTimeMillis();
            producer.send(new ProducerRecord<>(topic, json), (metadata, exeception) -> {
                //消息发送成功之后收到了Kafka服务端发来的ACK确认消息之后,就回调下面的方法
                //metadata保存着生产者发送过来的消息的元数据,如果消息的发送过程中出现了异常,则改参数的值为null
                if (null != metadata) {
                    log.info("消息发送的分区是: {}, 耗时: {}ms.", metadata.partition(), start - System.currentTimeMillis());
                } else {
                    log.error(exeception.getMessage());
                }
            });
        }
    }

    public static void reveive(String topic) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "kafka-1:9092,kafka-2:9092,kafka-3:9092");
        props.put("group.id", "g2");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ZERO);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
    }

    public static void main(String[] args) {
        reveive("user");
    }
}
