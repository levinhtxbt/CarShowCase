package net.levinh.android_carshowcase.activity;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.Glide;

import net.levinh.android_carshowcase.Adapter.ViewHolder;
import net.levinh.android_carshowcase.Model.Car;

import java.util.List;

/**
 * Created by levinhtxbt@gmail.com on 09/08/2016.
 */
public class CarListGlideActivity extends BaseActivity {
    @Override
    protected void onItemClicked(int position, View view, List<Car> car) {

    }

    @Override
    protected void onBindItemView(ViewHolder holder, Car item, int position) {

        Context context = holder.imageViewCar.getContext();
        holder.textViewTitle.setText(item.getName());
        Glide.with(context)
                .load(item.getImageUrl())
                .fitCenter()
                .crossFade()
                .into(holder.imageViewCar);

    }
}
