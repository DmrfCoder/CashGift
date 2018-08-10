package cn.xiaojii.cashgift.view.impl.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.adapter.RunningAccountListViewAdapter;
import cn.xiaojii.cashgift.view.impl.base.BaseFragment;
import cn.xiaojii.cashgift.bean.fragment.ProjectBean;
import cn.xiaojii.cashgift.interactor.impl.RunningAccountFragmentInteractor;
import cn.xiaojii.cashgift.presenter.impl.fragment.RunningAccountFragmentPresenter;
import cn.xiaojii.cashgift.view.inter.fragment.IRunningAccountView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

@SuppressLint("ValidFragment")
public class RunningAccountFragment extends BaseFragment implements View.OnClickListener, IRunningAccountView {

    private RunningAccountFragmentPresenter runningAccountPresenter;
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
        runningAccountPresenter = new RunningAccountFragmentPresenter(this, new RunningAccountFragmentInteractor());

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
                showAddProjectFragmentDialog(getActivity(), getClass().getName());
                break;
            default:
                break;
        }

    }


}
