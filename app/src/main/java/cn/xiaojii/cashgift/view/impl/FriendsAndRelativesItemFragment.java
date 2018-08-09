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
import android.widget.TextView;

import java.util.List;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.adapter.RunningAccountListViewAdapter;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.inter.IBaseFragmentView;
import cn.xiaojii.cashgift.interactor.impl.FriendsAndRelativesItemInteractor;
import cn.xiaojii.cashgift.presenter.impl.FriendsAndRelativesItemPresenter;
import cn.xiaojii.cashgift.view.IFriendsAndRelativesItemView;

/**
 * @author dmrfcoder
 * @date 2018/8/8
 */

public class FriendsAndRelativesItemFragment extends Fragment implements IFriendsAndRelativesItemView, IBaseFragmentView, View.OnClickListener {


    private FriendsAndRelativesItemPresenter friendsAndRelativesItemPresenter;
    private ListView friendsAndRelativesItemListview;
    private TextView txTotalMoney, txInCount, txOutCount;

    /**
     * RunningAccountListViewAdapter就是此处需要的adapter
     */
    private RunningAccountListViewAdapter friendsAndRelativesListViewAdapter;
    private int totalMoney = 0, inCount = 0, outCount = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friendsandrelativesitem, null);

        friendsAndRelativesItemPresenter = new FriendsAndRelativesItemPresenter(this, new FriendsAndRelativesItemInteractor());


        initData();
        initView(view);

        return view;
    }

    private void initData() {
        if (friendsAndRelativesListViewAdapter == null) {
            friendsAndRelativesListViewAdapter = new RunningAccountListViewAdapter(getActivity());
        }
    }

    private void initView(View view) {
        friendsAndRelativesItemListview = view.findViewById(R.id.id_friends_item_listview);
        friendsAndRelativesItemListview.setAdapter(friendsAndRelativesListViewAdapter);
        view.findViewById(R.id.id_friends_item_top_right).setOnClickListener(this);
        view.findViewById(R.id.id_friends_item_top_left).setOnClickListener(this);
        txTotalMoney = view.findViewById(R.id.id_friends_item_summoney);
        txInCount = view.findViewById(R.id.id_friends_item_in_count);
        txOutCount = view.findViewById(R.id.id_friends_item_out_count);
        updateTextView();

    }

    @Override
    public void updateData(List<Class> classList) {

    }

    @Override
    public void showAddProjectFragmentDialog(Context context, String tag) {

    }


    @Override
    public void onClick(View view) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void updateView(int totalMoney, int inCount, int outCount) {
        this.totalMoney = totalMoney;
        this.inCount = inCount;
        this.outCount = outCount;
        updateTextView();
    }

    private void updateTextView() {
        if (txOutCount != null && txInCount != null && txOutCount != null) {
            txTotalMoney.setText(totalMoney + "");
            txInCount.setText(inCount + "");
            txOutCount.setText(outCount + "");
        }

    }

    @Override
    public void updateListView(List<ProjectBean> projectBeanList) {
        if (projectBeanList != null) {
            if (friendsAndRelativesListViewAdapter == null) {
                friendsAndRelativesListViewAdapter = new RunningAccountListViewAdapter(getActivity());
            }
            friendsAndRelativesListViewAdapter.setProjectBeanList(projectBeanList);
            friendsAndRelativesListViewAdapter.notifyDataSetChanged();
        }
    }


}