package cn.xiaojii.cashgift.util;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.bean.FriendsAndRelativesBean;
import cn.xiaojii.cashgift.bean.RunningAccountBean;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class ConvertBeanUtil {

    public static List<FriendsAndRelativesBean> convertRunningAccountBeanToFriendsAndRelativesBean(List<RunningAccountBean> runningAccountBeanList) {
        List<FriendsAndRelativesBean> friendsAndRelativesBeanList = new ArrayList<>();

        for (RunningAccountBean runningAccountBean : runningAccountBeanList) {
            String name = runningAccountBean.getName();
            boolean find_flag = false;
            for (FriendsAndRelativesBean friendsAndRelativesBean : friendsAndRelativesBeanList) {
                if (friendsAndRelativesBean.getName().equals(name)) {
                    find_flag = true;
                    if (friendsAndRelativesBean.getSumMoney() > 0) {
                        friendsAndRelativesBean.addIn();

                    } else {
                        friendsAndRelativesBean.addOut();

                    }
                    friendsAndRelativesBean.updateSumMoney(friendsAndRelativesBean.getSumMoney());
                }
            }
            if (!find_flag) {
                FriendsAndRelativesBean friendsAndRelativesBean = new FriendsAndRelativesBean(runningAccountBean);
                friendsAndRelativesBeanList.add(friendsAndRelativesBean);

            }
        }

        return friendsAndRelativesBeanList;
    }
}
