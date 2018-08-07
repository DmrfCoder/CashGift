package cn.xiaojii.cashgift.view;


import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.inter.IBaseFragmentView;

/**
 * @author dmrfcoder
 */
public interface IMainView {
    /**
     * jump to target fragment
     *
     * @param targetFragment
     */
    void startfragment(IBaseFragmentView targetFragment);


}

