package cn.xiaojii.cashgift.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class ProjectBean implements Parcelable {
    private String name;
    private String project;
    private String year;
    private String monthAndDay;
    private int money;


    @Override
    public boolean equals(Object obj) {
        ProjectBean projectBean=(ProjectBean) obj;
        if (projectBean.getName().equals(this.name)){
            if (projectBean.getIntMoney()==this.money){
                if (projectBean.getProject().equals(this.project)){
                    if (projectBean.getYear().equals(this.year)){
                        if (projectBean.getMonthAndDay().equals(this.monthAndDay)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public ProjectBean() {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR) + "";
        monthAndDay = (c.get(Calendar.MONTH) + 1) + "." + c.get(Calendar.DAY_OF_MONTH);
    }

    protected ProjectBean(Parcel in) {
        name = in.readString();
        project = in.readString();
        year = in.readString();
        monthAndDay = in.readString();
        money = in.readInt();
    }

    public static final Creator<ProjectBean> CREATOR = new Creator<ProjectBean>() {
        @Override
        public ProjectBean createFromParcel(Parcel in) {
            return new ProjectBean(in);
        }

        @Override
        public ProjectBean[] newArray(int size) {
            return new ProjectBean[size];
        }
    };

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

    public String getMoney() {
        return money+"";
    }

    public int getIntMoney() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(project);
        parcel.writeString(year);
        parcel.writeString(monthAndDay);
        parcel.writeInt(money);
    }
}
