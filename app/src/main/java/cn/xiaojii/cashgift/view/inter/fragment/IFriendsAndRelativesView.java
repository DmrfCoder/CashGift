package cn.xiaojii.cashgift.view.inter.fragment;

import java.util.List;

import cn.xiaojii.cashgift.bean.fragment.FriendsAndRelativesBean;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public interface IFriendsAndRelativesView {
    /**
     * update the listview on FriendsAndRelativesView
     *
     * @param friendsAndRelativesBeanList
     */
    void updateListView(List<FriendsAndRelativesBean> friendsAndRelativesBeanList);



}
