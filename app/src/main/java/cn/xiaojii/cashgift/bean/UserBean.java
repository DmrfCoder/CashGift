package cn.xiaojii.cashgift.bean;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

//全局用户的Bean，保存当前用户的信息
public class UserBean {
    private String excelName;

    public UserBean() {
        excelName="账簿.xls";
    }

    public String getExcelName() {
        return excelName;
    }

    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }
}
