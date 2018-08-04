package cn.xiaojii.cashgift.interactor;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.List;

import cn.xiaojii.cashgift.BuildConfig;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.interactor.impl.MainInterator;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class MainInteratorTest {

    private MainInterator mainInterator;
    private ProjectBean projectBean;
    private String TAG = "MainInteratorTest";


    @Before
    public void setUp() {
        ShadowLog.stream = System.out;
        mainInterator = new MainInterator();
    }


    @Test
    public void testAddRunningAccount() {
        projectBean = new ProjectBean();
        projectBean.setName("张三");
        projectBean.setProject("婚礼");
        projectBean.setMoney(1000);

        mainInterator.addRunningAccount(projectBean, new MainInterator.OnAddProjrctListener() {
            @Override
            public void onAddError() {
                Log.i(TAG, "onAddError");
            }

            @Override
            public void onAddSuccess(List<ProjectBean> projectBeanList) {
                Log.i(TAG, "onAddSuccess:" + projectBeanList.toString());
            }
        });
    }


    @Test
    public void testInitData() {
        mainInterator.initData(new MainInterator.OnInitDataListener() {
            @Override
            public void OnInitError() {
                Log.i(TAG, "OnInitError:");
            }

            @Override
            public void OnInitSuccess(List<ProjectBean> projectBeanList) {
                Log.i(TAG, "onAddSuccess:" + projectBeanList.toString());
            }
        });
    }

}