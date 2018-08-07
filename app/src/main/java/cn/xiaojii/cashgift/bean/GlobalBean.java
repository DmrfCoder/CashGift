package cn.xiaojii.cashgift.bean;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class GlobalBean {
    public static enum inOrOut {
        IN, OUT
    }

    public static String filename = "demo_cash_gift";
    public static String filepath = "CashGift";

    public static String NORMAR_ACTION = "cn.xiaojii.normal.receiver";
    public static String BROADCAST_TAG_KEY = "key";
    public static String BROADCAST_BEAN_LIST_KEY = "listkey";


    public static String NORMAR_ACTION2 = "cn.xiaojii.normal.receiver2";
    public static String BROADCAST_NEED_DATA_KEY = "need_data_key";
    public static String BROADCAST_NEED_DATA = "need_data";



    public static String NORMAR_ACTION3 = "cn.xiaojii.normal.receiver3";
    public static String BROADCAST_ADD_PROJECT_BEAN_KEY = "add_project_bean_key";
    public static String BROADCAST_ADD_PROJECT_FRAGMENT_NAME_KEY = "add_project_fragment_name_key";


}
