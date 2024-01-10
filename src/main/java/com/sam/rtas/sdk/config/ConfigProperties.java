package com.sam.rtas.sdk.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.common.protocol.types.Field;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Builder
public class ConfigProperties {
    String url;
    String keySerializer;
    String valueSerializer;

    public static ConfigProperties getProperties() {
        String configServerUrl = "http://localhost:8888";
        String application = "kafka";
        String profile = "deployment";

        String configUrl = configServerUrl + "/config/" + application;
        HttpResponse<String> response = HttpRequestHelper.getRequest(configUrl);
        if (response.statusCode() == 200) {
            HashMap<String, Object> resMap = parseJsonResponse(response.body());
            List<HashMap<String, Object>> propertySources = (List<HashMap<String, Object>>) resMap.get("propertySources");
            for (HashMap<String, Object> source: propertySources) {
                String name = source.get("name").toString();
                String appName = getAppName(name);
                if (appName.equals("kafka")) {
                    HashMap<String, String> sourceMap = (HashMap<String, String>) source.get("source");
                    return ConfigProperties.builder()
                            .url(sourceMap.get("rtas.kafka.url"))
                            .keySerializer(sourceMap.get("rtas.kafka.keySerializerConfig"))
                            .valueSerializer(sourceMap.get("rtas.kafka.valueSerializerConfig"))
                            .build();
                }
            }
        }
        return null;
    }

    private static HashMap<String, Object> parseJsonResponse(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, HashMap.class);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON response", e);
        }
    }

    private static String getAppName(String name) {
        int startIndex = name.indexOf("application-") + "application-".length();
        int endIndex = name.indexOf(".yml");
        if (startIndex >= 0 && endIndex >= 0) {
            return name.substring(startIndex, endIndex);
        }
        return name;
    }
}
