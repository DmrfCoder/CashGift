package cn.xiaojii.cashgift.interactor;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.RunningAccountBean;
import cn.xiaojii.cashgift.util.JsonToListUtil;
import cn.xiaojii.cashgift.util.ReadFileToStringUtil;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class MainInterator {
    public MainInterator() {
        runningAccountBeanList = new ArrayList<>();
    }

    private List<RunningAccountBean> runningAccountBeanList;

    public List<RunningAccountBean> getRunningAccountBeanList() {
        return runningAccountBeanList;
    }

    public void setRunningAccountBeanList(List<RunningAccountBean> runningAccountBeanList) {
        this.runningAccountBeanList = runningAccountBeanList;
    }

    public interface OnAddProjrctListener {
        /**
         * 增加流水账失败时调用
         */
        void onAddError();

        /**
         * 增加流水账成功时调用，传入Bean对象（因为一次增加一个，所以传入单个Bean）
         *
         * @param runningAccountBeanList
         */
        void onAddSuccess(List<RunningAccountBean> runningAccountBeanList);
    }

    public void addRunningAccount(RunningAccountBean runningAccountBean, OnAddProjrctListener onAddProjrctListener) {
        if (runningAccountBean == null) {
            onAddProjrctListener.onAddError();
        } else {
            runningAccountBeanList.add(runningAccountBean);
            onAddProjrctListener.onAddSuccess(runningAccountBeanList);
        }

    }


    public interface OnInitDataListener {
        void OnInitError();

        void OnInitSuccess(List<RunningAccountBean> runningAccountBeanList);
    }

    public void initData(Context context, OnInitDataListener onInitDataListener) {

        String fileContent = ReadFileToStringUtil.Read(context, GlobalBean.filename);
        List<RunningAccountBean> runningAccountBeanList = JsonToListUtil.jsonToList(fileContent);

        if (runningAccountBeanList == null) {
            onInitDataListener.OnInitError();
            this.runningAccountBeanList = new ArrayList<>();
        } else {
            this.runningAccountBeanList = runningAccountBeanList;
            onInitDataListener.OnInitSuccess(runningAccountBeanList);
        }

    }
}
