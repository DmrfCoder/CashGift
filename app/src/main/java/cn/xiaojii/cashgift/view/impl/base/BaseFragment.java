package cn.xiaojii.cashgift.view.impl.base;

import android.app.Instrumentation;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;

import cn.xiaojii.cashgift.view.impl.fragment.AddProjectDialogFragment;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

public abstract class BaseFragment extends Fragment {

    public void onBack() {
        Instrumentation inst = new Instrumentation();
        inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
    }

    public void showAddProjectFragmentDialog(Context context, String tag) {
        AddProjectDialogFragment addProjectDialogFragment = new AddProjectDialogFragment();
        addProjectDialogFragment.show(getFragmentManager(), tag);
    }


    /**
     * 初始化fragment
     * @param view
     */
    public abstract void initFragment(View view);
}
