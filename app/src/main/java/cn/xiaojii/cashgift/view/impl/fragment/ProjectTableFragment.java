package cn.xiaojii.cashgift.view.impl.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.adapter.ProjectTableAdapter;
import cn.xiaojii.cashgift.bean.fragment.ProjectTableBean;
import cn.xiaojii.cashgift.interactor.impl.ProjectTableInterator;
import cn.xiaojii.cashgift.presenter.impl.fragment.ProjectTableFragmentPresenter;
import cn.xiaojii.cashgift.view.impl.base.BaseFragment;
import cn.xiaojii.cashgift.view.inter.fragment.IProjectTableView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class ProjectTableFragment extends BaseFragment implements IProjectTableView, AdapterView.OnItemClickListener {


    private ProjectTableFragmentPresenter projectTablePresenter;
    private ProjectTableAdapter projectTableAdapter;
    private ListView projectTableListView;
    private TextView txDetailProjectTable;
    private TextView txTopTotalMoney;
    private String detailProjectTable = "共：0 人 收礼：0（0个） 送礼：0（0个）";
    private int totalMoney = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_projecttable, null);

        initFragment(view);
        return view;
    }


    @Override
    public void initFragment(View view) {
        projectTablePresenter = new ProjectTableFragmentPresenter(this, new ProjectTableInterator());
        txTopTotalMoney = view.findViewById(R.id.id_summoney_projettable);
        txDetailProjectTable = view.findViewById(R.id.id_detail_projecttable);
        projectTableListView = view.findViewById(R.id.id_listview_projecttable);
        projectTableListView.setOnItemClickListener(this);
        updateTextView();


        if (projectTableAdapter == null) {
            projectTableAdapter = new ProjectTableAdapter(getActivity());
        }

        projectTableListView.setAdapter(projectTableAdapter);
    }


    @Override
    public void updateListView(List<ProjectTableBean> projectTableBeanList) {
        if (projectTableBeanList == null) {
            return;
        }

        if (projectTableAdapter == null) {
            projectTableAdapter = new ProjectTableAdapter(getActivity());
        }
        projectTableAdapter.setProjectTableBeanList(projectTableBeanList);
        projectTableAdapter.notifyDataSetChanged();


    }

    @Override
    public void updateTextView() {
        if (txTopTotalMoney != null && txDetailProjectTable != null) {
            txDetailProjectTable.setText(detailProjectTable);
            txTopTotalMoney.setText(totalMoney + "");
        }

    }


    @Override
    @SuppressLint("SetTextI18n")
    public void updateTopBarData(int a, int b, int c, int d, int e, int totalMoney) {
        detailProjectTable = "共：" + a + "人 收礼：" + b + "（" + c + "个） 送礼：" + d + "（" + e + "个）";
        this.totalMoney = totalMoney;
        updateTextView();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.id_listview_projecttable:
                projectTablePresenter.clickListViewItem(i);
                break;
            default:
                break;
        }
    }
}
