package com.smart.db.po;

import com.smart.db.model.MplMenu;
import com.smart.db.model.MplRole;
import com.smart.db.model.MplUser;
import java.util.List;
import lombok.Data;

/**
 * @author lwq
 * @data 2019/8/16
 */
@Data
public class UserPo {

    private MplUser mplUser;
    private List<MplMenu> mplMenus;
    private MplRole mplRole;
}
