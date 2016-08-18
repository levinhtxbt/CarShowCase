package net.levinh.android_carshowcase.activity;

import android.content.Context;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.squareup.picasso.Picasso;

import net.levinh.android_carshowcase.Adapter.ViewHolder;
import net.levinh.android_carshowcase.Model.Car;

import java.util.List;

/**
 * Created by levinhtxbt@gmail.com on 09/08/2016.
 */
public class CarListPicassoActivity extends BaseActivity {

    private static final String TAG = "CarListPicassoActivity";

    @Override
    protected void onItemClicked(int position, View view, List<Car> car) {

    }

    @Override
    protected void onBindItemView(ViewHolder holder, Car item, int position) {
        Context context = holder.imageViewCar.getContext();
        holder.textViewTitle.setText(item.getName());
        Picasso.with(context)
                .load(Uri.parse(item.getImageUrl()))
                .resize(100, 100)
                .centerInside()
                .into(holder.imageViewCar);
    }
}
