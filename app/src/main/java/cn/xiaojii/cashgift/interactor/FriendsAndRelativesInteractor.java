package cn.xiaojii.cashgift.interactor;

import android.widget.ListView;

import java.util.ArrayList;
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

    public interface InitFriendsAndRelativesListener {
        /**
         * 监听更新数据失败
         */
        void onInitFriendsAndRelativesError();

        /**
         * 监听更新数据成功
         * @param friendsAndRelativesBeanList
         */
        void onInitFriendsAndRelativesSuccess(List<FriendsAndRelativesBean> friendsAndRelativesBeanList);

    }

    public void Inquire(final String name, final OnInquireFinishedListener onInquireFinishedListener) {

    }

    public void InitFriendsAndRelativesListView(final InitFriendsAndRelativesListener initFriendsAndRelativesListener){




    }



}
