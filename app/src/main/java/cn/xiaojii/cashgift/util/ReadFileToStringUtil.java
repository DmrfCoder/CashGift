package cn.xiaojii.cashgift.util;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import cn.xiaojii.cashgift.bean.ContantsValue;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class ReadFileToStringUtil {
    public static String read(String filename) {


        //如果手机已插入sd卡,且app具有读写sd卡的权限
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {

                File dir = new File(Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + ContantsValue.filepath);
                //文件夹是否已经存在
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                filename = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + ContantsValue.filepath + "/" + filename + ".json";
                try {
                    FileInputStream input = new FileInputStream(filename);
                    byte[] b = new byte[input.available()];
                    input.read(b);
                    String result = new String(b);
                    return result;
                } catch (FileNotFoundException e) {
                    return null;
                }


            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            Log.i("ReadFileToStringUtil", "读文件失败");
        }

        return null;
    }
}
