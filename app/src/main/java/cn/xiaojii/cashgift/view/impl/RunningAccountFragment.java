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
public class RunningAccountFragment extends Fragment implements View.OnClickListener,IRunningAccountView, IBaseFragmentView {

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
        runningAccountPresenter=new RunningAccountPresenter(this,new RunningAccountInteractor());

        initView(view);
        return view;
    }

    private void initView(View view) {
        runningAccountListView=view.findViewById(R.id.id_listview_runningaccount);
        if (runningAccountListViewAdapter==null){
            runningAccountListViewAdapter=new RunningAccountListViewAdapter(getActivity());
        }
        runningAccountListView.setAdapter(runningAccountListViewAdapter);
        view.findViewById(R.id.id_runningaccount_top_right).setOnClickListener(this);

    }

    @Override
    public void updateListView(List<ProjectBean> projectBeanList) {
        if (runningAccountListViewAdapter==null){
            runningAccountListViewAdapter=new RunningAccountListViewAdapter(getActivity());
        }
        runningAccountListViewAdapter.setProjectBeanList(projectBeanList);
        runningAccountListViewAdapter.notifyDataSetChanged();
    }



    @Override
    public void updateData(List<Class> classList) {

    }

    @Override
    public void showAddProjectFragmentDialog(Context context, String tag) {

    }

   // @Override
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
                runningAccountPresenter.addProjectFromDialog(projectBean);
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_runningaccount_top_right:
                showDialog(getActivity());
                break;
                default:
                    break;
        }

    }
}
