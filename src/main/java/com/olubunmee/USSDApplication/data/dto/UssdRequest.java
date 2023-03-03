package com.olubunmee.USSDApplication.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.olubunmee.USSDApplication.data.model.Action;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@JsonIgnoreProperties({"action","isTransaction"})
public class UssdRequest {
    private String phone;
    private Action action;
    private String pin;
    private String command;
    private boolean isTransaction;
}
