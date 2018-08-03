package cn.xiaojii.cashgift.bean;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class FriendsAndRelativesBean {
    private String name;
    private int in;
    private int out;
    private int sumMoney;


    public FriendsAndRelativesBean(RunningAccountBean runningAccountBean) {
        name = runningAccountBean.getName();
        sumMoney = runningAccountBean.getMoney();
        if (sumMoney > 0) {
            in = 1;
            out = 0;
        } else {
            in = 0;
            out = 1;
        }
    }

    public FriendsAndRelativesBean(String name, int in, int out, int sumMoney) {
        this.name = name;
        this.in = in;

        this.out = out;
        this.sumMoney = sumMoney;
    }

    public void addIn() {
        in++;
    }

    public void addOut() {
        out++;
    }

    public void updateSumMoney(int money) {
        sumMoney += money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public int getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(int sumMoney) {
        this.sumMoney = sumMoney;
    }
}
