package cn.xiaojii.cashgift.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.view.IProjectTableView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class ProjectTableFragment extends BaseFragment implements IProjectTableView {

    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private int e = 0;

    String detailProjectTable = "共：" + a + "人 收礼：" + b + "（" + c + "个） 送礼：" + d + "（" + e + "个）";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_projecttable, null);
        return view;
    }
}
