package cn.xiaojii.cashgift.view.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.List;

import cn.xiaojii.cashgift.bean.RunningAccountBean;
import cn.xiaojii.cashgift.interactor.RunningAccountInterator;
import cn.xiaojii.cashgift.presenter.impl.RunningAcountPresenter;
import cn.xiaojii.cashgift.view.IRunningAccountView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class RunningAccountFragment extends BaseFragment implements IRunningAccountView {

    private RunningAcountPresenter runningAcountPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        runningAcountPresenter = new RunningAcountPresenter(this, new RunningAccountInterator());
    }

    @Override
    public void updateListView(List<RunningAccountBean> runningAccountBeanList) {

    }
}
