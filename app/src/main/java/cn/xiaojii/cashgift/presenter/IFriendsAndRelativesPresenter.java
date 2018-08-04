package cn.xiaojii.cashgift.presenter;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public interface IFriendsAndRelativesPresenter {


    /**
     * inquire Friends And Relatives
     *
     * @param name
     * @return
     */
    void inquireFriendsAndRelatives(String name);

    /**
     * update listview data order by code
     *
     * @param Code
     */
    boolean updateOrder(int Code);





}
