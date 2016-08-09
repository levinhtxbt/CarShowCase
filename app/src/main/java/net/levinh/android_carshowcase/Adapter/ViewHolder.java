package net.levinh.android_carshowcase.Adapter;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.levinh.android_carshowcase.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by levinhtxbt@gmail.com on 07/08/2016.
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.textViewTitle)
    public AppCompatTextView textViewTitle;
    @Bind(R.id.imageViewCar)
    public AppCompatImageView imageViewCar;

    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bindEvent(final MyClickListener myClickListener, final int position) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myClickListener.onItemClick(position, itemView);
            }
        });
    }
}
