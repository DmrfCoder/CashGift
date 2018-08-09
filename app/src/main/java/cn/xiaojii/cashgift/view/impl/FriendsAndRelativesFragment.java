package cn.xiaojii.cashgift.view.impl;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.List;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.adapter.FriendAndRelativesListViewAdapter;
import cn.xiaojii.cashgift.base.BaseFragment;
import cn.xiaojii.cashgift.bean.FriendsAndRelativesBean;
import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.interactor.impl.FriendsAndRelativesInteractor;
import cn.xiaojii.cashgift.presenter.impl.FriendsAndRelativesPresenter;
import cn.xiaojii.cashgift.inter.IBaseFragmentView;
import cn.xiaojii.cashgift.view.IFriendsAndRelativesView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

@SuppressLint("ValidFragment")
public class FriendsAndRelativesFragment extends BaseFragment implements IFriendsAndRelativesView, IBaseFragmentView,
        View.OnClickListener, AdapterView.OnItemClickListener,
        TextWatcher {
    private FriendsAndRelativesPresenter friendsAndRelativesPresenter;
    private ListView friendsAndRelativesListView;
    private FriendAndRelativesListViewAdapter friendAndRelativesListViewAdapter;
    private EditText editTextInquire;
    private Button buttonInquire;


    private String TAG = "FriendsAndRelativesFragment";


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friendsandrelatives, null);

        initFragment(view);


        return view;
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void initFragment(View view) {

        /*
            初始化listview
         */
        friendsAndRelativesListView = view.findViewById(R.id.id_friends_listview);
        friendAndRelativesListViewAdapter = new FriendAndRelativesListViewAdapter(getActivity());
        friendsAndRelativesListView.setAdapter(friendAndRelativesListViewAdapter);

        friendsAndRelativesListView.setOnItemClickListener(this);

        editTextInquire = view.findViewById(R.id.id_search_et);
        editTextInquire.addTextChangedListener(this);
        editTextInquire.setOnClickListener(this);

        buttonInquire = view.findViewById(R.id.id_search_bt);

        view.findViewById(R.id.id_search_bt).setOnClickListener(this);
        view.findViewById(R.id.id_friends_top_right).setOnClickListener(this);
        friendsAndRelativesPresenter = new FriendsAndRelativesPresenter(this, new FriendsAndRelativesInteractor());


        friendsAndRelativesPresenter.updateView();

    }


    @Override
    public void updateListView(List<FriendsAndRelativesBean> friendsAndRelativesBeanList) {
        if (friendsAndRelativesBeanList == null) {
            return;
        }
        friendAndRelativesListViewAdapter.setFriendsAndRelativesBeanList(friendsAndRelativesBeanList);
        friendAndRelativesListViewAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_friends_top_right:
                showAddProjectFragmentDialog(getActivity(), getClass().getName());
                break;
            case R.id.id_search_bt:
                break;
            case R.id.id_search_et:
                buttonInquire.setVisibility(View.VISIBLE);
                break;
            default:
                break;

        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()) {
            case R.id.id_friends_listview:

                friendsAndRelativesPresenter.clickListViewItem(position);
                break;
            default:
                break;
        }

    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Log.i(TAG, "beforeTextChanged");
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        friendsAndRelativesPresenter.inquireFriendsAndRelatives(charSequence.toString());

    }

    @Override
    public void afterTextChanged(Editable editable) {
        Log.i(TAG, "afterTextChanged");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        friendsAndRelativesPresenter.onDestroy();
    }


}
