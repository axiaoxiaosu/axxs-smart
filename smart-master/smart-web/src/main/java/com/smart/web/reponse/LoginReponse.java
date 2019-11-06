package com.smart.web.reponse;

import com.smart.db.model.MplMenu;
import com.smart.db.model.MplUser;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author lwq
 * @data 2019/8/22
 */
@EqualsAndHashCode
@Accessors(chain = true)
@Data
public class LoginReponse {

    private MplUser user;
    private List<MplMenu> menu;
    private String token;

}
