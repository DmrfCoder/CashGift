package cn.xiaojii.cashgift.view;

import java.util.List;

import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.bean.ProjectTableBean;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public interface IProjectTableView {


    /**
     * @param projectTableBeanList
     */
    void updateListView(List<ProjectTableBean> projectTableBeanList);
}
