package cn.xiaojii.cashgift.util.io;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import cn.xiaojii.cashgift.bean.global.PasswordBean;

/**
 * Created by Wjyyy on 2018/8/9.
 */

public class SharedPreferencesUtil {

    public static final String IS_FIRST_RUN = "isFirstRun";

    private static SharedPreferencesUtil sharedPreferencesUtil = null;

    public static SharedPreferencesUtil getInstance(Context context) {
        if (sharedPreferencesUtil == null) {
            synchronized (SharedPreferencesUtil.class) {
                if (sharedPreferencesUtil == null) {
                    sharedPreferencesUtil = new SharedPreferencesUtil();
                    sharedPreferencesUtil.setContext(context);
                    return sharedPreferencesUtil;
                }
            }
        }

        return sharedPreferencesUtil;
    }

    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean getBoolean(String key, boolean defValue) {
        try {
            return getSP().getBoolean(key, defValue);
        } catch (NullPointerException exception) {
            Log.d("hcj", "" + exception);
            return defValue;
        }
    }

    public void putBoolean(String key, boolean value) {
        try {
            SharedPreferences.Editor editor = getSP().edit();
            editor.putBoolean(key, value);
            editor.commit();
        } catch (NullPointerException exception) {
            Log.d("hcj", "" + exception);
        }
    }


    public long getLong(String key, long defValue) {
        try {
            return getSP().getLong(key, defValue);
        } catch (NullPointerException exception) {
            Log.d("hcj", "" + exception);
            return defValue;
        }
    }

    public void putLong(String key, long value) {
        try {
            SharedPreferences.Editor editor = getSP().edit();
            editor.putLong(key, value);
            editor.commit();
        } catch (NullPointerException exception) {
            Log.d("hcj", "" + exception);
        }
    }

    public int getInt(String key, int defaultValue) {
        try {
            return getSP().getInt(key, defaultValue);
        } catch (Exception e) {
            Log.d("hcj", "" + e);
            return defaultValue;

        }
    }

    public void putInt(String key, int value) {
        try {
            SharedPreferences.Editor editor = getSP().edit();
            editor.putInt(key, value);
            editor.commit();
        } catch (Exception e) {
            Log.d("hcj", "" + e);
        }
    }

    public String getString(String key, String defValue) {
        try {
            return getSP().getString(key, defValue);
        } catch (NullPointerException e) {
            Log.d("hcj", "" + e);
            return defValue;
        }
    }

    public void putString(String key, String value) {
        try {
            SharedPreferences.Editor editor = getSP().edit();
            editor.putString(key, value);
            editor.commit();
        } catch (NullPointerException e) {
            Log.d("hcj", "" + e);
        }
    }


    public void putPasswordBean(String key, PasswordBean passwordBean) {
        try {
            SharedPreferences.Editor editor = getSP().edit();
            editor.putBoolean(key + "_flagFinger", passwordBean.isFlagFinger());
            editor.putBoolean(key + "_flagGesture", passwordBean.isFlagGesture());
            editor.putString(key + "_gesturePassword", passwordBean.getGesturePassword());
            editor.commit();
        } catch (NullPointerException e) {
            Log.d("hcj", "" + e);
        }

    }

    public PasswordBean getPasswordBean(String key) {
        PasswordBean passwordBean = new PasswordBean();
        boolean flagFinger = false;
        boolean flagGesture = false;
        String pass = "";
        try {
            getSP().getBoolean(key + "_flagFinger", flagFinger);
            getSP().getBoolean(key + "_flagGesture", flagGesture);
            getSP().getString(key + "_gesturePassword", pass);

            passwordBean.setFlagFinger(flagFinger);
            passwordBean.setFlagGesture(flagGesture);
            passwordBean.setGesturePassword(pass);
            return passwordBean;


        } catch (NullPointerException e) {
            Log.d("hcj", "" + e);
            return null;
        }
    }

    public void clear() {
        try {
            SharedPreferences.Editor editor = getSP().edit();
            editor.clear();
            editor.commit();
        } catch (NullPointerException e) {
            Log.d("hcj", "" + e);
        }
    }

    private SharedPreferences getSP() {
        return context.getSharedPreferences("sp", Context.MODE_PRIVATE);
    }

    private SharedPreferences getSP(String name) {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }
}
