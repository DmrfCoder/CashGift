package cn.xiaojii.cashgift.view.impl;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.List;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.adapter.RunningAccountListViewAdapter;
import cn.xiaojii.cashgift.base.BaseFragment;
import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.interactor.impl.RunningAccountInteractor;
import cn.xiaojii.cashgift.presenter.impl.RunningAccountPresenter;
import cn.xiaojii.cashgift.inter.IBaseFragmentView;
import cn.xiaojii.cashgift.view.IRunningAccountView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

@SuppressLint("ValidFragment")
public class RunningAccountFragment extends BaseFragment implements View.OnClickListener, IRunningAccountView, IBaseFragmentView {

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
        runningAccountPresenter = new RunningAccountPresenter(this, new RunningAccountInteractor());

        initFragment(view);
        return view;
    }

    @Override
    public void initFragment(View view) {
        runningAccountListView = view.findViewById(R.id.id_listview_runningaccount);
        if (runningAccountListViewAdapter == null) {
            runningAccountListViewAdapter = new RunningAccountListViewAdapter(getActivity());
        }
        runningAccountListView.setAdapter(runningAccountListViewAdapter);
        view.findViewById(R.id.id_runningaccount_top_right).setOnClickListener(this);

    }


    @Override
    public void updateListView(List<ProjectBean> projectBeanList) {
        if (runningAccountListViewAdapter == null) {
            runningAccountListViewAdapter = new RunningAccountListViewAdapter(getActivity());
        }
        runningAccountListViewAdapter.setProjectBeanList(projectBeanList);
        runningAccountListViewAdapter.notifyDataSetChanged();
    }





    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_runningaccount_top_right:
                showAddProjectFragmentDialog(getActivity(), "RunningAccountFragment");
                break;
            default:
                break;
        }

    }


}
