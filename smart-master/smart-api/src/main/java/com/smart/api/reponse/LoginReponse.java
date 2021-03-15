package com.smart.api.reponse;

import com.common.reponse.UserData;
import com.smart.db.model.MenuData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author lwq
 * @data 2019/8/22
 */
@EqualsAndHashCode
@Accessors(chain = true)
@Data
public class LoginReponse extends CorsResponse {

    private UserData user;
    private List<MenuData> menu;
    private String token;
}
