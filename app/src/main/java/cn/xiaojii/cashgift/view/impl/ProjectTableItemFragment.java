package cn.xiaojii.cashgift.view.impl;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.adapter.ProjectTableItemAdapter;
import cn.xiaojii.cashgift.base.BaseFragment;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.bean.ProjectTableItemBean;
import cn.xiaojii.cashgift.inter.IBaseFragmentView;
import cn.xiaojii.cashgift.interactor.impl.ProjectTableItemInteractor;
import cn.xiaojii.cashgift.presenter.impl.ProjectTableItemPresenter;
import cn.xiaojii.cashgift.view.IProjectTableItemView;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

public class ProjectTableItemFragment extends BaseFragment implements IProjectTableItemView, IBaseFragmentView, View.OnClickListener {


    private ProjectTableItemAdapter projectTableItemAdapter;
    private ProjectTableItemPresenter projectTableItemPresenter;
    private ListView projectTableItemListView;
    private TextView txSumMoney, txPersonName, txIn, txOut;
    private int inCount, inMoney, outCount, outMoney, sumMoney;
    private String strIn = "收礼：" + inMoney + "（" + inCount + "个）";
    private String strOut = "送礼：" + outMoney + "（" + outCount + "个）";
    private String personName = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_projecttableitem, null);
        initFragment(view);
        return view;
    }

    @Override
    public void initFragment(View view) {
        projectTableItemPresenter = new ProjectTableItemPresenter(this, new ProjectTableItemInteractor());
        if (projectTableItemAdapter == null) {
            projectTableItemAdapter = new ProjectTableItemAdapter(getActivity());
        }

        projectTableItemListView = view.findViewById(R.id.id_projecttable_item_listview);
        projectTableItemListView.setAdapter(projectTableItemAdapter);

        view.findViewById(R.id.id_projecttable_item_top_right).setOnClickListener(this);

        txIn = view.findViewById(R.id.id_projecttable_item_in);
        txOut = view.findViewById(R.id.id_projecttable_item_out);
        txPersonName = view.findViewById(R.id.id_projecttable_item_top_center);
        txSumMoney = view.findViewById(R.id.id_projecttable_item_summoney);

        updateTextView();

    }


    @SuppressLint("SetTextI18n")
    @Override
    public void updateTextView() {
        if (txIn != null && txOut != null && txPersonName != null && txSumMoney != null) {
            strIn = "收礼：" + inMoney + "（" + inCount + "个）";
            strOut = "送礼：" + outMoney + "（" + outCount + "个）";
            txIn.setText(strIn);
            txOut.setText(strOut);
            txPersonName.setText(personName);
            txSumMoney.setText(sumMoney + "");
        }

    }


    @Override
    public void updateView(int inCount, int inMoney, int outCount, int outMoney, int sumMoney, String personName) {
        this.inCount = inCount;
        this.inMoney = inMoney;
        this.outCount = outCount;
        this.outMoney = outMoney;
        this.sumMoney = sumMoney;
        this.personName = personName;
        updateTextView();
    }

    @Override
    public void updateListView(List<ProjectTableItemBean> projectTableItemBeanList) {
        if (projectTableItemAdapter == null) {
            projectTableItemAdapter = new ProjectTableItemAdapter(getActivity());
        }
        projectTableItemAdapter.setProjectTableItemBeanList(projectTableItemBeanList);
        projectTableItemAdapter.notifyDataSetChanged();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_projecttable_item_top_right:
                showAddProjectFragmentDialog(getActivity(), getClass().getName());
                break;
            default:
                break;
        }
    }
}
