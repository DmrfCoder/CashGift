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
import cn.xiaojii.cashgift.adapter.ProjectTableAdapter;
import cn.xiaojii.cashgift.bean.ProjectTableBean;
import cn.xiaojii.cashgift.inter.IBaseFragmentView;
import cn.xiaojii.cashgift.interactor.impl.ProjectTableInterator;
import cn.xiaojii.cashgift.presenter.IMainPresenter;
import cn.xiaojii.cashgift.presenter.impl.ProjectTablePresenter;
import cn.xiaojii.cashgift.view.IProjectTableView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class ProjectTableFragment extends Fragment implements IProjectTableView, IBaseFragmentView {

    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private int e = 0;


    String detailProjectTable = "共：" + a + "人 收礼：" + b + "（" + c + "个） 送礼：" + d + "（" + e + "个）";


    private ProjectTablePresenter projectTablePresenter;
    private ProjectTableAdapter projectTableAdapter;
    private ListView projectTableListView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_projecttable, null);

        projectTablePresenter = new ProjectTablePresenter(this, new ProjectTableInterator());
        initView(view);
        return view;
    }

    private void initView(View view) {
        projectTableListView = view.findViewById(R.id.id_listview_projecttable);
        projectTableAdapter = new ProjectTableAdapter(getActivity());
        projectTableListView.setAdapter(projectTableAdapter);
    }


    @Override
    public void updateData(List<Class> classList) {

    }

    @Override
    public void showDialog(Context context) {

    }

    @Override
    public void updateListView(List<ProjectTableBean> projectTableBeanList) {
        if (projectTableBeanList == null) {
            return;
        }
        projectTableAdapter.setProjectTableBeanList(projectTableBeanList);
        projectTableAdapter.notifyDataSetChanged();


    }
}
