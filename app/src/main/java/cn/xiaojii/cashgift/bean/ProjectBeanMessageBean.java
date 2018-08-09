package cn.xiaojii.cashgift.bean;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

public class ProjectBeanMessageBean {
    private ProjectBean projectBean;
    private String tag;

    public ProjectBean getProjectBean() {
        return projectBean;
    }

    public void setProjectBean(ProjectBean projectBean) {
        this.projectBean = projectBean;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ProjectBeanMessageBean(ProjectBean projectBean, String tag) {

        this.projectBean = projectBean;
        this.tag = tag;
    }
}
