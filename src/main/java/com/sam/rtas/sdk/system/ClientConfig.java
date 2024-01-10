package com.sam.rtas.sdk.system;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClientConfig {
    String clientName;
    String clientId;
    String clientToken;
}
