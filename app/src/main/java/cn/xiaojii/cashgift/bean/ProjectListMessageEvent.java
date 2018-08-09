package cn.xiaojii.cashgift.bean;

import java.util.List;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

public class ProjectListMessageEvent {
    private List<ProjectBean> projectBeans;
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ProjectListMessageEvent(List<ProjectBean> projectBeans, String tag) {

        this.projectBeans = projectBeans;
        this.tag = tag;
    }

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
