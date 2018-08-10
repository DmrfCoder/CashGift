package cn.xiaojii.cashgift.presenter.impl;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.List;

import cn.xiaojii.cashgift.bean.ContantsValue;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.bean.ProjectBeanMessageBean;
import cn.xiaojii.cashgift.bean.ProjectListMessageEvent;
import cn.xiaojii.cashgift.bean.UserBean;
import cn.xiaojii.cashgift.interactor.IMainInteractor;
import cn.xiaojii.cashgift.interactor.impl.MainInterator;
import cn.xiaojii.cashgift.presenter.IMainPresenter;
import cn.xiaojii.cashgift.util.ExcelUtil;
import cn.xiaojii.cashgift.view.IMainView;
import cn.xiaojii.cashgift.view.impl.MainActivity;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class MainPresenter implements IMainPresenter, MainInterator.OnInitDataListener,
        MainInterator.OnAddProjectListener, IMainInteractor.ExportExcelListener {
    private IMainView mainView;
    private MainInterator mainInterator;

    private String TAG = "MainPresenter";


    public MainPresenter(IMainView mainView, MainInterator mainInterator) {
        this.mainView = mainView;
        this.mainInterator = mainInterator;
        EventBus.getDefault().register(this);
    }


    @Override
    public void initActivityData() {
        mainInterator.initData(this);
    }

    @Override
    public void getData(MainInterator.OnGetDataListener onGetDataListener) {
        mainInterator.GetData(onGetDataListener);
    }


    @Override
    @Subscribe
    public void addProjectFromEventBus(ProjectBeanMessageBean projectBeanMessageBean) {
        if (projectBeanMessageBean.getTag().equals(ContantsValue.TAG_DIALOGFRAGMENT)) {
            ProjectBean projectBean = projectBeanMessageBean.getProjectBean();
            mainInterator.AddProject(projectBean, this);
        }

    }

    @Override
    public void onDestroy() {
        mainInterator.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    @Subscribe
    public void receiveExportExcel(String code) {
        if (code.equals(ContantsValue.EXPORTEXCEL)) {
            mainInterator.exportExcel(this);
        }
    }


    @Override
    public void OnInitError() {

    }

    @Override
    public void OnInitSuccess(List<ProjectBean> projectBeanList) {
        sendListEventBus(projectBeanList);

    }


    @Override
    public void onAddProjectError() {
        Log.e(TAG, "onAddProjectError");
    }

    @Override
    public void onAddProjectSuccess(List<ProjectBean> list) {
        Log.i(TAG, "onAddProjectSuccess,list size:" + list.size());
        sendListEventBus(list);
    }

    private void sendListEventBus(List list) {
        ProjectListMessageEvent projectListMessageEvent = new ProjectListMessageEvent(list, ContantsValue.TAG_MAINPRESENTER);
        EventBus.getDefault().postSticky(projectListMessageEvent);
    }

    @Override
    public void onExportExcelError() {

    }

    @Override
    public void onExportExcelSuccess(List list, UserBean userBean) {
        File file = new File(ContantsValue.APP_FOLDER_PATH);
        //文件夹是否已经存在
        if (!file.exists()) {
            file.mkdirs();
        }

        String[] title = {"姓名", "项目", "收支", "日期"};
        String fileName = file.toString() + "/" + userBean.getExcelName();
        ExcelUtil.initExcel(fileName, title);
        ExcelUtil.writeObjListToExcel(list, fileName, (MainActivity) mainView);
    }
}
