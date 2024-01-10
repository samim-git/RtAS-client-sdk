package com.sam.rtas.sdk.config;

import org.apache.kafka.common.protocol.types.Field;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class HttpRequestHelper {
    public static HttpResponse<String> getRequest(String url) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    //                .header()
                    //                .header()
                    .GET()
                    .build();

            return getHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());

        } catch (URISyntaxException exception) {} catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private static HttpClient getHttpClient() {
        return HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .followRedirects(HttpClient.Redirect.NEVER)
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }
}
