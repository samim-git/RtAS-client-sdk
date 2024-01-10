package com.sam.rtas.sdk.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import java.util.Properties;


public class KafkaConfig {
    public static Properties getProperties() {
        ConfigProperties configProperties = ConfigProperties.getProperties();
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, configProperties.url);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, configProperties.keySerializer);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, configProperties.valueSerializer);
        return properties;
    }
}
