package net.levinh.android_carshowcase.Fragments;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.levinh.android_carshowcase.Model.Car;
import net.levinh.android_carshowcase.R;

public class ScreenSlideFragment extends Fragment {

    private static final String KEY = "key";
    private Car car;
    ImageView imageView;
    TextView textView;

    public ScreenSlideFragment() {
    }

    public static ScreenSlideFragment newInstance(Car car) {
        ScreenSlideFragment fragment = new ScreenSlideFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, car);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        car = (Car) getArguments().getSerializable(KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_screen_slide, container, false);
        imageView = (ImageView) view.findViewById(R.id.imgView);
        textView = (TextView) view.findViewById(R.id.textViewTitle);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        textView.setText(car.getName());

        Picasso.with(getContext())
                .load(Uri.parse(car.getImageUrl()))
                .resize(1000, displayMetrics.heightPixels)
                .centerInside()
                .into(imageView);
    }
}
