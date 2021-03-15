package com.smart.demo.mode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cat implements Serializable {
    private String name;
    @JsonProperty("iTime")
    @JsonIgnore
    private Date iTime;
    private Integer age;
    @JsonIgnore
    private String password;
}
