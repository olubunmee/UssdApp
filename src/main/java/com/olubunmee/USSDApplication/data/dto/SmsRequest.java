package com.olubunmee.USSDApplication.data.dto;

import com.olubunmee.USSDApplication.data.model.Messages;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsRequest {
    private List<Messages> messages = new ArrayList<>();
}
