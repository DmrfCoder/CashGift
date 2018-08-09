package cn.xiaojii.cashgift.view.impl;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import org.greenrobot.eventbus.EventBus;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.bean.ProjectBeanMessageBean;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

public class AddProjectDialogFragment extends DialogFragment  {
    Button ok, cancel;
    EditText et_name, et_project, et_money;
    RadioGroup inOrOutRg;


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.9), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_addproject, container);
        initDialogFragmentView(view);


        return view;
    }

    private void initDialogFragmentView(View view1) {
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
                ProjectBeanMessageBean projectBeanMessageBean=new ProjectBeanMessageBean(projectBean,GlobalBean.TAG_DIALOGFRAGMENT);
                EventBus.getDefault().post(projectBeanMessageBean);


                dismissSelf();

            }
        });

        cancel = view1.findViewById(R.id.id_dialog_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissSelf();
            }
        });
    }


    private void dismissSelf() {
        this.dismiss();


    }
}
