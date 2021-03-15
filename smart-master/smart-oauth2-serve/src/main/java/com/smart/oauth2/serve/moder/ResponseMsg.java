package com.smart.oauth2.serve.moder;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description: Description
 * @ProjectName: spring-parentResourceContro
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
public class ResponseMsg implements Serializable {
    private String status;
    private String msg;
    private Object result;
}
