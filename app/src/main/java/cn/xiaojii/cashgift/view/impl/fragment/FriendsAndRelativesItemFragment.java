package cn.xiaojii.cashgift.view.impl.fragment;

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
import cn.xiaojii.cashgift.adapter.RunningAccountListViewAdapter;
import cn.xiaojii.cashgift.view.impl.base.BaseFragment;
import cn.xiaojii.cashgift.bean.fragment.ProjectBean;
import cn.xiaojii.cashgift.interactor.impl.FriendsAndRelativesItemInteractor;
import cn.xiaojii.cashgift.presenter.impl.fragment.FriendsAndRelativesItemFragmentPresenter;
import cn.xiaojii.cashgift.view.inter.fragment.IFriendsAndRelativesItemView;

/**
 * @author dmrfcoder
 * @date 2018/8/8
 */

public class FriendsAndRelativesItemFragment extends BaseFragment implements IFriendsAndRelativesItemView, View.OnClickListener {


    private FriendsAndRelativesItemFragmentPresenter friendsAndRelativesItemPresenter;
    private ListView friendsAndRelativesItemListview;
    private TextView txTotalMoney, txInCount, txOutCount, txName;

    /**
     * RunningAccountListViewAdapter就是此处需要的adapter
     */
    private RunningAccountListViewAdapter friendsAndRelativesListViewAdapter;
    private int totalMoney = 0, inCount = 0, outCount = 0;
    private String name = "";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friendsandrelativesitem, null);

        friendsAndRelativesItemPresenter = new FriendsAndRelativesItemFragmentPresenter(this, new FriendsAndRelativesItemInteractor());

        initFragment(view);


        return view;
    }


    @Override
    public void initFragment(View view) {
        if (friendsAndRelativesListViewAdapter == null) {
            friendsAndRelativesListViewAdapter = new RunningAccountListViewAdapter(getActivity());
        }

        friendsAndRelativesItemListview = view.findViewById(R.id.id_friends_item_listview);
        friendsAndRelativesItemListview.setAdapter(friendsAndRelativesListViewAdapter);
        view.findViewById(R.id.id_friends_item_top_right).setOnClickListener(this);
        view.findViewById(R.id.id_friends_item_top_left).setOnClickListener(this);
        txTotalMoney = view.findViewById(R.id.id_friends_item_summoney);
        txInCount = view.findViewById(R.id.id_friends_item_in_count);
        txOutCount = view.findViewById(R.id.id_friends_item_out_count);
        txName = view.findViewById(R.id.id_friends_item_top_center);
        updateTextView();


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_friends_item_top_right:
                showAddProjectFragmentDialog(getActivity(), getClass().getName());
                break;
            case R.id.id_friends_item_top_left:

                break;
            default:
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void updateView(int totalMoney, int inCount, int outCount, String name) {
        this.totalMoney = totalMoney;
        this.inCount = inCount;
        this.outCount = outCount;
        this.name = name;
        updateTextView();
    }

    @Override
    public void updateTextView() {
        if (txOutCount != null && txInCount != null && txOutCount != null) {
            txTotalMoney.setText(totalMoney + "");
            txInCount.setText(inCount + "");
            txOutCount.setText(outCount + "");
            txName.setText(name);
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
