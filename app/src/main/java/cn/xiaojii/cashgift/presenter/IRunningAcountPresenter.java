package cn.xiaojii.cashgift.presenter;

import java.util.List;

import cn.xiaojii.cashgift.bean.FriendsAndRelativesBean;
import cn.xiaojii.cashgift.bean.RunningAccountBean;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public interface IRunningAcountPresenter {


    /**
     * 新增流水帐，新增完之后调用view的update listview
     * @param runningAccountBean
     */
    void addRunningAcount(RunningAccountBean runningAccountBean);

}
