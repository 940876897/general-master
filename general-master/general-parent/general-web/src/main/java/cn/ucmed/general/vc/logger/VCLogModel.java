package cn.ucmed.general.vc.logger;

public class VCLogModel {

    /**
     * 操作类型(添加、删除、修改)
     */
    private String operateType;

    /**
     * 操作对象(项目、项目软件、版本)
     */
    private String operateObject;

    /**
     * 操作对象ID
     */
    private String objectId;

    /**
     * 操作对象详细内容
     */
    private String objectDetail;

    /**
     * 操作用户ID
     */
    private String operationUserId;

    /**
     * 操作用户姓名
     */
    private String operationUserName;

    /**
     * 操作用户IP
     */
    private String operationUserIp;

    /**
     * 操作时间
     */
    private String date;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperateObject() {
        return operateObject;
    }

    public void setOperateObject(String operateObject) {
        this.operateObject = operateObject;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        objectId = objectId;
    }

    public String getObjectDetail() {
        return objectDetail;
    }

    public void setObjectDetail(String objectDetail) {
        objectDetail = objectDetail;
    }

    public String getOperationUserId() {
        return operationUserId;
    }

    public void setOperationUserId(String operationUserId) {
        this.operationUserId = operationUserId;
    }

    public String getOperationUserName() {
        return operationUserName;
    }

    public void setOperationUserName(String operationUserName) {
        this.operationUserName = operationUserName;
    }

    public String getOperationUserIp() {
        return operationUserIp;
    }

    public void setOperationUserIp(String operationUserIp) {
        this.operationUserIp = operationUserIp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
