package com.sam.rtas.sdk.system;

import com.sam.rtas.sdk.config.Conf;
import com.sam.rtas.sdk.config.KafkaConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
public class EventPublisher {
    @Conf("database.username")
    String name;
    public void publish(ClientConfig clientConfig, RtasEven even) {
        System.out.println("this is the name: "+name);
        Properties properties = KafkaConfig.getProperties();
        Producer<String, String> producer = new KafkaProducer<>(properties);

        ProducerRecord<String, String> record = new ProducerRecord<>(even.topic, clientConfig.getClientId(), even.content);
        producer.send(record);
        producer.close();

        System.out.println("----------Event published");
    }
}
