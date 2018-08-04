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

    public interface OnAddProjectInFragmentListener {


        /**
         * 在fragment中添加数据成功
         * @param projectBean
         */
        void onAddProjectInFragmentSuccess(ProjectBean projectBean);

        /**
         * 在fragment中添加数据失败
         */
        void onAddProjectInFragmentError();

    }

}

