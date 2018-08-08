package cn.xiaojii.cashgift.interactor.impl;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.bean.ProjectTableBean;
import cn.xiaojii.cashgift.interactor.IBaseInteractor;

/**
 * @author dmrfcoder
 * @date 2018/8/7
 */

public class ProjectTableInterator implements IBaseInteractor {
    private List<ProjectTableBean> projectTableBeanList;
    private String TAG = "ProjectTableInterator";
    private List<String> nameList;

    public ProjectTableInterator() {
        nameList = new ArrayList<>();
    }

    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private int e = 0;

    private int totalMoney=0;


    @Override
    public void addProject(ProjectBean projectBean, AddProjectListener addProjectListener) {
        if (addSingleProjectBean(projectBean)) {
            addProjectListener.onAddProjectSuccess(projectTableBeanList, null, null);
        } else {
            addProjectListener.onAddProjectError();
        }
    }


    private boolean addSingleProjectBean(ProjectBean projectBean) {

        Log.i(TAG, "addSingleProjectBean");
        if (projectBean == null) {
            return false;
        }

        if (!nameList.contains(projectBean.getName())) {
            nameList.add(projectBean.getName());
            a++;
        }

        boolean findFlag = false;
        if (projectTableBeanList == null) {
            projectTableBeanList = new ArrayList<>();
        } else {
            for (ProjectTableBean projectTableBean : projectTableBeanList) {
                if (projectTableBean.getName().equals(projectBean.getProject())) {
                    if (projectTableBean.hasTargetBean(projectBean)) {
                        return false;
                    }
                    findFlag = true;


                    projectTableBean.addProjectBean(projectBean);

                    totalMoney+=projectBean.getIntMoney();
                    if (projectBean.getIntMoney() < 0) {
                        projectTableBean.updateMoneyOut(projectBean.getIntMoney());
                        d += projectBean.getIntMoney();
                        e++;
                    } else {
                        projectTableBean.updateMoneyIn(projectBean.getIntMoney());
                        b += projectBean.getIntMoney();
                        c++;
                    }
                }
            }
        }

        if (!findFlag) {
            ProjectTableBean projectTableBean = new ProjectTableBean();
            projectTableBean.setName(projectBean.getProject());
            totalMoney+=projectBean.getIntMoney();
            if (projectBean.getIntMoney() < 0) {
                projectTableBean.setSumMoneyOut(projectBean.getIntMoney());
                d += projectBean.getIntMoney();
                e++;
            } else {
                projectTableBean.setSumMoneyIn(projectBean.getIntMoney());
                b += projectBean.getIntMoney();
                c++;
            }

            projectTableBean.addProjectBean(projectBean);


            projectTableBeanList.add(projectTableBean);
        }

        return true;

    }




    @Override
    public void initData(List dataList, InitDataListener initDataListener) {

        //这里的datalist应该是projectbean类型的
        Log.i(TAG, "initData");
        for (Object projectBean : dataList) {
            if (!addSingleProjectBean((ProjectBean) projectBean)) {
                initDataListener.onInitDataError();

            }
        }

        initDataListener.onInitDataSuccess(projectTableBeanList);
    }

    @Override
    public void updateView(UpdateViewListener updateViewListener) {
        if (projectTableBeanList == null) {
            updateViewListener.onUpdateViewError();
            return;
        }

        updateViewListener.onUpdateViewSuccess(projectTableBeanList);


    }


    public interface OnUpdateTopBarDataListener {
        /**
         * 更新顶部数据失败
         */
        void onUpdateTopBarDataError();


        /**
         * 更新顶部数据成功，显示格式为：总结：totalMoney "共：" + a + "人 收礼：" + b + "（" + c + "个） 送礼：" + d + "（" + e + "个）"
         * @param a
         * @param b
         * @param c
         * @param d
         * @param e
         * @param totalMoney
         */
        void onUpdateTopBarDataSuccess(int a, int b, int c, int d, int e,int totalMoney);
    }


    public void updateTopBarData(OnUpdateTopBarDataListener onUpdateTopBarDataListener) {
        if (nameList.size() == 0) {
            onUpdateTopBarDataListener.onUpdateTopBarDataError();
        } else {
            onUpdateTopBarDataListener.onUpdateTopBarDataSuccess(a, b, c, d, e,totalMoney);
        }
    }
}
