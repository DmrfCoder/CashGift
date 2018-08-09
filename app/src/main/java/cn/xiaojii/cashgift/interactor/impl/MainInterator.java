package cn.xiaojii.cashgift.interactor.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.bean.UserBean;
import cn.xiaojii.cashgift.interactor.IMainInteractor;
import cn.xiaojii.cashgift.util.JsonToListUtil;
import cn.xiaojii.cashgift.util.ListToJsonUtil;
import cn.xiaojii.cashgift.util.ReadFileToStringUtil;
import cn.xiaojii.cashgift.util.WriteStringToFileUtil;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class MainInterator implements IMainInteractor {
    public MainInterator() {
        projectBeanList = new ArrayList<>();
        userBean = new UserBean();
    }

    private static List<ProjectBean> projectBeanList;
    private UserBean userBean;


    public void onDestroy() {
        String json = ListToJsonUtil.ListToJson(projectBeanList);
        WriteStringToFileUtil.write(json, GlobalBean.filename);
    }

    @Override
    public void exportExcel(ExportExcelListener exportExcelListener) {
        if (projectBeanList == null) {
            exportExcelListener.onExportExcelError();
        } else {
            exportExcelListener.onExportExcelSuccess(projectBeanList,userBean);
        }
    }

    public interface OnInitDataListener {
        void OnInitError();

        void OnInitSuccess(List<ProjectBean> projectBeanList);
    }

    public void initData(OnInitDataListener onInitDataListener) {

        String fileContent = ReadFileToStringUtil.read(GlobalBean.filename);
        List<ProjectBean> projectBeanList = JsonToListUtil.jsonToList(fileContent);

        if (projectBeanList == null) {

            this.projectBeanList = new ArrayList<>();

            onInitDataListener.OnInitError();


        } else {
            this.projectBeanList = projectBeanList;

            onInitDataListener.OnInitSuccess(projectBeanList);


        }

    }


    public interface OnGetDataListener {
        /**
         * 获取收据失败
         */
        void OnGetDataError();

        /**
         * 获取数据包成功
         *
         * @param projectBeanList
         */
        void OnGetDataSuccess(List<ProjectBean> projectBeanList);
    }


    public static void GetData(OnGetDataListener onGetDataListener) {
        if (projectBeanList == null) {
            onGetDataListener.OnGetDataError();
        } else {
            onGetDataListener.OnGetDataSuccess(projectBeanList);
        }
    }

    public interface OnAddProjectListener {
        /**
         * 新增收据失败
         */
        void onAddProjectError();

        /**
         * 新增数据包成功
         */
        void onAddProjectSuccess(List<ProjectBean> list);
    }


    public void AddProject(ProjectBean projectBean, OnAddProjectListener onAddProjectListener) {
        if (projectBean == null) {
            onAddProjectListener.onAddProjectError();
        } else {
            if (projectBeanList == null) {
                projectBeanList = new ArrayList<>();
            }
            projectBeanList.add(projectBean);

            onAddProjectListener.onAddProjectSuccess(projectBeanList);
        }
    }


    public List<ProjectBean> getProjectBeanList() {
        return projectBeanList;
    }
}
