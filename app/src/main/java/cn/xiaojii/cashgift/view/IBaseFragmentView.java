package cn.xiaojii.cashgift.view;

import android.content.Context;

import java.util.List;

/**
 * @author dmrfcoder
 * @date 2018/8/2
 */

public interface IBaseFragmentView {

    /**
     * 更新数据list，泛型
     *
     * @param classList
     */
    void updateData(List<Class> classList);

    /**
     * @param context
     */
    void showDialog(final Context context);
}
