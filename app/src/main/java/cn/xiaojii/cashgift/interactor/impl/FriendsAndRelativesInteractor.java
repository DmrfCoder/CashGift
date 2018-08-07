package cn.xiaojii.cashgift.interactor.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.bean.FriendsAndRelativesBean;
import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.interactor.IBaseInteractor;
import cn.xiaojii.cashgift.util.ConvertBeanUtil;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class FriendsAndRelativesInteractor implements IBaseInteractor {

    private List<FriendsAndRelativesBean> friendsAndRelativesBeanList;


    public FriendsAndRelativesInteractor() {

    }

    @Override
    public void addProject(ProjectBean projectBean, IBaseInteractor.AddProjectListener addProjectListener) {
        if (projectBean == null) {
            addProjectListener.onAddProjectError();
        }
        if (friendsAndRelativesBeanList == null) {
            friendsAndRelativesBeanList = new ArrayList<>();
            FriendsAndRelativesBean friendsAndRelativesBean = new FriendsAndRelativesBean(projectBean);
            friendsAndRelativesBeanList.add(friendsAndRelativesBean);
        } else {
            boolean findFlag = false;
            for (FriendsAndRelativesBean friendsAndRelativesBean : friendsAndRelativesBeanList) {
                if (friendsAndRelativesBean.getName().equals(projectBean.getName())) {
                    findFlag = true;
                    if (friendsAndRelativesBean.getSumMoney() > 0) {
                        friendsAndRelativesBean.addIn();

                    } else {
                        friendsAndRelativesBean.addOut();

                    }
                    friendsAndRelativesBean.updateSumMoney(friendsAndRelativesBean.getSumMoney());

                }
            }

            if (!findFlag) {
                FriendsAndRelativesBean friendsAndRelativesBean = new FriendsAndRelativesBean(projectBean);
                friendsAndRelativesBeanList.add(friendsAndRelativesBean);

            }
        }
        addProjectListener.onAddProjectSuccess(friendsAndRelativesBeanList, "FriendsAndRelatives", projectBean);

    }

    @Override
    public void initData(List dataList, InitDataListener initDataListener) {
        if (dataList != null) {
            friendsAndRelativesBeanList = ConvertBeanUtil.convertProjectBeansToFriendsAndRelativesBeans(dataList);
            initDataListener.onInitDataSuccess(friendsAndRelativesBeanList);
        } else {
            initDataListener.onInitDataError();
        }
    }

    @Override
    public void updateView(UpdateViewListener updateViewListener) {
        if (friendsAndRelativesBeanList != null) {
            updateViewListener.onUpdateViewSuccess(friendsAndRelativesBeanList);
        } else {
            updateViewListener.onUpdateViewError();
        }
    }

    private void addProject(ProjectBean projectBean) {
        FriendsAndRelativesBean friendsAndRelativesBean = new FriendsAndRelativesBean(projectBean);
        if (friendsAndRelativesBeanList == null) {
            friendsAndRelativesBeanList = new ArrayList<>();
        }
        friendsAndRelativesBeanList.add(friendsAndRelativesBean);
        //这里只要把数据加进去就可以了，不用刷新view，因为此时view对用户不可见
    }


    public interface OnInquireFinishedListener {
        /**
         * 监听查询失败或查询无结果
         */
        void onInquireError();

        /**
         * 监听查询成功
         *
         * @return
         */
        void onInquireSuccess(List<FriendsAndRelativesBean> friendsAndRelativesBeanList);
    }


    public void Inquire(final String name, final OnInquireFinishedListener onInquireFinishedListener) {

        if (name == null) {
            onInquireFinishedListener.onInquireError();
        } else if (name.equals("")) {
            onInquireFinishedListener.onInquireSuccess(friendsAndRelativesBeanList);
        } else {
            List<FriendsAndRelativesBean> list = new ArrayList<>();

            for (FriendsAndRelativesBean friendsAndRelativesBean : friendsAndRelativesBeanList) {
                if (friendsAndRelativesBean.getName().contains(name)) {
                    list.add(friendsAndRelativesBean);
                }
            }
            onInquireFinishedListener.onInquireSuccess(list);
        }
    }

}



