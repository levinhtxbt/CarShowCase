package net.levinh.android_carshowcase.Datastore;

import net.levinh.android_carshowcase.Model.Car;

import java.util.List;

/**
 * Created by levinhtxbt@gmail.com on 07/08/2016.
 */
public interface OnCarReceivedListener {
    void onCarReceived(List<Car> cars, Exception ex);

}