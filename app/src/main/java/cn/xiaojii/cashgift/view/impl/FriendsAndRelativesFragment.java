package cn.xiaojii.cashgift.view.impl;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
import cn.xiaojii.cashgift.bean.ParcelableListBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.inter.OnBroadCastListener;
import cn.xiaojii.cashgift.interactor.impl.FriendsAndRelativesInteractor;
import cn.xiaojii.cashgift.interactor.impl.MainInterator;
import cn.xiaojii.cashgift.presenter.IMainPresenter;
import cn.xiaojii.cashgift.presenter.impl.FriendsAndRelativesPresenter;
import cn.xiaojii.cashgift.receiver.DataReceiver;
import cn.xiaojii.cashgift.inter.IBaseFragmentView;
import cn.xiaojii.cashgift.view.IFriendsAndRelativesView;
import cn.xiaojii.cashgift.view.IMainView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

@SuppressLint("ValidFragment")
public class FriendsAndRelativesFragment extends Fragment implements IFriendsAndRelativesView, IBaseFragmentView,
        View.OnClickListener, AdapterView.OnItemClickListener,
        TextWatcher ,OnBroadCastListener{
    private FriendsAndRelativesPresenter friendsAndRelativesPresenter;
    private ListView friendsAndRelativesListView;
    private FriendAndRelativesListViewAdapter friendAndRelativesListViewAdapter;
    private IMainView.OnAddProjectInFragmentListener onAddProjectInFragmentListener;
    private EditText editTextInquire;
    private Button buttonInquire;
    private DataReceiver dataReceiver;


    private String TAG = "FriendsAndRelativesFragment";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IMainView.OnAddProjectInFragmentListener) {
            onAddProjectInFragmentListener = (IMainView.OnAddProjectInFragmentListener) context;
        }
    }

    public FriendsAndRelativesFragment() {
        friendsAndRelativesPresenter = new FriendsAndRelativesPresenter(this, new FriendsAndRelativesInteractor());
    }

    public FriendsAndRelativesFragment(IMainPresenter mainPresenter) {
        friendsAndRelativesPresenter = new FriendsAndRelativesPresenter(this, new FriendsAndRelativesInteractor());
        mainPresenter.getData(new MainInterator.OnGetDataListener() {
            @Override
            public void OnGetDataError() {
                friendsAndRelativesPresenter.initFragmentData(null);
            }

            @Override
            public void OnGetDataSuccess(List<ProjectBean> projectBeanList) {
                friendsAndRelativesPresenter.initFragmentData(projectBeanList);
            }
        });
        dataReceiver=new DataReceiver(this);
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friendsandrelatives, null);

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

        editTextInquire = view.findViewById(R.id.id_search_et);
        editTextInquire.addTextChangedListener(this);
        editTextInquire.setOnClickListener(this);

        buttonInquire = view.findViewById(R.id.id_search_bt);

        view.findViewById(R.id.id_search_bt).setOnClickListener(this);
        view.findViewById(R.id.id_friends_top_right).setOnClickListener(this);

        friendsAndRelativesPresenter.updateView();

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
                showDialog(getActivity());
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
                //position就是点击的item的下标
                break;
        }

    }


    @Override
    public void updateData(List<Class> classList) {

    }

    @Override
    public void showDialog(Context context) {
        final Dialog dialog = new Dialog(context);
        View view1 = LayoutInflater.from(context).inflate(
                R.layout.dialog_addproject, null);
        dialog.setContentView(view1);

        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay();
        final android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();
        p.width = (int) (d.getWidth() * 0.9);
        dialog.getWindow().setAttributes(p);

        Button ok, cancel;
        final EditText et_name, et_project, et_money;
        final RadioGroup inOrOutRg;

        et_name = view1.findViewById(R.id.id_dialog_et_name);
        et_project = view1.findViewById(R.id.id_dialog_et_project);
        et_money = view1.findViewById(R.id.id_dialog_et_money);
        inOrOutRg = view1.findViewById(R.id.id_dialog_inout);

        ok = view1.findViewById(R.id.id_dialog_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                String project = et_project.getText().toString();
                String money = et_money.getText().toString();
                GlobalBean.inOrOut inOrOut;
                if (inOrOutRg.getCheckedRadioButtonId() == R.id.id_dialog_in) {
                    inOrOut = GlobalBean.inOrOut.IN;
                } else {
                    inOrOut = GlobalBean.inOrOut.OUT;
                }

                ProjectBean projectBean = new ProjectBean();
                projectBean.setName(name);
                if (inOrOut == GlobalBean.inOrOut.IN) {
                    projectBean.setMoney(Math.abs(Integer.parseInt(money)));
                } else {
                    projectBean.setMoney(-Math.abs(Integer.parseInt(money)));
                }
                projectBean.setProject(project);
                friendsAndRelativesPresenter.addProject(projectBean);
                onAddProjectInFragmentListener.onAddProjectInFragmentSuccess(projectBean);
                dialog.dismiss();

            }
        });

        cancel = view1.findViewById(R.id.id_dialog_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

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
    public void onRecevie(ParcelableListBean parcelableListBean) {
        friendsAndRelativesPresenter.initFragmentData(parcelableListBean.getProjectBeanList());
    }
}
