package cn.ucmed.general.shiro.Service;

import cn.ucmed.general.shiro.model.User;
import cn.ucmed.general.shiro.model.UserModel;

/**
 * Created by ucmed on 2016/3/29.
 */
public interface SecurityService {

    UserModel login(User user);
}
