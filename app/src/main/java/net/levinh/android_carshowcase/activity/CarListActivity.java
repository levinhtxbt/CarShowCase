package net.levinh.android_carshowcase.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import net.levinh.android_carshowcase.Adapter.ViewHolder;
import net.levinh.android_carshowcase.Loader.ImageLoaderThread;
import net.levinh.android_carshowcase.Model.Car;
import net.levinh.android_carshowcase.R;

import java.io.Serializable;
import java.util.List;

public class CarListActivity extends BaseActivity {
    private static final String TAG = "CarListActivity";
    public static final String CAR_DATA = "car";
    public static final String BUNDLE  = "bundle";

    @Override
    protected void onItemClicked(int position, View view, List<Car> car) {

        Intent intent = new Intent(this,CarDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(CAR_DATA,position);
        intent.putExtra(BUNDLE,bundle);
        startActivity(intent);
    }

    @Override
    protected void onBindItemView(ViewHolder holder, Car item, int position) {
        Context context = holder.imageViewCar.getContext();
        holder.textViewTitle.setText(item.getName());
        holder.imageViewCar.setTag(position);
        ImageLoaderThread imageLoaderThread = new ImageLoaderThread(context, holder.imageViewCar, position);
        imageLoaderThread.execute(item.getImageUrl());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnuPicasso) {
            Intent intent = new Intent(this, CarListPicassoActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.mnuFresco) {
            Intent intent = new Intent(this, CarListFrescoActivity.class);
            startActivity(intent);
        }else if (item.getItemId() == R.id.mnuGlide) {
            Intent intent = new Intent(this, CarListGlideActivity.class);
            startActivity(intent);
        }

        return true;
    }
}

