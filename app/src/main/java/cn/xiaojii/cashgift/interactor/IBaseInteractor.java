package cn.xiaojii.cashgift.interactor;

import java.util.List;

import cn.xiaojii.cashgift.bean.FriendsAndRelativesBean;
import cn.xiaojii.cashgift.bean.ProjectBean;

/**
 * @author dmrfcoder
 * @date 2018/8/4
 */

public interface IBaseInteractor {

    public interface AddProjectListener {
        /**
         * 添加失败
         */
        void onAddProjectError();


        /**
         * 添加成功
         *
         * @param beanList
         * @param BroadCastTag
         */
        void onAddProjectSuccess(List beanList, String BroadCastTag, ProjectBean projectBean);
    }


    /**
     * 增加数据
     *
     * @param projectBean
     */
    void addProject(ProjectBean projectBean, AddProjectListener addProjectListener);


    public interface InitDataListener {
        /**
         * 监听更新数据失败
         */
        void onInitDataError();

        /**
         * 监听更新数据成功
         *
         * @param dataList
         */
        void onInitDataSuccess(List dataList);

    }

    /**
     * @param dataList
     */
    void initData(List dataList, InitDataListener initDataListener);


    public interface UpdateViewListener {
        /**
         * 监听更新数据失败
         */
        void onUpdateViewError();

        /**
         * 监听更新数据成功
         *
         * @param dataList
         */
        void onUpdateViewSuccess(List dataList);

    }

    /**
     * @param updateViewListener
     */
    void updateView(UpdateViewListener updateViewListener);


    public interface ClickListviewItemListener {
        /**
         * 点击item发生错误
         */
        void onClickItemError();

        /**
         * 逻辑处理成功，返回对应信息
         * @param dataList
         */
        void onCliskItemSuccess(List dataList);
    }

    /**
     * 处理item的点击逻辑
     * @param name
     */
    void clickListViewItem(String name,ClickListviewItemListener clickListviewItemListener);
}
