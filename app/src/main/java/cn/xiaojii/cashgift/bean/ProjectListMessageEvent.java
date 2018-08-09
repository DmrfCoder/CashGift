package cn.xiaojii.cashgift.bean;

import java.util.List;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

public class ProjectListMessageEvent {
    private List<ProjectBean> projectBeans;

    public ProjectListMessageEvent(List<ProjectBean> projectBeans) {
        this.projectBeans = projectBeans;
    }

    public List<ProjectBean> getProjectBeans() {

        return projectBeans;
    }

    public void setProjectBeans(List<ProjectBean> projectBeans) {
        this.projectBeans = projectBeans;
    }
}
