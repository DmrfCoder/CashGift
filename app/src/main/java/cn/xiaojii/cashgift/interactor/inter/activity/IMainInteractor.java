package cn.xiaojii.cashgift.interactor.inter.activity;

import java.util.List;

import cn.xiaojii.cashgift.bean.global.UserBean;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

public interface IMainInteractor {

    public interface ExportExcelListener{
        /**
         * 导出excel失败
         */
        void onExportExcelError();

        /**
         * 导出excel成功
         * @param list
         * @param userBean
         */
        void onExportExcelSuccess(List list,UserBean userBean);
    }


    /**
     * 导出excel功能
     *
     * @param exportExcelListener
     *
     */
    void exportExcel(ExportExcelListener exportExcelListener);
}
