package net.levinh.android_carshowcase.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import net.levinh.android_carshowcase.Fragments.ScreenSlideFragment;
import net.levinh.android_carshowcase.Model.Car;

import java.util.List;

/**
 * Created by levinhtxbt@gmail.com on 10/08/2016.
 */
public class DetailsSlidePageAdapter extends FragmentStatePagerAdapter {

    List<Car> carList;

    public DetailsSlidePageAdapter(FragmentManager fm, List<Car> carList) {
        super(fm);
        this.carList = carList;
    }

    @Override
    public Fragment getItem(int position) {
        return ScreenSlideFragment.newInstance(carList.get(position));
    }

    public int getVirtualPosition(int realPosition) {
        return realPosition % (carList.size()+1);
    }

    @Override
    public int getCount() {
        return carList.size();
    }

//    @Override
//    public float getPageWidth(int position) {
//        return 0.95f;
//    }
}
