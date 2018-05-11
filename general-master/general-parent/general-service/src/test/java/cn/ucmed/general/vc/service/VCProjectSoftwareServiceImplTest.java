package cn.ucmed.general.vc.service;

import cn.ucmed.general.vc.model.VCProjectSoftware;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.XlsDataSetLoader;
import cn.ucmed.common.constants.model.DefaultDBValue;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.SubjectThreadState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Date;

/**
 * @ClassName VCProjectServiceImplTest
 * @Description 测试项目软件service
 * @author 刘兴龙
 * @date 2015年7月24日 下午13:35:30
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@DbUnitConfiguration(dataSetLoader = XlsDataSetLoader.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml"})
public class VCProjectSoftwareServiceImplTest {

    @Autowired
    private VCProjectSoftwareService vcProjectSoftwareService;

    @Test
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = {
            "classpath:TEST-DATA/VersionControl/vc_project_software.xlsx",
            "classpath:TEST-DATA/VersionControl/vc_project.xlsx",
            "classpath:TEST-DATA/VersionControl/vc_software_version.xlsx" })
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = {
            "classpath:TEST-DATA/VersionControl/vc_project_software.xlsx",
            "classpath:TEST-DATA/VersionControl/vc_project.xlsx",
            "classpath:TEST-DATA/VersionControl/vc_software_version.xlsx" })
    public void updateProjectSoftwareTest() {
        VCProjectSoftware vcProejctSoftware = new VCProjectSoftware();
        vcProejctSoftware.setVcProjectId("3e4becc0-0612-11e6-a837-0800200c9a66");
        vcProejctSoftware.setSoftwareId(0);
        vcProejctSoftware.setSoftwareName("测试修改");
        vcProejctSoftware.setSoftwareType("2");
        vcProejctSoftware.setCommonName("csxg");
        vcProejctSoftware.setVcProjectSoftwareId("1c2dd00d-e608-4956-b139-396e8e5f41c7");
        vcProejctSoftware.setCreatedby("system");
        vcProejctSoftware.setCreatedon(new Date());
        vcProejctSoftware.setDeletionState(DefaultDBValue.CREATE_DELETION_STATE);

        Subject subject = PowerMockito.mock(Subject.class);
        PowerMockito.when(subject.getPrincipal()).thenReturn("system");
        SubjectThreadState subjectThreadState = new SubjectThreadState(subject);
        subjectThreadState.bind();
        vcProjectSoftwareService.updateProjectSoftware(vcProejctSoftware);
    }

    @Test
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = {
            "classpath:TEST-DATA/VersionControl/vc_project_software.xlsx",
            "classpath:TEST-DATA/VersionControl/vc_project.xlsx",
            "classpath:TEST-DATA/VersionControl/vc_software_version.xlsx" })
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = {
            "classpath:TEST-DATA/VersionControl/vc_project_software.xlsx",
            "classpath:TEST-DATA/VersionControl/vc_project.xlsx",
            "classpath:TEST-DATA/VersionControl/vc_software_version.xlsx" })
    public void versionDetectionTest() {
        // PaginationResult<VCProjectSoftwareModel> ptr =
        // vcProjectSoftwareService
        // .getProjectSoftwareList("5a51f9ba-5cf7-4ac2-b84a-0aa10c2e14c4",
        // 1L, 20L);
        // List<VCProjectSoftwareModel> list = ptr.getList();
        // for (VCProjectSoftwareModel vcProjectSoftwareModel : list) {
        // System.out.println("软件名称:"
        // + vcProjectSoftwareModel.getSoftwareName() + "\n最新市场版本号:"
        // + vcProjectSoftwareModel.getLatestMarketVersion());
        // }
    }

}