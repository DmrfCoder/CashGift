package cn.xiaojii.cashgift;

import org.junit.Test;

import java.util.List;

import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.bean.ProjectTableBean;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        ProjectTableBean projectTableBean=new ProjectTableBean();
        projectTableBean.setName("demo");
        ProjectBean projectBean=new ProjectBean();
        projectBean.setName("demo");
        projectTableBean.addProjectBean(projectBean);

        if (projectTableBean.hasTargetBean(projectBean)){
            System.out.print("true");
        }else {
            System.out.print("false");
        }

        assertEquals(4, 2 + 2);
    }
}