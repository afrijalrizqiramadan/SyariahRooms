package com.syariahrooms;

import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;

public class ShimmerView {
    private ShimmerFrameLayout mShimmerFrameLayout;
    private View mView;

    public ShimmerView(ShimmerFrameLayout shimmerFrameLayout, View view) {
        this.mShimmerFrameLayout = shimmerFrameLayout;
        this.mView = view;
    }

    public void showShimmerView() {
        this.mShimmerFrameLayout.startShimmer();
        this.mShimmerFrameLayout.setVisibility(View.VISIBLE);
        this.mView.setVisibility(View.GONE);
    }

    public void hideShimmerView() {
        this.mShimmerFrameLayout.startShimmer();
        this.mShimmerFrameLayout.setVisibility(View.GONE);
        this.mView.setVisibility(View.GONE);
    }
}
