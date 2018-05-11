package cn.ucmed.general.common.util;

public class VersionUtil {

    private VersionUtil() {
    }

    /**
     * @param clientVersion
     * @param serverVersion
     * @return
     * @throws Exception
     * @Description 判断是否有新版本，如果客户端版本<服务端版本则返回true，否则返回false
     */
    public static boolean hasNewVersion(String clientVersion, String serverVersion) {

        /*
         * 当掌上医院版本号为空且版本管理平台不为空时，判定为有更新；当掌上医院版本号不为空且版本管理平台为空时，判定为没有更新
         */
        if (clientVersion == null || serverVersion == null) {
            return clientVersion == null && serverVersion != null;
        }

        String[] cv = clientVersion.split("\\.");// 注意此处为正则匹配，不能用.；
        String[] sv = serverVersion.split("\\.");

        int idx = 0;
        int minLength = Math.min(cv.length, sv.length);// 取最小长度值
        int diff = 0;

        while (idx < minLength
                && (diff = cv[idx].length() - sv[idx].length()) == 0// 先比较长度
                && (diff = cv[idx].compareTo(sv[idx])) == 0) {// 再比较字符
            ++idx;
        }
        // 如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : cv.length - sv.length;
        return diff < 0;
    }
}
