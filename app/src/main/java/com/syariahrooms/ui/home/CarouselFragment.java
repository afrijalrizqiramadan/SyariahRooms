package com.syariahrooms.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.syariahrooms.R;

public class CarouselFragment extends Fragment {

    public static Fragment newInstance(Activity context, int position, float scale) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putFloat("scale", scale);
        return Fragment.instantiate(context, CarouselFragment.class.getName(), bundle);
    }

    private int[] imageArray = {R.drawable.card, R.drawable.card,
            R.drawable.card, R.drawable.card};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        LinearLayout linearLayout = (LinearLayout)
                inflater.inflate(R.layout.item_slider, container, false);
        ImageView imageView = linearLayout.findViewById(R.id.changeback);

        int position = this.getArguments().getInt("position");
        imageView.setImageResource(imageArray[position]);

        CustomLinearLayout root = linearLayout.findViewById(R.id.item_root);
        float scale = this.getArguments().getFloat("scale");
        root.setScaleBoth(scale);

        return linearLayout;
    }
}
