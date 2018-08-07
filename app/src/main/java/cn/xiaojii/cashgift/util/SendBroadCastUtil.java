package cn.xiaojii.cashgift.util;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.view.impl.MainActivity;

/**
 * @author dmrfcoder
 * @date 2018/8/7
 */

public class SendBroadCastUtil {

    public static void sendNeedDataBC(Fragment fragment) {
        Intent intent = new Intent(GlobalBean.NORMAR_ACTION);
        Bundle bundle = new Bundle();
        bundle.putString(GlobalBean.BROADCAST_NEED_DATA_KEY, GlobalBean.BROADCAST_NEED_DATA);
        intent.putExtras(bundle);
        fragment.getActivity().sendBroadcast(intent);
    }


    public static void sendDataBC(MainActivity mainActivity, List<ProjectBean> projectBeans) {
        Intent intent = new Intent(GlobalBean.NORMAR_ACTION2);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(GlobalBean.BROADCAST_BEAN_LIST_KEY, (ArrayList<? extends Parcelable>) projectBeans);
        intent.putExtras(bundle);
        mainActivity.sendBroadcast(intent);
    }

    public static void sendAddProjectBC(Fragment fragment, ProjectBean projectBean,String fragmentName) {
        Intent intent = new Intent(GlobalBean.NORMAR_ACTION3);
        Bundle bundle = new Bundle();
        bundle.putParcelable(GlobalBean.BROADCAST_ADD_PROJECT_BEAN_KEY, projectBean);
        bundle.putString(GlobalBean.BROADCAST_ADD_PROJECT_FRAGMENT_NAME_KEY,fragmentName);
        intent.putExtras(bundle);
        fragment.getActivity().sendBroadcast(intent);
    }
}
