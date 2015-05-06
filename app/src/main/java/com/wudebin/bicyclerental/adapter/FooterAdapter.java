package com.wudebin.bicyclerental.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wudebin.bicyclerental.R;


public abstract class FooterAdapter extends
        RecyclerEntryAdapter {

	private static final int TYPE_FOOTER = Integer.MIN_VALUE;
	private static final int TYPE_ADAPTEE_OFFSET = 2;
	private boolean canLoadMore = false;
	private Context mContext;
	
	
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
			int viewType) {
		if (viewType == TYPE_FOOTER) {
			return onCreateFooterViewHolder(parent, viewType);
		}
		return onCreateContentItemViewHolder(parent, viewType
				- TYPE_ADAPTEE_OFFSET);
	}

	@Override
	public final void onBindViewHolder(RecyclerView.ViewHolder holder,
			int position) {
		if (position == getContentItemCount()
				&& holder.getItemViewType() == TYPE_FOOTER&&useFooter()) {
			onBindFooterView(holder, position);
		} else {
			onBindContentItemView(holder, position);
		}
	}

	private static class ViewHolderFooter extends RecyclerView.ViewHolder {
		public ProgressBar pro;
		public TextView title;

		public ViewHolderFooter(View v) {
			super(v);
			pro = (ProgressBar) v.findViewById(R.id.pro);
			title = (TextView) v.findViewById(R.id.footerTitle);
		}
	}

	@Override
	public int getItemCount() {
		int itemCount = getContentItemCount();
		if (useFooter()) {
			itemCount += 1;
		}
		return itemCount;
	}

	@Override
	public int getItemViewType(int position) {

		if (position == getContentItemCount() && useFooter()) {
			return TYPE_FOOTER;
		}
		return getContentItemType(position) + TYPE_ADAPTEE_OFFSET;
	}

	public void setCanLoadMore(boolean canLoadMore) {
		this.canLoadMore = canLoadMore;
	}
	
	public boolean isCanLoadMore() {
		return canLoadMore;
	}

	public abstract boolean useFooter();

	public RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent,
			int viewType) {
        mContext=parent.getContext();
		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.listview_footer, parent, false);
		ViewHolderFooter vh = new ViewHolderFooter(v);
		return vh;
	}

	public void onBindFooterView(RecyclerView.ViewHolder holder, int position) {
		ViewHolderFooter footerHolder = (ViewHolderFooter) holder;
		if (isCanLoadMore()) {
			footerHolder.pro.setVisibility(View.VISIBLE);
			footerHolder.title.setText(mContext.getString(R.string.recyclerview_loadmore));
		} else {
			footerHolder.pro.setVisibility(View.GONE);
			footerHolder.title.setText(mContext.getString(R.string.recyclerview_noloadmore));
		}
	}

	public abstract RecyclerView.ViewHolder onCreateContentItemViewHolder(
			ViewGroup parent, int viewType);

	public abstract void onBindContentItemView(RecyclerView.ViewHolder holder,
			int position);

	public abstract int getContentItemCount();

	public abstract int getContentItemType(int position);
}