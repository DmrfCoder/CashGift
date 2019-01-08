package cn.xiaojii.cashgift.interactor.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import cn.xiaojii.cashgift.BuildConfig;
import cn.xiaojii.cashgift.bean.fragment.ProjectBean;

/**
 * @author dmrfcoder
 * @date 2018/8/4
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class FriendsAndRelativesInteractorTest {

    private FriendsAndRelativesFragmentInteractor friendsAndRelativesInteractor;
    private String TAG = "FriendsAndRelativesInteractorTest";


    @Before
    public void setUp() {
        ShadowLog.stream = System.out;
        friendsAndRelativesInteractor = new FriendsAndRelativesFragmentInteractor();
    }


    @Test
    public void testAddProject() {

        ProjectBean projectBean = new ProjectBean();
        projectBean.setName("张三");
        projectBean.setProject("上学");
        projectBean.setMoney(100);


    }

}