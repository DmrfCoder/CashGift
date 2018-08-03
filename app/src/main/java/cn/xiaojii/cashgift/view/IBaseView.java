package cn.xiaojii.cashgift.view;

import java.util.List;

import cn.xiaojii.cashgift.bean.RunningAccountBean;

/**
 * @author dmrfcoder
 * @date 2018/8/2
 */

public interface IBaseView {
    void updateData(List<RunningAccountBean> runningAccountBeanList);
}
