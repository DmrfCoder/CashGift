package cn.xiaojii.cashgift.view;


import android.support.v4.app.Fragment;

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
     * @param addToBackStack
     */
    void startfragment(Fragment targetFragment,boolean addToBackStack);


    /**
     * 底部tab切换
     * @param position
     */
    void onTabChanged(int position);
}

