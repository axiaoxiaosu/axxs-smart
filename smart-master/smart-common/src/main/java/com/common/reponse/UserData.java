package com.common.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor

public class UserData {


    private String username;


    private String nickName;

    private String portrait;


    private String email;


    private Date lastTime;

}
