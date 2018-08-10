package cn.xiaojii.cashgift.presenter.inter.fragment;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

public interface IProjectTablePresenter {
    /**
     * 当fragment中的listview被点击时进行fragment跳转前的逻辑处理
     * @param position
     */
    void clickListViewItem(int position);

}
