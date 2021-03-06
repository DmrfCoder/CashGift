package cn.xiaojii.cashgift.interactor.impl;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.bean.fragment.FriendsAndRelativesBean;
import cn.xiaojii.cashgift.bean.fragment.ProjectBean;
import cn.xiaojii.cashgift.interactor.inter.base.IBaseFragmentInteractor;
import cn.xiaojii.cashgift.interactor.inter.fragment.IFriendsAndRelativesInteractor;
import cn.xiaojii.cashgift.util.ConvertBeanUtil;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class FriendsAndRelativesFragmentInteractor implements IBaseFragmentInteractor, IFriendsAndRelativesInteractor {

    private List<FriendsAndRelativesBean> friendsAndRelativesBeanList;
    private List<ProjectBean> projectBeanList;


    public FriendsAndRelativesFragmentInteractor() {

    }

    @Override
    public void addProject(ProjectBean projectBean, IBaseFragmentInteractor.AddProjectListener addProjectListener) {
        if (projectBean == null) {
            addProjectListener.onAddProjectError();
        }
        projectBeanList.add(projectBean);
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
        addProjectListener.onAddProjectSuccess(friendsAndRelativesBeanList);

    }




    @Override
    public void updateView(UpdateFarViewListener updateViewListener) {
        if (friendsAndRelativesBeanList != null) {
            updateViewListener.onUpdateViewSuccess(friendsAndRelativesBeanList);
        } else {
            updateViewListener.onUpdateViewError();
        }
    }

    @Override
    public void initData(List dataList, UpdateFarViewListener updateFarViewListener) {
        if (dataList != null) {
            projectBeanList = dataList;
            friendsAndRelativesBeanList = ConvertBeanUtil.convertProjectBeansToFriendsAndRelativesBeans(dataList);
            updateFarViewListener.onUpdateViewSuccess(friendsAndRelativesBeanList);
        } else {
            updateFarViewListener.onUpdateViewError();
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




    @Override
    public void clickListViewItem(int position, NeedPositionNameListener needPositionNameListener) {
        if (position >= friendsAndRelativesBeanList.size()) {
            needPositionNameListener.onNeedPositionNameError();
        } else {
            needPositionNameListener.onNeedPositionNameSuccess(friendsAndRelativesBeanList.get(position).getName());
        }
    }


}



