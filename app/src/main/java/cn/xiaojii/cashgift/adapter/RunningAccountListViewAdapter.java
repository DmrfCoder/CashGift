package cn.xiaojii.cashgift.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import cn.xiaojii.cashgift.bean.ProjectBean;

/**
 * @author dmrfcoder
 * @date 2018/8/4
 */

public class RunningAccountListViewAdapter extends BaseAdapter {
    //ProjectBean其实就是RunningAccountBean

    public List<ProjectBean> getProjectBeanList() {
        return projectBeanList;
    }

    public void setProjectBeanList(List<ProjectBean> projectBeanList) {
        this.projectBeanList = projectBeanList;
    }

    private List<ProjectBean> projectBeanList;


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
