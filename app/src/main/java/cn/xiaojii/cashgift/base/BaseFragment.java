package cn.xiaojii.cashgift.base;

import android.app.Instrumentation;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import cn.xiaojii.cashgift.view.impl.AddProjectDialogFragment;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

public class BaseFragment extends Fragment {

    public void onBack() {
        Instrumentation inst = new Instrumentation();
        inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
    }

    public void showAddProjectFragmentDialog(Context context, String tag) {
        AddProjectDialogFragment addProjectDialogFragment = new AddProjectDialogFragment();
        addProjectDialogFragment.show(getFragmentManager(), tag);
    }
}
