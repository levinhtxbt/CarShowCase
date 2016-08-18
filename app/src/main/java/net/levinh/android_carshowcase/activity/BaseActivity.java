package net.levinh.android_carshowcase.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.levinh.android_carshowcase.Adapter.BindViewHolder;
import net.levinh.android_carshowcase.Adapter.CarAdapter;
import net.levinh.android_carshowcase.Adapter.MyClickListener;
import net.levinh.android_carshowcase.Adapter.ViewHolder;
import net.levinh.android_carshowcase.Datastore.AssetBasedCarDatastoreImpl;
import net.levinh.android_carshowcase.Datastore.CarDatastore;
import net.levinh.android_carshowcase.Datastore.OnCarReceivedListener;
import net.levinh.android_carshowcase.Model.Car;
import net.levinh.android_carshowcase.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by levinhtxbt@gmail.com on 09/08/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = "CarListActivity";
    @Bind(R.id.recycleViewCar)
    RecyclerView recyclerViewCar;
    CarAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);
        ButterKnife.bind(this);

        Gson gson = new GsonBuilder().create();
        CarDatastore carDatastore = new AssetBasedCarDatastoreImpl(this, "car_data.json", gson);
        carDatastore.getCarList(new OnCarReceivedListener() {
            @Override
            public void onCarReceived(final List<Car> cars, Exception ex) {
                adapter = new CarAdapter(cars);
                adapter.setOnClickListener(new MyClickListener() {
                    @Override
                    public void onItemClick(int position, View view) {
                        onItemClicked( position,  view, cars);
                    }
                });
                adapter.setOnBindViewHolder(new BindViewHolder() {
                    @Override
                    public void onBindViewHolder(ViewHolder holder, Car item, int position) {
                        onBindItemView( holder,  item,  position);
                    }
                });
                recyclerViewCar.setHasFixedSize(true);
                recyclerViewCar.setLayoutManager(new LinearLayoutManager(BaseActivity.this));
                recyclerViewCar.setAdapter(adapter);
            }
        });
    }

    protected abstract void onItemClicked(int position, View view, List<Car> car);
    protected  abstract void onBindItemView(ViewHolder holder, Car item, int position);
}
