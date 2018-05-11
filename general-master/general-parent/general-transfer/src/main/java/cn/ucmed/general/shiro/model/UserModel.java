package cn.ucmed.general.shiro.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by ucmed on 2015/12/31.
 */
public class UserModel extends User implements Serializable {

    private String oldPassword;

    private Set<String> roles;

    private Set<String> permissions;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }


}
