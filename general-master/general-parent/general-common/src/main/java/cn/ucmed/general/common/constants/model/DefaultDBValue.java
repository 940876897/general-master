package cn.ucmed.general.common.constants.model;

public class DefaultDBValue {

    /**
     * 是应用
     */
    public static final String IS_APP = "0";

    /**
     * 是资源
     */
    public static final String IS_ZIP = "1";

    /**
     * 应用和资源
     */
    public static final String APP_ZIP = "2";

    /**
     * 已下架
     */
    public static final String OFF_SHELVES = "2";

    /**
     * 已上架
     */
    public static final String ON_SHELVES = "1";

    /**
     * 未上架
     */
    public static final String NOT_ON_SHELVES = "0";

    /**
     * 已删除
     */
    public static final String DELETION_STATE_DEL = "1";

    /**
     * 未删除
     */
    public static final String CREATE_DELETION_STATE = "0";

    /**
     * 1,安卓 2，IOS 3，微信 4 支付宝 5 官网 6 其他
     */
    public static final String DOCTOR_REGISTER_CHANNEL_TYPE = "6";

    /**
     * 通过审核
     */
    public static final String DOCTOR_REGISTER_STATE = "2";

    /**
     * UserRelation表医生类型
     */
    public static final String DOCTOR_TYPE = "1";

    /**
     * UserRelation表患者类型
     */
    public static final String PATIENT_TYPE = "2";

    /**
     * 强制更新
     */
    public static final String FORCE_UPDATE = "F";

    public static final String ADVICE_UPDATE = "R";

    private DefaultDBValue() {
    }
}
