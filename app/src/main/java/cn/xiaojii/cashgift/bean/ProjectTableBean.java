package cn.xiaojii.cashgift.bean;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmrfcoder
 * @date 2018/8/7
 */

public class ProjectTableBean {
    private String TAG="ProjectTableBean";


    private String name;
    private int sumMoneyIn;
    private int sumMoneyOut;

    private List<ProjectBean> projectBeanList;


    public void addProjectBean(ProjectBean projectBean) {
        projectBeanList.add(projectBean);

    }


    public void updateMoneyIn(int in) {

        Log.i(TAG,"updateMoneyIn--in:"+in+" before:"+sumMoneyIn);
        sumMoneyIn += in;
        if (sumMoneyIn!=800){
            int c=0;
        }


    }

    public void updateMoneyOut(int out) {
        Log.i(TAG,"updateMoneyOut--out:"+out+" before:"+sumMoneyIn);
        sumMoneyOut += out;

    }

    public ProjectTableBean() {
        sumMoneyOut = 0;
        sumMoneyIn = 0;
        projectBeanList = new ArrayList<>();
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIntSumMoneyIn() {
        return sumMoneyIn;
    }

    public void setSumMoneyIn(int sumMoneyIn) {
        this.sumMoneyIn = sumMoneyIn;
    }

    public int getIntSumMoneyOut() {

        return sumMoneyOut;
    }

    public String getStrSumMoneyIn() {

        return sumMoneyIn + "";
    }

    public void setSumMoneyOut(int sumMoneyOut) {
        this.sumMoneyOut = sumMoneyOut;
    }

    public String getStrSumMoneyOut() {

        return sumMoneyOut + "";
    }


    public boolean hasTargetBean(ProjectBean projectBean) {


        if (projectBeanList==null){
            return false;
        }

        for (ProjectBean projectBean1:projectBeanList){
            if (projectBean1.equals(projectBean)){
                return true;
            }

        }

        return false;

    }

}
