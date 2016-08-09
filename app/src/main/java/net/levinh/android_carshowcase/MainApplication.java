package net.levinh.android_carshowcase;

import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by levinhtxbt@gmail.com on 08/08/2016.
 */
public class MainApplication extends android.app.Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
