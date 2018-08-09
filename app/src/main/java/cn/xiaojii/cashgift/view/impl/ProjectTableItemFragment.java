package cn.xiaojii.cashgift.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.xiaojii.cashgift.base.BaseFragment;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.view.IProjectTableItemView;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

public class ProjectTableItemFragment extends BaseFragment implements IProjectTableItemView {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view=inflater.inflate();
        return view;
    }

    @Override
    public void updateView(int inCount, int inMoney, int outCount, int outMoney) {

    }

    @Override
    public void updateListView(List<ProjectBean> projectBeanList) {

    }
}
