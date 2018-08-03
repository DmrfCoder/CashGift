package cn.xiaojii.cashgift.view.impl;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.List;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.adapter.FriendAndRelativesListViewAdapter;
import cn.xiaojii.cashgift.bean.FriendsAndRelativesBean;
import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.RunningAccountBean;
import cn.xiaojii.cashgift.interactor.FriendsAndRelativesInteractor;
import cn.xiaojii.cashgift.presenter.impl.FriendsAndRelativesPresenter;
import cn.xiaojii.cashgift.util.ConvertBeanUtil;
import cn.xiaojii.cashgift.view.IFriendsAndRelativesView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class FriendsAndRelativesFragment extends BaseFragment implements IFriendsAndRelativesView, View.OnClickListener, AdapterView.OnItemClickListener {
    private FriendsAndRelativesPresenter friendsAndRelativesPresenter;
    private ListView friendsAndRelativesListView;
    private FriendAndRelativesListViewAdapter friendAndRelativesListViewAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friendsandrelatives, null);
        friendsAndRelativesPresenter = new FriendsAndRelativesPresenter(this, new FriendsAndRelativesInteractor());
        initView(view);


        return view;
    }

    private void initView(View view) {

        /*
            初始化listview
         */
        friendsAndRelativesListView = view.findViewById(R.id.id_friends_listview);
        friendAndRelativesListViewAdapter = new FriendAndRelativesListViewAdapter(getActivity());
        friendsAndRelativesListView.setAdapter(friendAndRelativesListViewAdapter);
        friendsAndRelativesPresenter.initFriendsAndRelativesListView();

        view.findViewById(R.id.id_search_bt).setOnClickListener(this);
        view.findViewById(R.id.id_friends_top_right).setOnClickListener(this);


    }


    @Override
    public void updateListView(List<FriendsAndRelativesBean> friendsAndRelativesBeanList) {
        friendAndRelativesListViewAdapter.setFriendsAndRelativesBeanList(friendsAndRelativesBeanList);
        friendAndRelativesListViewAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_friends_top_right:
                showAddprojectDialog(getActivity());
                break;
            case R.id.id_search_bt:
                break;
            default:
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()) {
            case R.id.id_friends_listview:
                //position就是点击的item的下标
                break;
        }

    }


    @Override
    public void updateData(List<RunningAccountBean> runningAccountBeanList) {
        super.updateData(runningAccountBeanList);

        updateListView(ConvertBeanUtil.convertRunningAccountBeanToFriendsAndRelativesBean(runningAccountBeanList));

    }
}
