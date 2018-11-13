package song.jeno.com.fruitdemo;

import android.app.Application;
import android.content.Context;

/**
 * author : 宋佳
 * time   : 2018/05/23
 * desc   :
 * version: 1.0.0
 */

public class MyApplication extends Application {


    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();


    }


    //返回
    public static Context getContextObject() {
        return context;
    }

}
