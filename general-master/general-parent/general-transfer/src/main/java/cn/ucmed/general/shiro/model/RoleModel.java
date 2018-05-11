package cn.ucmed.general.shiro.model;

import java.util.List;
import java.io.Serializable;

/**
 * Created by ucmed on 2015/12/31.
 */
public class RoleModel extends Role implements Serializable {

    private List<User> userList;

    private List<String> permissionList;

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }

    public List<String> getPermissionList() {
        return permissionList;
    }

}
