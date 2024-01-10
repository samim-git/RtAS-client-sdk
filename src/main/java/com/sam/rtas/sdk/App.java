package com.sam.rtas.sdk;

import com.sam.rtas.sdk.system.RtasEven;
import com.sam.rtas.sdk.system.ClientConfig;
import com.sam.rtas.sdk.system.EventPublisher;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        EventPublisher eventPublisher = new EventPublisher();
        eventPublisher.publish(ClientConfig.builder()
                        .clientId("test_client_id")
                        .clientName("test_client_name")
                        .clientToken("test_client_token").build(),
                RtasEven.builder()
                        .content("event content")
                        .id(2)
                        .topic("test_event_topic")
                        .build());
    }
}
