package cn.xiaojii.cashgift.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.xiaojii.cashgift.bean.RunningAccountBean;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class JsonToListUtil {
    public static List<RunningAccountBean> jsonToList(String strJson) {

        if (strJson==null||strJson.equals("")){
            return null;
        }

        List<RunningAccountBean> runningAccountBeanList;

        Gson gson = new Gson();

        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(strJson);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        Iterator it = jsonArray.iterator();
        runningAccountBeanList = new ArrayList<>();

        while (it.hasNext()) {
            jsonElement = (JsonElement) it.next();
            strJson = jsonElement.toString();
            RunningAccountBean runningAccountBean = gson.fromJson(strJson, RunningAccountBean.class);
            runningAccountBeanList.add(runningAccountBean);
        }
        return runningAccountBeanList;
    }
}
