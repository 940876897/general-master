package cn.ucmed.general.vc.controller.vcproject;

import cn.ucmed.general.vc.model.VCProjectModel;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.XlsDataSetLoader;
import cn.ucmed.common.constants.PermissionConstants;
import cn.ucmed.common.util.PaginationResult;
import junit.framework.Assert;
import net.sf.json.JSONObject;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.SubjectThreadState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Date;
import java.util.List;

@RunWith(value = SpringJUnit4ClassRunner.class)
@DbUnitConfiguration(dataSetLoader = XlsDataSetLoader.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@ContextConfiguration(locations = {
        "classpath*:META-INF/spring/*.xml",
        "file:../general/src/main/webapp/WEB-INF/general-servlet.xml"
})
public class VCProjectControllerTest {

    @Autowired
    private VCProjectController vCProjectController;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.setParameter("currentPageNo", "1");
        request.setParameter("pageSize", "5");
        request.setParameter("searchValue", "浙一医院");

        Subject subject = PowerMockito.mock(Subject.class);
        PowerMockito.when(subject.isAuthenticated()).thenReturn(true);
        PowerMockito.when(subject.isPermitted(PermissionConstants.PROJECT_SEARCH)).thenReturn(true);
        PowerMockito.when(subject.isPermitted(PermissionConstants.PROJECT_VIEW)).thenReturn(true);
        SubjectThreadState thread = new SubjectThreadState(subject);
        thread.bind();
    }

    @Test
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = {"classpath:TEST-DATA/vc_project.xlsx"})
    public void listGetTest() throws Exception {
        VCProjectModel m1 = new VCProjectModel();
        m1.setVcProjectId("5a51f9ba-5cf7-4ac2-b84a-0aa10c2e14c4");
        m1.setHospitalName("浙江大学附属第一医院");
        m1.setProjectName("浙一医院");
        m1.setCreatedby("sysrtem");
        m1.setCreatedon(new Date());
        m1.setModifiedby("system");
        m1.setModifiedon(new Date());
        m1.setDeletionState("0");
        m1.setDescription("测试");
        request.getSession().setAttribute("admin.session", "system");
        JSONObject j = vCProjectController.addPost(request, m1);
        Assert.assertEquals(j.optInt("code"), 0);
        PaginationResult<VCProjectModel> list = vCProjectController.listGet(request);
        VCProjectModel m2 = list.getList().get(0);
        Assert.assertEquals(m1.getProjectName(), m2.getProjectName());
        Assert.assertEquals(m1.getHospitalName(), m2.getHospitalName());
        Assert.assertEquals(m1.getCreatedby(), m2.getCreatedby());
        Assert.assertEquals(m1.getModifiedby(), m2.getModifiedby());
        Assert.assertEquals(m1.getDeletionState(), m2.getDeletionState());
    }

    @Test
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = {"classpath:TEST-DATA/vc_project.xlsx"})
    public void searchTest() throws Exception {

        VCProjectModel m1 = new VCProjectModel();
        m1.setVcProjectId("5a51f9ba-5cf7-4ac2-b84a-0aa10c2e14c4");
        m1.setHospitalName("浙江大学附属第一医院");
        m1.setProjectName("浙一医院");
        m1.setCreatedby("sysrtem");
        m1.setCreatedon(new Date());
        m1.setModifiedby("system");
        m1.setModifiedon(new Date());
        m1.setDeletionState("0");
        m1.setDescription("测试");
        request.getSession().setAttribute("admin.session", "system");
        JSONObject j = vCProjectController.addPost(request, m1);
        Assert.assertEquals(j.optInt("code"), 0);
        List<VCProjectModel> list = vCProjectController.search(request, m1.getProjectName());
        VCProjectModel m2 = list.get(0);
        Assert.assertEquals(m1.getProjectName(), m2.getProjectName());
        Assert.assertEquals(m1.getCreatedby(), m2.getCreatedby());
        Assert.assertEquals(m1.getModifiedby(), m2.getModifiedby());
        Assert.assertEquals(m1.getDeletionState(), m2.getDeletionState());
    }

    @Test
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = {"classpath:TEST-DATA/vc_project.xlsx"})
    public void updateTest() throws Exception {
        VCProjectModel m1 = new VCProjectModel();
        m1.setVcProjectId("5a51f9ba-5cf7-4ac2-b84a-0aa10c2e14c4");
        m1.setHospitalName("浙江大学附属第一医院");
        m1.setProjectName("浙一医院");
        m1.setCreatedby("sysrtem");
        m1.setCreatedon(new Date());
        m1.setModifiedby("system");
        m1.setModifiedon(new Date());
        m1.setDeletionState("0");
        m1.setDescription("测试");
        request.getSession().setAttribute("admin.session", "system");
        JSONObject j = vCProjectController.addPost(request, m1);
        Assert.assertEquals(j.optInt("code"), 0);
        PaginationResult<VCProjectModel> list = vCProjectController.listGet(request);
        VCProjectModel m2 = list.getList().get(0);
        Assert.assertEquals(m1.getProjectName(), m2.getProjectName());
        Assert.assertEquals(m1.getCreatedby(), m2.getCreatedby());
        Assert.assertEquals(m1.getHospitalName(), m2.getHospitalName());
        Assert.assertEquals(m1.getModifiedby(), m2.getModifiedby());
        Assert.assertEquals(m1.getDeletionState(), m2.getDeletionState());
        m1.setProjectName("浙一医院");
        m1.setHospitalName("浙江大学附属第二医院");
        JSONObject uj = vCProjectController.updatePut(request, m1);
        Assert.assertEquals(uj.optInt("code"), 0);
        PaginationResult<VCProjectModel> list2 = vCProjectController.listGet(request);
        VCProjectModel m3 = list2.getList().get(0);
        Assert.assertEquals(m1.getProjectName(), m3.getProjectName());
        Assert.assertEquals(m1.getCreatedby(), m3.getCreatedby());
        Assert.assertEquals(m1.getHospitalName(), m3.getHospitalName());
        Assert.assertEquals(m1.getModifiedby(), m3.getModifiedby());
        Assert.assertEquals(m1.getDeletionState(), m3.getDeletionState());
    }

}
