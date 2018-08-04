package cn.xiaojii.cashgift.view.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.adapter.RunningAccountListViewAdapter;
import cn.xiaojii.cashgift.bean.ParcelableListBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.inter.OnBroadCastListener;
import cn.xiaojii.cashgift.interactor.impl.FriendsAndRelativesInteractor;
import cn.xiaojii.cashgift.interactor.impl.MainInterator;
import cn.xiaojii.cashgift.interactor.impl.RunningAccountInteractor;
import cn.xiaojii.cashgift.presenter.IMainPresenter;
import cn.xiaojii.cashgift.presenter.impl.FriendsAndRelativesPresenter;
import cn.xiaojii.cashgift.presenter.impl.RunningAccountPresenter;
import cn.xiaojii.cashgift.receiver.DataReceiver;
import cn.xiaojii.cashgift.inter.IBaseFragmentView;
import cn.xiaojii.cashgift.view.IMainView;
import cn.xiaojii.cashgift.view.IRunningAccountView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

@SuppressLint("ValidFragment")
public class RunningAccountFragment extends Fragment implements IRunningAccountView, OnBroadCastListener, IBaseFragmentView {

    private RunningAccountPresenter runningAccountPresenter;
    private DataReceiver dataReceiver;
    private IMainView.OnAddProjectInFragmentListener onAddProjectInFragmentListener;
    private RunningAccountListViewAdapter runningAccountListViewAdapter;
    private ListView runningAccountListView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IMainView.OnAddProjectInFragmentListener) {
            onAddProjectInFragmentListener = (IMainView.OnAddProjectInFragmentListener) context;
        }
    }


    @SuppressLint("ValidFragment")
    public RunningAccountFragment(IMainPresenter mainPresenter) {
        runningAccountPresenter = new RunningAccountPresenter(this, new RunningAccountInteractor());
        mainPresenter.getData(new MainInterator.OnGetDataListener() {
            @Override
            public void OnGetDataError() {
                runningAccountPresenter.initFragmentData(null);
            }

            @Override
            public void OnGetDataSuccess(List<ProjectBean> projectBeanList) {
                runningAccountPresenter.initFragmentData(projectBeanList);
            }
        });
        dataReceiver = new DataReceiver(this);
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
        runningAccountListViewAdapter.setProjectBeanList(projectBeanList);
        runningAccountListViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRecevie(ParcelableListBean parcelableListBean) {
        runningAccountPresenter.initFragmentData(parcelableListBean.getProjectBeanList());
    }

    @Override
    public void updateData(List<Class> classList) {

    }

    @Override
    public void showDialog(Context context) {

    }
}
