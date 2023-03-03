package com.olubunmee.USSDApplication.service.ussd;

import com.olubunmee.USSDApplication.data.dto.UssdRequest;
import com.olubunmee.USSDApplication.data.model.Action;

public interface UssdService {
    String performUssdOperation(UssdRequest request);
}
