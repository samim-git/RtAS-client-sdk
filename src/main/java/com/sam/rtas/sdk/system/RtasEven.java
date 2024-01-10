package com.sam.rtas.sdk.system;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RtasEven {
    int id;
    String topic;
    String content;
}
