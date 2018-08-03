package cn.xiaojii.cashgift.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.xiaojii.cashgift.R;
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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_runningaccount, null);
        runningAcountPresenter = new RunningAcountPresenter(this, new RunningAccountInterator());
        return view;
    }

    @Override
    public void updateListView(List<RunningAccountBean> runningAccountBeanList) {

    }
}
