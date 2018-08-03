package cn.xiaojii.cashgift.presenter;

import java.util.List;

import cn.xiaojii.cashgift.bean.FriendsAndRelativesBean;
import cn.xiaojii.cashgift.bean.RunningAccountBean;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public interface IMainPresenter {


    /**
     * 新增流水帐，新增完之后调用view的update listview
     *
     * @param runningAccountBean
     */
    void addProject(RunningAccountBean runningAccountBean);

    /**
     * 应用初始化时从文件中读取数据
     */
    void initData();

    /**
     * 从MainInterator中获取数据
     * @return
     */
    List<RunningAccountBean> getData();

}
