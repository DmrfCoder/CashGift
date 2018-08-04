package cn.xiaojii.cashgift.interactor.impl;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.List;

import cn.xiaojii.cashgift.BuildConfig;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.interactor.IBaseInteractor;

import static org.junit.Assert.*;

/**
 * @author dmrfcoder
 * @date 2018/8/4
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class FriendsAndRelativesInteractorTest {

    private FriendsAndRelativesInteractor friendsAndRelativesInteractor;
    private String TAG = "FriendsAndRelativesInteractorTest";


    @Before
    public void setUp() {
        ShadowLog.stream = System.out;
        friendsAndRelativesInteractor = new FriendsAndRelativesInteractor();
    }


    @Test
    public void testAddProject() {

        ProjectBean projectBean = new ProjectBean();
        projectBean.setName("张三");
        projectBean.setProject("上学");
        projectBean.setMoney(100);

        friendsAndRelativesInteractor.addProject(projectBean, new IBaseInteractor.AddProjectListener() {
            @Override
            public void onAddProjectError() {
                Log.e(TAG, "onAddProjectError");
            }

            @Override
            public void onAddProjectSuccess(List beanList) {
                Log.i(TAG, "onAddProjectSuccess");
            }
        });
    }

}