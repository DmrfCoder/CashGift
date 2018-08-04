package cn.xiaojii.cashgift.inter;

import cn.xiaojii.cashgift.bean.ParcelableListBean;

/**
 * @author dmrfcoder
 * @date 2018/8/4
 */

public interface OnBroadCastListener {
    /**
     * 接收到广播后调用。
     */
    void onRecevie(ParcelableListBean parcelableListBean);


}
