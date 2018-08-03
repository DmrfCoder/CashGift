package cn.xiaojii.cashgift.interactor;

import java.util.List;

import cn.xiaojii.cashgift.bean.FriendsAndRelativesBean;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class FriendsAndRelativesInteractor {
    public interface OnInquireFinishedListener {
        /**
         * 监听查询失败或查询无结果
         */
        void onInquireError();

        /**
         * 监听查询成功
         *
         * @return
         */
        void onInquireSuccess(List<FriendsAndRelativesBean> friendsAndRelativesBeanList);
    }

    private void Inquire(final String name, final OnInquireFinishedListener onInquireFinishedListener) {

    }
}
