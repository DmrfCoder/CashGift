package cn.xiaojii.cashgift.interactor;

import android.util.Log;

import org.apache.tools.ant.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadow.api.Shadow;
import org.robolectric.shadows.ShadowLog;

import java.util.List;

import cn.xiaojii.cashgift.BuildConfig;
import cn.xiaojii.cashgift.bean.RunningAccountBean;
import cn.xiaojii.cashgift.view.impl.MainActivity;

import static org.junit.Assert.*;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class MainInteratorTest {

    private MainInterator mainInterator;
    private RunningAccountBean runningAccountBean;
    private String TAG = "MainInteratorTest";


    @Before
    public void setUp() {
        ShadowLog.stream = System.out;
        mainInterator = new MainInterator();
    }


    @Test
    public void testAddRunningAccount() {
        runningAccountBean = new RunningAccountBean();
        runningAccountBean.setName("张三");
        runningAccountBean.setProject("婚礼");
        runningAccountBean.setMoney(1000);

        mainInterator.addRunningAccount(runningAccountBean, new MainInterator.OnAddProjrctListener() {
            @Override
            public void onAddError() {
                Log.i(TAG, "onAddError");
            }

            @Override
            public void onAddSuccess(List<RunningAccountBean> runningAccountBeanList) {
                Log.i(TAG, "onAddSuccess:" + runningAccountBeanList.toString());
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
            public void OnInitSuccess(List<RunningAccountBean> runningAccountBeanList) {
                Log.i(TAG, "onAddSuccess:" + runningAccountBeanList.toString());
            }
        });
    }

}