package cn.ucmed.general.vc.controller.vcsoftwareversion;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import cn.ucmed.general.vc.task.VersionControlTsk;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.XlsDataSetLoader;

@RunWith(value = SpringJUnit4ClassRunner.class)
@DbUnitConfiguration(dataSetLoader = XlsDataSetLoader.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@ContextConfiguration(locations = {
        "classpath*:META-INF/spring/*.xml",
        "file:../general/src/main/webapp/WEB-INF/general-servlet.xml"
})
public class VersionControlTskTest {

    @Autowired
    private VersionControlTsk versionControlTsk;

    @Test
    public void goTest() {
        versionControlTsk.go();
    }
}
