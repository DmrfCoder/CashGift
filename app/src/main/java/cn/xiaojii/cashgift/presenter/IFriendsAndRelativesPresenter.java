package cn.xiaojii.cashgift.presenter;

import java.util.List;

import cn.xiaojii.cashgift.bean.FriendsAndRelativesBean;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public interface IFriendsAndRelativesPresenter {


    /**
     * inquire Friends And Relatives
     * @param name
     * @return
     */
    List<FriendsAndRelativesBean> inquireFriendsAndRelatives(String name);

    /**
     * update listview data order by code
     * @param Code
     */
    boolean updateOrder(int Code);

}
