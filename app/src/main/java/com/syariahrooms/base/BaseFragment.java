package com.syariahrooms.base;

import android.util.Log;


import androidx.fragment.app.Fragment;

import com.syariahrooms.App;
import com.syariahrooms.data.Repository;

import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    private static String TAG = "basefraggmen";
    protected Unbinder unbinder;

    protected void setUnbinder(Unbinder unbinder) {
        this.unbinder = unbinder;
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: ");
        if (unbinder != null) unbinder.unbind();
        super.onDestroyView();

    }

    protected Repository getRepository() {
        return ((App) getActivity().getApplication()).getRepository();
    }
}
