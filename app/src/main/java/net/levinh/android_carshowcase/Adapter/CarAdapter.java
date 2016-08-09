package net.levinh.android_carshowcase.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.levinh.android_carshowcase.Model.Car;
import net.levinh.android_carshowcase.R;

import java.util.List;

/**
 * Created by levinhtxbt@gmail.com on 07/08/2016.
 */
public class CarAdapter extends RecyclerView.Adapter<ViewHolder>{

    private List<Car> listCar;
    public static MyClickListener myClick;
    public static BindViewHolder mBindView;
    public CarAdapter(List<Car> listCar) {
        this.listCar = listCar;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Car item = listCar.get(position);
        mBindView.onBindViewHolder(holder,item,position);
        holder.bindEvent(myClick,position);
    }

    @Override
    public int getItemCount() {
        return listCar.size();
    }

    public void setOnClickListener(MyClickListener listener){
        this.myClick = listener;
    }

    public void setOnBindViewHolder(BindViewHolder bindViewHolder){
        this.mBindView = bindViewHolder;
    }

}
