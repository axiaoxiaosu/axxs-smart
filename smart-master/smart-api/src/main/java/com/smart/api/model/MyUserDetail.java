package com.smart.api.model;

import com.smart.db.model.MenuData;
import com.smart.db.model.MplRole;
import com.smart.db.model.MplUser;
import com.smart.db.po.UserPo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author lwq
 */
@NoArgsConstructor
@Data
public class MyUserDetail implements UserDetails {

    private MplUser user;
    private List<MenuData> mplMenu;
    private MplRole mplRole;

    public MyUserDetail(UserPo userPo) {
        this.user = userPo.getMplUser();
        this.mplRole = userPo.getMplRole();
        this.mplMenu = userPo.getMplMenus();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public MplRole getMplRole() {
        return this.mplRole;
    }

    public MplUser getUser() {
        return this.user;
    }

    /**
     * 用户账户是否过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus() == 0;
    }

    /**
     * 用户凭证是否过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否有效
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
