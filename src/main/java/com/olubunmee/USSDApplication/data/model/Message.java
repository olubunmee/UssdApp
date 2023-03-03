package com.olubunmee.USSDApplication.data.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    private String from;
    private List<Destination> destinations = new ArrayList<>();
    private String text;
}
