package cn.xiaojii.cashgift.bean.fragment;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

public class ProjectTableItemBean {
    private int totalMoney;
    private int inMoney;
    private int outMoney;
    private String personName;


    public String getStrTotalMoney() {
        return totalMoney + "";
    }

    public String getStrInMoney() {
        return inMoney + "";
    }

    public String getStrOutMoney() {
        return outMoney + "";
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void updateTotalMoney(int totalMoney) {
        this.totalMoney += totalMoney;
    }

    public int getInMoney() {
        return inMoney;
    }

    public void updateInMoney(int inMoney) {
        this.inMoney += inMoney;
    }

    public int getOutMoney() {
        return outMoney;
    }

    public void updateOutMoney(int outMoney) {
        this.outMoney += outMoney;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public ProjectTableItemBean() {
        totalMoney = 0;
        inMoney = 0;
        outMoney = 0;
        personName = "";
    }

    public ProjectTableItemBean(int totalMoney, int inMoney, int outMoney, String personName) {

        this.totalMoney = totalMoney;
        this.inMoney = inMoney;
        this.outMoney = outMoney;
        this.personName = personName;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void setInMoney(int inMoney) {
        this.inMoney = inMoney;
    }

    public void setOutMoney(int outMoney) {
        this.outMoney = outMoney;
    }
}
