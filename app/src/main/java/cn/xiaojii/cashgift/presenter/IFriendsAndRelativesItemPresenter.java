package cn.xiaojii.cashgift.presenter;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * @author dmrfcoder
 * @date 2018/8/8
 */

public interface IFriendsAndRelativesItemPresenter {


    /**
     * 更新当前item对象的姓名
     * @param name
     */
    void updateTargetName(String name);
}
