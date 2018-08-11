package cn.xiaojii.cashgift.util.io;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.xiaojii.cashgift.bean.global.ConstantsBean;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class WriteStringToFileUtil {


    public static void write(String content, String filename) {
        //如果手机已插入sd卡,且app具有读写sd卡的权限
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                File dir = new File(ConstantsBean.APP_FOLDER_PATH);
                //文件夹是否已经存在
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                filename = ConstantsBean.APP_FOLDER_PATH + "/" + filename + ".json";
                FileOutputStream output = new FileOutputStream(filename);
                output.write(content.getBytes());
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            Log.i("WriteStringToFileUtil", "写文件失败");
        }
    }
}
