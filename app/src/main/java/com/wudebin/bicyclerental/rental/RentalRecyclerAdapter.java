package com.wudebin.bicyclerental.rental;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wudebin.bicyclerental.R;
import com.wudebin.bicyclerental.adapter.FooterAdapter;

/**
 * Created by wudebin on 15-5-4.
 */
public class RentalRecyclerAdapter extends FooterAdapter {

    @Override
    public boolean useFooter() {
        return false;
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rental_recycler_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindContentItemView(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getContentItemCount() {
        return 10;
    }

    @Override
    public int getContentItemType(int position) {
        return 0;
    }

    class ViewHolder extends  RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
