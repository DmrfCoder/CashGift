package cn.xiaojii.cashgift.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 回收activity，保证所有的activity被销毁
 * @author dmrfcoder
 */

public class ActivityCollector {
    private static List<Activity> activityList = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    //加所有activity销毁，只保留
    public static void finishAllButLast() {
        Activity activity = activityList.get(activityList.size() - 1);
        removeActivity(activity);

        for (Activity activityItem : activityList) {
            if (!activityItem.isFinishing()) {
                activityItem.finish();
            }
        }

        activityList.clear();
        activityList.add(activity);
    }





    public static void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }

        activityList.clear();
    }
}
