package cn.xiaojii.cashgift.bean.message;

import java.util.List;

import cn.xiaojii.cashgift.bean.fragment.ProjectBean;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

public class ProjectListMessageBean {
    private List<ProjectBean> projectBeans;
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ProjectListMessageBean(List<ProjectBean> projectBeans, String tag) {

        this.projectBeans = projectBeans;
        this.tag = tag;
    }

    public ProjectListMessageBean(List<ProjectBean> projectBeans) {
        this.projectBeans = projectBeans;
    }

    public List<ProjectBean> getProjectBeans() {

        return projectBeans;
    }

    public void setProjectBeans(List<ProjectBean> projectBeans) {
        this.projectBeans = projectBeans;
    }
}
