package com.smart.db.po;

import com.smart.db.model.MenuData;
import com.smart.db.model.MplRole;
import com.smart.db.model.MplUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lwq
 * @data 2019/8/16
 */
@Data
public class UserPo implements Serializable {
    private MplUser mplUser;
    private List<MenuData> mplMenus;
    private MplRole mplRole;
}
