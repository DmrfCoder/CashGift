package cn.xiaojii.cashgift.presenter.inter.base;

import cn.xiaojii.cashgift.bean.fragment.ProjectBean;
import cn.xiaojii.cashgift.bean.message.ProjectListMessageBean;

/**
 * @author dmrfcoder
 * @date 2018/8/4
 */

public interface IBaseFragmentPresenter {

    /**
     *
     * 从dialog中增加条目，参数为泛型,
     * @param projectBean
     */
    void addProjectFromDialog(ProjectBean projectBean);



    /**
     * 从MainInteractor eventbus接收数据进行初始化
     * @param projectListMessageBean
     */

    void initDataFromMainInteractor(ProjectListMessageBean projectListMessageBean);

    /**
     * 从model拿到数据更新视图
     */
    void updateView();

    /**
     * 当Fragment进入后台模式时调用
     */
    void onPause();


    /**
     * view销毁时调用
     */
    void onDestroy();
}
