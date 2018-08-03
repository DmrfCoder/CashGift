package cn.xiaojii.cashgift.view.impl;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.RunningAccountBean;
import cn.xiaojii.cashgift.view.IBaseView;


/**
 * @author dmrfcoder
 * @date 2018/8/2
 */

public class BaseFragment extends Fragment implements IBaseView {
    public void showAddprojectDialog(final Context context) {
        Dialog dialog = new Dialog(context);
        View view1 = LayoutInflater.from(context).inflate(
                R.layout.dialog_addproject, null);
        dialog.setContentView(view1);
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

                RunningAccountBean runningAccountBean = new RunningAccountBean();
                runningAccountBean.setName(name);
                runningAccountBean.setMoney(Integer.parseInt(money));
                runningAccountBean.setProject(project);
                ((MainActivity) context).mainPresenter.addProject(runningAccountBean);

            }
        });

        cancel = view1.findViewById(R.id.id_dialog_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        dialog.show();
    }
}
