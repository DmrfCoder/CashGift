package cn.xiaojii.cashgift.bean.global;

import android.os.Environment;

import java.io.IOException;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class ConstantsBean {

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


    public static String NORMAR_ACTION4 = "cn.xiaojii.normal.receiver4";


    public static String TAG_MAINPRESENTER = "MainPresenter";
    public static String TAG_DIALOGFRAGMENT = "AddProjectDialogFragment";
    public static String TAG_FRIENDSANDRELATIVES = "FriendsAndRelativesFragmentPresenter";
    public static String TAG_PROJECTTABLE = "ProjectTableFragmentPresenter";

    public static String EXPORTEXCEL = "export_excel";

    public static String CASHGIFT_GESTURE_PWD_KEY = "CASHGIFT_GESTURE_PWD_KEY";
    public static String APP_CONFIG_SP_KEY = "APP_CONFIG_SP_KEY";


    /**
     * 手势枚举
     */
    public enum GESTURE_TYPE {
        /**
         * 设置、重置、验证
         */
        GESTURE_SET("set", 0), GESTURE_RESET("reset", 1), GESTURE_VERIFICATION("verification", 2);
        private String name;
        private int index;

        GESTURE_TYPE(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public int getIndex() {
            return index;
        }

        public static GESTURE_TYPE getItemByIndex(int index) {
            for (GESTURE_TYPE gesture_type : GESTURE_TYPE.values()) {
                if (gesture_type.getIndex() == index) {
                    return gesture_type;
                }
            }
            return null;
        }
    }

    public static String GESTURE_KEY = "GESTURE_KEY";

    public static String APP_FOLDER_PATH;

    static {
        try {
            APP_FOLDER_PATH = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + ConstantsBean.filepath;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
