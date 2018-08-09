package cn.xiaojii.cashgift.presenter.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import cn.xiaojii.cashgift.BuildConfig;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.interactor.impl.FriendsAndRelativesInteractor;
import cn.xiaojii.cashgift.view.impl.FriendsAndRelativesFragment;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * @author dmrfcoder
 * @date 2018/8/4
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class FriendsAndRelativesPresenterTest {


    private FriendsAndRelativesPresenter friendsAndRelativesPresenter;
    private FriendsAndRelativesFragment friendsAndRelativesFragment;

    @Before
    public void setUp() {
        ShadowLog.stream = System.out;
        friendsAndRelativesFragment = new FriendsAndRelativesFragment();
        friendsAndRelativesPresenter = new FriendsAndRelativesPresenter(friendsAndRelativesFragment, new FriendsAndRelativesInteractor());
    }


    @Test
    public void testAddProjectBean() {
        ProjectBean projectBean = new ProjectBean();
        projectBean.setName("张三");
        projectBean.setProject("上学");
        projectBean.setMoney(100);
        friendsAndRelativesPresenter.addProjectFromDialog(projectBean);

        verify(friendsAndRelativesPresenter, never()).onAddProjectError();

    }

}