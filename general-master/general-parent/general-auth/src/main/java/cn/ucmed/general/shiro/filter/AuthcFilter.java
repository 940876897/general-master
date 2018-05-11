package cn.ucmed.general.shiro.filter;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthcFilter extends AccessControlFilter {

    @Value("${app.key}")
    private String appKey;

    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        if (!getSubject(request, response).isAuthenticated()) {
            afterAccessDenied(request, response);
            return false;
        }
        return true;
    }

    private void afterAccessDenied(ServletRequest request, ServletResponse response) throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("shiro unauth");

        /**
         * 只有版本后台为只使用angularjs做前端的项目；
         */
        if (!appKey.equals("V")) {
            if (((HttpServletRequest) request).getRequestURL().indexOf("/pages/") <= 0 &&
                    ((HttpServletRequest) request).getRequestURL().indexOf(".json") <= 0) {
                redirectToLogin(request, response);
            }
        }
    }
}  