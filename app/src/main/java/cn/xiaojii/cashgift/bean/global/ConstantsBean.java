package cn.xiaojii.cashgift.bean.global;

import android.os.Environment;

import java.io.IOException;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class ConstantsBean {

    public static enum inOrOut {
        /**
         * 收入/支出
         */
        IN, OUT
    }

    /**
     * 文件存储
     */
    public static String filename = "demo_cash_gift";
    public static String filepath = "CashGift";
    public static String APP_FOLDER_PATH;

    static {
        try {
            APP_FOLDER_PATH = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + ConstantsBean.filepath;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * EventBus Tag
     */
    public static String TAG_MAINPRESENTER = "MainPresenter";
    public static String TAG_DIALOGFRAGMENT = "AddProjectDialogFragment";
    public static String TAG_PROJECTTABLE = "ProjectTableFragmentPresenter";
    public static String EXPOR_TEXCEL = "export_excel";

    /**
     * SharedPreferences
     */
    public static String APP_CONFIG_GESTURE_SP_KEY = "APP_CONFIG_GESTURE_SP_KEY";


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




}
