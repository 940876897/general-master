package cn.ucmed.general.common.constants;

import java.util.Arrays;
import java.util.List;

public class GlobalConstants {


    public static final String HTTP_PREFIX = "http://";

    public static final String SESSION_KEY = "admin.session";

    /**
     * 添加用户时的初始化密码
     */
    public static final String INIT_PSW = "88851766";

    public static final Integer RETURN_SUCCESS_CODE = 0;

    public static final String RETURN_SUCCESS_INFO = "success";


    /**
     * 版本管理 超级管理员用户主键
     */
    public static final String VERSION_SUPER_USER_ID = "ffd42da5-b359-11e5-b368-507b9d24b9d1";

    /**
     * 版本管理 超级管理员角色主键
     */
    public static final String VERSION_SUPER_USER_ROLE_ID = "d6c579a0-b99d-11e5-a6af-0050569b08a8";

    /**
     * RUBIK-X 超级管理员用户主键
     */
    public static final String RX_SUPER_USER_ID = "91680d1a-5b8e-45af-8bcd-c16d740d85db";


    /**
     * RUBIK-X 超级管理员角色主键
     */
    public static final String RX_SUPER_USER_ROLE_ID = "4102db92-fba2-11e5-9448-507b9d24b9d1";


    /**
     * 统一平台超管主键
     */
    public static final String PLATFORM_SUPER_USER_ID = "1c333055-7fc1-4056-bf28-561838f8ac6d";
    public static final String PLATFORM_SUPER_USER_ID_PERPAR = "fe9209cd-687f-4539-9ea6-ce8b61b62f02";

    /**
     * 同一平台角色主键
     */
    public static final String PLATFORM_SUPER_ROLE_ID = "40cc2a46-18cf-4de4-9486-fb192266c503";

    public static final List<String> SUPER_USERS =
            Arrays.asList(VERSION_SUPER_USER_ID,
                    RX_SUPER_USER_ID,PLATFORM_SUPER_USER_ID,PLATFORM_SUPER_USER_ID_PERPAR);

    public static final List<String> SUPER_ROLES =
            Arrays.asList(VERSION_SUPER_USER_ROLE_ID,
                    RX_SUPER_USER_ROLE_ID,PLATFORM_SUPER_ROLE_ID);

    private GlobalConstants() {

    }
}
