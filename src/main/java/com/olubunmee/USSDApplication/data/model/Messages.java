package com.olubunmee.USSDApplication.data.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Messages {
    private List<Destination> destinations;
    private String from;
    private String text;
}
