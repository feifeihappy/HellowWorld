package cn.itcast.huayu.menu.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @author ln：zpf on 2016/7/30
 */
public class ToastUtil {
   public static void showToast(Context context ,String string){
       Toast.makeText(context,string,Toast.LENGTH_SHORT).show();
   }
    public static void LongToast(Context context ,String string){
        Toast.makeText(context,string,Toast.LENGTH_LONG).show();
    }
}
