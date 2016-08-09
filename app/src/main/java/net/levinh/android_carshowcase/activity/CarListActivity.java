package net.levinh.android_carshowcase.activity;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import net.levinh.android_carshowcase.Adapter.ViewHolder;
import net.levinh.android_carshowcase.Loader.ImageLoaderThread;
import net.levinh.android_carshowcase.Model.Car;
import net.levinh.android_carshowcase.R;

public class CarListActivity extends BaseActivity {
    private static final String TAG = "CarListActivity";

    @Override
    protected void onItemClicked(int position, View view, Car car) {
        Toast.makeText(this, car.getName() + " ", Toast.LENGTH_SHORT).show();
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

