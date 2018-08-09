package cn.xiaojii.cashgift.presenter;

import java.util.List;

import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.bean.ProjectBeanMessageBean;
import cn.xiaojii.cashgift.interactor.impl.MainInterator;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public interface IMainPresenter {




    /**
     * 应用初始化时从文件中读取数据
     */
    void initActivityData();

    /**
     * 获取数据给调用者
     * @param onGetDataListener
     */
    void getData(MainInterator.OnGetDataListener onGetDataListener);

    /**
     *
     * 增加流水，参数为泛型
     * @param projectBeanMessageBean
     */
    void addProjectFromEventBus(ProjectBeanMessageBean projectBeanMessageBean);

    /**
     * 存储数据
     */
    void onDestroy();
}
