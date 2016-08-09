package net.levinh.android_carshowcase.Utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by levinhtxbt@gmail.com on 09/08/2016.
 */
public class BitmapCache {
    static LruCache<String, Bitmap> cache;

    static final Object lock = new Object();

    static BitmapCache singleInstance;

    private BitmapCache() {

        int maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024);

        // Use 1/8th of the available memory for this memory cache.
        int cacheSize = maxMemory/8;

        cache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount()/1024;
            }
        };
    }

    public static BitmapCache getBitmapCache() {
        synchronized (lock) {
            if (singleInstance == null)
                singleInstance = new BitmapCache();
            return singleInstance;
        }
    }


    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {


        synchronized (lock) {
//            if (getBitmap(key) == null) {
                cache.put(key, bitmap);
//            }
        }


    }
   public Bitmap getBitmap(String key){

       synchronized (lock) {
           return cache.get(key);
       }
   }
}
