package ir.farhadkargaran.restapi;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.Toast;

public class App extends Application {


    private static App instance;
    public static volatile Handler applicationHandler;
    public static volatile Context applicationContext;

    public SharedPreferences settings;
    public SharedPreferences.Editor editor;


    public static final App instance() {
        if (instance != null)
            return instance;
        throw new RuntimeException("Top10 is not instantiated yet code=553442d236");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationContext = getApplicationContext();
        applicationHandler = new Handler(getApplicationContext().getMainLooper());
        settings = getSharedPreferences("UserInfo", 0);
        editor = settings.edit();

    }


    public static  void toastShort(String str)
    {
        Toast.makeText(instance,str,Toast.LENGTH_SHORT).show();
    }
    public static  void toastLong(String str)
    {
        Toast.makeText(instance,str,Toast.LENGTH_LONG).show();
    }



}
