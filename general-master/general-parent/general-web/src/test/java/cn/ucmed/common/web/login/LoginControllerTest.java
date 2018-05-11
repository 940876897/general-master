package cn.ucmed.common.web.login;

import cn.ucmed.general.security.LoginController;
import cn.ucmed.general.shiro.model.User;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.XlsDataSetLoader;
import junit.framework.Assert;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.io.IOException;


@RunWith(value = SpringJUnit4ClassRunner.class)
@DbUnitConfiguration(dataSetLoader = XlsDataSetLoader.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@ContextConfiguration(locations = {
        "classpath*:META-INF/spring/*.xml",
        "file:../general/src/main/webapp/WEB-INF/general-servlet.xml"
})
public class LoginControllerTest {

    @Autowired
    private LoginController loginController;
    
    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = {"classpath:TEST-DATA/common/web/login/u_users.xls"})
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = {"classpath:TEST-DATA/common/web/login/u_users.xls"})
    public void loginTest() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        User user = new User();
        user.setUsername("admin");
        user.setPassword("88851766");
        JSONObject res = loginController.login(request, user);
        Assert.assertEquals("true", res.optString("success"));
    }
}
