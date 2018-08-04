package cn.xiaojii.cashgift.interactor.impl;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.bean.FriendsAndRelativesBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.interactor.IBaseInteractor;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class FriendsAndRelativesInteractor implements IBaseInteractor {

    private List<FriendsAndRelativesBean> friendsAndRelativesBeanList;


    @Override
    public void addProject(ProjectBean projectBean, IBaseInteractor.AddProjectListener addProjectListener) {
        if (projectBean == null) {
            addProjectListener.onAddProjectError();
        }
        if (friendsAndRelativesBeanList == null) {
            friendsAndRelativesBeanList = new ArrayList<>();
            FriendsAndRelativesBean friendsAndRelativesBean = new FriendsAndRelativesBean(projectBean);
            friendsAndRelativesBeanList.add(friendsAndRelativesBean);
            addProjectListener.onAddProjectSuccess(friendsAndRelativesBeanList);
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
                    addProjectListener.onAddProjectSuccess(friendsAndRelativesBeanList);

                }
            }

            if (!findFlag) {
                FriendsAndRelativesBean friendsAndRelativesBean = new FriendsAndRelativesBean(projectBean);
                friendsAndRelativesBeanList.add(friendsAndRelativesBean);
                addProjectListener.onAddProjectSuccess(friendsAndRelativesBeanList);
            }
        }

    }

    @Override
    public void initData(List dataList, InitDataListener initDataListener) {
        if (dataList != null) {
            friendsAndRelativesBeanList = dataList;
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

    }


}
