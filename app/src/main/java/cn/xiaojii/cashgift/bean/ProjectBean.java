package cn.xiaojii.cashgift.bean;

import java.util.Calendar;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class ProjectBean {
    private String name;
    private String project;
    private String year;
    private String monthAndDay;
    private int money;

    public ProjectBean() {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR) + "";
        monthAndDay = (c.get(Calendar.MONTH) + 1) + "." + c.get(Calendar.DAY_OF_MONTH);
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonthAndDay() {
        return monthAndDay;
    }

    public void setMonthAndDay(String monthAndDay) {
        this.monthAndDay = monthAndDay;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
