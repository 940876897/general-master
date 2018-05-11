package cn.ucmed.general.vc.controller.vcsoftwareversion;

import cn.ucmed.general.vc.model.VCSoftwareVersionModel;
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
import org.codehaus.jackson.map.ObjectMapper;
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

@RunWith(value = SpringJUnit4ClassRunner.class)
@DbUnitConfiguration(dataSetLoader = XlsDataSetLoader.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@ContextConfiguration(locations = {
        "classpath*:META-INF/spring/*.xml",
        "file:../general/src/main/webapp/WEB-INF/general-servlet.xml"
})
public class VCSoftwareVersionControllerTest {

    @Autowired
    private VCSoftwareVersionController vCSoftwareVersionController;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

        Subject subject = PowerMockito.mock(Subject.class);
        PowerMockito.when(subject.isAuthenticated()).thenReturn(true);
        PowerMockito.when(subject.isPermitted(PermissionConstants.SOFTWARE_VERSION)).thenReturn(true);
        SubjectThreadState thread = new SubjectThreadState(subject);
        thread.bind();
    }

    @SuppressWarnings("unchecked")
    @Test
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = {"classpath:TEST-DATA/vc_software_version.xlsx"})
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = {"classpath:TEST-DATA/vc_software_version.xlsx"})
    public void listGetTest() {

        request.setParameter("softwareId",
                "5a51f9ba-5cf7-4ac2-b84a-0aa10c2e14ss");
        request.setParameter("currentPageNo", "1");
        request.setParameter("pageSize", "20");
        request.getSession().setAttribute("admin.session", "system");
        JSONObject retJson = vCSoftwareVersionController
                .listGet(request);

        PaginationResult<VCSoftwareVersionModel> pageResult = null;
        try {
            ObjectMapper om = new ObjectMapper();
            pageResult = om.readValue(
                    retJson.optJSONObject("softWareVersionList").toString(),
                    PaginationResult.class);
        } catch (Exception e) {
            Assert.assertTrue(false);
        }

        JSONObject m2 = JSONObject.fromObject(pageResult.getList().get(0));
        Assert.assertEquals("5a51f9ba-5cf7-4ac2-b84a-0aa10c2e14ss",
                m2.optString("vcProjectSoftwareId"));
        Assert.assertEquals("1.0.0", m2.optString("versionNumber"));
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = {"classpath:TEST-DATA/vc_software_version.xlsx"})
    public void addPostTest() throws Exception {
//        VCSoftwareVersionModel v = new VCSoftwareVersionModel();
//        v.setVcProjectSoftwareId("fdggdgdfdsfsdfs11111111");
//        v.setVersionNumber("1.0.0");
//        v.setAppVersionNumber("1.0.0");
//        v.setReleaseTime(new Date());
//        v.setAppDownloadUrl("www.baidu.com");
//        v.setAppForceUpdate("Y");
//        v.setZipVersionNumber("1.0.0");
//        v.setZipDesc("测试");
//        v.setAppDesc("ceshi");
//        v.setZipDownloadUrl("www.baidu.com");
//        v.setDescription("备注");
//        VCSoftwareVersionModel model = vCSoftwareVersionController.addPost(
//                request, v);
//        Assert.assertEquals(model.getVcProjectSoftwareId(),
//                v.getVcProjectSoftwareId());
    }

    @Test
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = {"classpath:TEST-DATA/vc_software_version.xlsx"})
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = {"classpath:TEST-DATA/vc_software_version.xlsx"})
    public void updatePutTest() {
        VCSoftwareVersionModel model = new VCSoftwareVersionModel();
        model.setVcSoftwareVersionId("5a51f9ba-5cf7-4ac2-b84a-0aa10c2e14c4");
        model.setVcProjectSoftwareId("fdggdgdfdsfsdfs9999999999999999");
        model.setVersionNumber("1.0.00");
        model.setAppVersionNumber("1.0.00");
        model.setReleaseTime(new Date());
        model.setAppDownloadUrl("www.baidu.com0");
        model.setAppForceUpdate("N");
        model.setZipVersionNumber("1.0.00");
        model.setZipDesc("测试0");
        model.setAppDesc("ceshi0");
        model.setZipDownloadUrl("www.baidu.com0");
        model.setDescription("备注0");
        //TODO   vCSoftwareVersionController.updatePut(model);
        Assert.assertEquals("fdggdgdfdsfsdfs9999999999999999",
                model.getVcProjectSoftwareId());
    }

    @Test
    public void deleteTest() {
        // vCSoftwareVersionController.delete("e8b494da-02a9-4746-8aa9-cbda6afe8fbe");
    }

    @Test
    public void offShelvesTest() throws Exception {
        VCSoftwareVersionModel model = new VCSoftwareVersionModel();
        model.setVcSoftwareVersionId("dc73cc73-8730-4e64-8d82-cade1f91679d");
        //TODO     vCSoftwareVersionController.offShelves(model);
    }


    @Test
    public void addTest() {
/*    	VCSoftwareVersionModel model = new VCSoftwareVersionModel();
        model.setVcProjectSoftwareId("19199914-d53a-424e-8b2b-29456dc88305");
    	model.setReleaseTime(new Date());
    	model.setAppVersionNumber("1.1.2");
        vCSoftwareVersionController.addPost(request, model);*/
    }

}
