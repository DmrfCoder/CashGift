package cn.xiaojii.cashgift.view.impl;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.presenter.IMainPresenter;
import cn.xiaojii.cashgift.view.IRunningAccountView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

@SuppressLint("ValidFragment")
public class RunningAccountFragment extends Fragment implements IRunningAccountView {


    @SuppressLint("ValidFragment")
    public RunningAccountFragment(IMainPresenter mainPresenter) {

    }

    public RunningAccountFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_runningaccount, null);
        return view;
    }

    @Override
    public void updateListView(List<ProjectBean> projectBeanList) {

    }
}
