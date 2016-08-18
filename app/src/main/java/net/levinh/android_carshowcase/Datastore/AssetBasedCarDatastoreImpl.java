package net.levinh.android_carshowcase.Datastore;

import android.content.Context;
import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.levinh.android_carshowcase.Model.Car;
import net.levinh.android_carshowcase.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levinhtxbt@gmail.com on 07/08/2016.
 */
public class AssetBasedCarDatastoreImpl implements CarDatastore {
    private Context context;
    private String dataFilename;
    private Gson gson;

    public AssetBasedCarDatastoreImpl(Context context, String dataFilename, Gson gson) {
        this.context = context;
        this.dataFilename = dataFilename;
        this.gson = gson;
    }

    @Override
    public void getCarList(OnCarReceivedListener onCarReceivedListener) {
        if (onCarReceivedListener != null) {
            Type type = new TypeToken<List<Car>>(){}.getType();
            InputStream is = null;
            try {
                is = context.getAssets().open(dataFilename);
                List<Car> cars = gson.fromJson(new InputStreamReader(is), type);
                for (Car car : cars) {
                    car.setImageUrl("android.resource://net.levinh.android_carshowcase/raw/" + car.getImageUrl());
            }
//                //Duplicate data.
//                for (int i = 0; i < 1; i++) {
//                    cars.addAll(new ArrayList<>(cars));
//                }
                onCarReceivedListener.onCarReceived(cars, null);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
