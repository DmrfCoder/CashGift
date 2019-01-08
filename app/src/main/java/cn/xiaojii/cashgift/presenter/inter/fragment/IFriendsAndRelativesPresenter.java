package cn.xiaojii.cashgift.presenter.inter.fragment;

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
     * @return boolean
     */
    boolean updateOrder(int Code);


    /**
     * 当fragment中的listview被点击时进行fragment跳转前的逻辑处理
     * @param position
     */
    void clickListViewItem(int position);







}
