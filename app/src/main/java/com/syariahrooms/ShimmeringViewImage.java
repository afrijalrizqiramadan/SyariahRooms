package com.syariahrooms;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.facebook.shimmer.ShimmerFrameLayout;

public class ShimmeringViewImage extends RelativeLayout {
    Button buttonRetry;
    RelativeLayout relativeLayoutParrent;
    ShimmerFrameLayout shimmerFrameLayout;
    View viewAll;
    private ShimmerView shimmerView = new ShimmerView(this.shimmerFrameLayout, this.viewAll);

    public ShimmeringViewImage(Context context) {
        super(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_shimmer_image, this.relativeLayoutParrent);
        startProgress();
        addView(inflate);
    }

    public ShimmeringViewImage(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_shimmer_image, this.relativeLayoutParrent);
        startProgress();
        addView(inflate);
    }

    public void setRetryClicked(OnClickListener onClickListener) {
        this.buttonRetry.setOnClickListener(onClickListener);
    }

    public void startProgress() {
        setVisibility(VISIBLE);
        this.shimmerView.showShimmerView();
        this.viewAll.setVisibility(GONE);
        this.buttonRetry.setVisibility(GONE);
    }

    public void stopProgress() {
        setVisibility(GONE);
    }

    public void stopAndError() {
        this.shimmerView.hideShimmerView();
        this.viewAll.setVisibility(VISIBLE);
    }
}
