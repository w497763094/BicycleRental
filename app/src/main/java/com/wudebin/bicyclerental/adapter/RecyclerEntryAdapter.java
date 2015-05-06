package com.wudebin.bicyclerental.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class RecyclerEntryAdapter extends RecyclerView.Adapter{

    private View mFragmentRootView;

    protected void setFragmentRootView(View rootView) {
        mFragmentRootView = rootView;
    }

    protected View getFragmentRootView() {
        return mFragmentRootView;
    }
}
