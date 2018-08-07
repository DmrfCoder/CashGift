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
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.interactor.impl.MainInterator;
import cn.xiaojii.cashgift.interactor.impl.RunningAccountInteractor;
import cn.xiaojii.cashgift.presenter.IMainPresenter;
import cn.xiaojii.cashgift.presenter.impl.RunningAccountPresenter;
import cn.xiaojii.cashgift.inter.IBaseFragmentView;
import cn.xiaojii.cashgift.util.SendBroadCastUtil;
import cn.xiaojii.cashgift.view.IMainView;
import cn.xiaojii.cashgift.view.IRunningAccountView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

@SuppressLint("ValidFragment")
public class RunningAccountFragment extends Fragment implements View.OnClickListener,IRunningAccountView, IBaseFragmentView {

    private RunningAccountPresenter runningAccountPresenter;
    private RunningAccountListViewAdapter runningAccountListViewAdapter;
    private ListView runningAccountListView;


    @Override
    public void onStart() {
        super.onStart();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_runningaccount, null);
        runningAccountPresenter=new RunningAccountPresenter(this,new RunningAccountInteractor());
        SendBroadCastUtil.sendNeedDataBC(this);
        initView(view);
        return view;
    }

    private void initView(View view) {
        runningAccountListView=view.findViewById(R.id.id_listview_runningaccount);
        runningAccountListViewAdapter=new RunningAccountListViewAdapter(getActivity());
        runningAccountListView.setAdapter(runningAccountListViewAdapter);
        view.findViewById(R.id.id_runningaccount_top_right).setOnClickListener(this);
        runningAccountPresenter.updateView();
    }

    @Override
    public void updateListView(List<ProjectBean> projectBeanList) {
        runningAccountListViewAdapter.setProjectBeanList(projectBeanList);
        runningAccountListViewAdapter.notifyDataSetChanged();
    }



    @Override
    public void updateData(List<Class> classList) {

    }

    @Override
    public void showDialog(Context context) {

    }



    @Override
    public void onClick(View view) {

    }
}
