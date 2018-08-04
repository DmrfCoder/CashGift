package cn.xiaojii.cashgift.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.ParcelableListBean;
import cn.xiaojii.cashgift.inter.OnBroadCastListener;

/**
 * @author dmrfcoder
 * @date 2018/8/4
 */

public class DataReceiver extends BroadcastReceiver {

    private OnBroadCastListener onBroadCastListener;

    public DataReceiver(OnBroadCastListener onBroadCastListener) {
        this.onBroadCastListener = onBroadCastListener;
    }

    public DataReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        ParcelableListBean parcelableListBean = (ParcelableListBean) bundle.get(GlobalBean.BROADCAST_TAG);
        onBroadCastListener.onRecevie(parcelableListBean);

    }
}
