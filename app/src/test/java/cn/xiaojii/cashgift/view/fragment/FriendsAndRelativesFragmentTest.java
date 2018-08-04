package cn.xiaojii.cashgift.view.fragment;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowDialog;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import cn.xiaojii.cashgift.BuildConfig;
import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.view.impl.FriendsAndRelativesFragment;
import cn.xiaojii.cashgift.view.impl.MainActivity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author dmrfcoder
 * @date 2018/8/4
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class FriendsAndRelativesFragmentTest {
    private MainActivity mainActivity;
    private FriendsAndRelativesFragment friendsAndRelativesFragment;
    private Button right;


    @Before
    public void setUp() {
        ShadowLog.stream = System.out;
        mainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void testAddProject() {
        friendsAndRelativesFragment = new FriendsAndRelativesFragment();
        SupportFragmentTestUtil.startFragment(friendsAndRelativesFragment);
        View view = friendsAndRelativesFragment.getView();
        right=view.findViewById(R.id.id_friends_top_right);


        Dialog dialog = ShadowDialog.getLatestDialog();

        // 判断Dialog尚未弹出
        assertNull(dialog);

        right.performClick();

        dialog = ShadowDialog.getLatestDialog();
        // 判断Dialog已经弹出
        assertNotNull(dialog);

        Button ok, cancel;
        final EditText et_name, et_project, et_money;
        final RadioGroup inOrOutRg;

        et_name = dialog.findViewById(R.id.id_dialog_et_name);
        et_project = dialog.findViewById(R.id.id_dialog_et_project);
        et_money = dialog.findViewById(R.id.id_dialog_et_money);
        inOrOutRg = dialog.findViewById(R.id.id_dialog_inout);

        ok = dialog.findViewById(R.id.id_dialog_ok);
        et_name.setText("张三");
        et_project.setText("婚礼");
        et_money.setText("800");

        ok.performClick();


    }
}
