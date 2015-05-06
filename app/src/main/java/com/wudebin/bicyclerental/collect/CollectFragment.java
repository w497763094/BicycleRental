package com.wudebin.bicyclerental.collect;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.wudebin.bicyclerental.adapter.FooterAdapter;

/**
 * Created by wudebin on 15-5-4.
 */
public class CollectFragment extends FooterAdapter{
    @Override
    public boolean useFooter() {
        return false;
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentItemViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindContentItemView(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getContentItemCount() {
        return 0;
    }

    @Override
    public int getContentItemType(int position) {
        return 0;
    }
}
