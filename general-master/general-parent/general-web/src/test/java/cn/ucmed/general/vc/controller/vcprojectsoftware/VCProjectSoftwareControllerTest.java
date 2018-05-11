package cn.ucmed.general.vc.controller.vcprojectsoftware;

import cn.ucmed.general.vc.model.VCProjectSoftwareModel;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.XlsDataSetLoader;
import junit.framework.Assert;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Date;

@RunWith(value = SpringJUnit4ClassRunner.class)
@DbUnitConfiguration(dataSetLoader = XlsDataSetLoader.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@ContextConfiguration(locations = {
        "classpath*:META-INF/spring/*.xml",
        "file:../general/src/main/webapp/WEB-INF/general-servlet.xml"
})
public class VCProjectSoftwareControllerTest {

    @Autowired
    private VCProjectSoftwareController vCProjectSoftwareController;
    private MockHttpServletRequest request;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.DELETE_ALL, value = {"classpath:TEST-DATA/vc_project.xlsx"})
    public void listGetTest() {

        VCProjectSoftwareModel m1 = new VCProjectSoftwareModel();
        m1.setVcProjectSoftwareId("1c2dd00d-e608-4956-b139-396e800f4008");
        m1.setVcProjectId("1c2dd00d-e608-4956-b139-396e8e5f4");
        m1.setSoftwareName("fsds");
        m1.setCommonName("fsdfs");
        m1.setSoftwareType("3");
        m1.setCreatedby("sysrtem");
        m1.setCreatedon(new Date());
        m1.setModifiedby("system");
        m1.setModifiedon(new Date());
        m1.setDeletionState("0");
        m1.setDescription("测试");
        JSONObject j = vCProjectSoftwareController.addProjectSoftware(request, m1);
        Assert.assertEquals(j.optInt("code"), 0);
    }

}
