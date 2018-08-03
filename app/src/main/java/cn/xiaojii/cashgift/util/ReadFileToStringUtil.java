package cn.xiaojii.cashgift.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.xiaojii.cashgift.bean.GlobalBean;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class ReadFileToStringUtil {
    public static String read(String filename) {


        //如果手机已插入sd卡,且app具有读写sd卡的权限
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {

                File dir = new File(Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + GlobalBean.filepath);
                //文件夹是否已经存在
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                filename = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + GlobalBean.filepath + "/" + filename + ".json";
                FileInputStream input = new FileInputStream(filename);
                byte[] b = new byte[input.available()];
                input.read(b);
                String result = new String(b);
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            Log.i("ReadFileToStringUtil", "读文件失败");
        }

        return null;
    }
}
