package com.wudebin.bicyclerental.personalsetting;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.wudebin.bicyclerental.R;

import java.util.ArrayList;

public class PersonSettingRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_SIGN = 1;
    private static final int VIEW_TYPE_LINK = 2;
    private static final int VIEW_TYPE_ITEM = 3;
    private static final int VIEW_TYPE_LAST = 4;

    private LayoutInflater mInflater;
    private ArrayList<String> mItems;
    private View mHeaderView;
    private Context mContext;
    private MyItemTopClickListener mItemTopClickListener;
    private MyItemBottomClickListener mItemBottomClickListener;
    private MyHeaderClickListener mHeaderClickListener;
    private MySignClickListener mSignClickListener;
    public interface MyItemTopClickListener {
        public void onItemTopClick(View view, int postion);
    }
    public interface MyItemBottomClickListener {
        public void onItemBottomClick(View view, int postion);
    }
    public interface MyHeaderClickListener {
        public void onHeaderClick(View view, int postion);
    }
    public interface MySignClickListener {
        public void onSignClick(View view, int postion);
    }
    public void setOnItemTopClickListener(MyItemTopClickListener listener) {
        this.mItemTopClickListener = listener;
    }
    public void setOnItemBottomClickListener(MyItemBottomClickListener listener) {
        this.mItemBottomClickListener = listener;
    }
    public void setOnHeaderClickListener(MyHeaderClickListener listener) {
        this.mHeaderClickListener = listener;
    }
    public void setOnSignClickListener(MySignClickListener listener) {
        this.mSignClickListener = listener;
    }

    public PersonSettingRecyclerAdapter(Context context, ArrayList<String> items, View headerView) {
        mInflater = LayoutInflater.from(context);
        mItems = items;
        mHeaderView = headerView;
    }

    @Override
    public int getItemCount() {
        if (mHeaderView == null) {
            return mItems.size();
        } else {
            return mItems.size() + 3;
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type;
        if (0==position){
            type= VIEW_TYPE_HEADER;
        }else if (1==position){
            type= VIEW_TYPE_SIGN;
        }else if (2==position){
            type= VIEW_TYPE_LINK;
        }else if(getItemCount()==position+1){
            type= VIEW_TYPE_LAST;
        }else{
            type= VIEW_TYPE_ITEM;
        }
        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        if (viewType == VIEW_TYPE_HEADER) {
            return new HeaderViewHolder(mHeaderView,mHeaderClickListener);
        } else if (viewType == VIEW_TYPE_SIGN) {

            return new SignViewHolder(mInflater.inflate(android.R.layout.simple_list_item_1, parent, false),mSignClickListener);
        } else if (viewType == VIEW_TYPE_LINK) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.perset_recyclerview_item, null);
            return new LinkViewHolder(v,mItemTopClickListener,mItemBottomClickListener);
        } else if (viewType == VIEW_TYPE_LAST) {
            return new LastViewHolder(mInflater.inflate(R.layout.recycleview_lastitem_text, parent, false));
        } else {

            View v = LayoutInflater.from(mContext).inflate(R.layout.perset_recyclerview_item_noedit, null);
            return new ItemViewHolder(v);

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemViewHolder) {
            ((ItemViewHolder) viewHolder).top_content.setText(mItems.get(position - 3));
        }else if(viewHolder instanceof SignViewHolder){
            ((SignViewHolder) viewHolder).textView.setText(mContext.getResources().getString(R.string.first_sign));
        }else if(viewHolder instanceof LastViewHolder){
            ((LastViewHolder) viewHolder).textView.setText(mContext.getResources().getString(R.string.no_more_class));
        }
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private MyHeaderClickListener mListener;
        public HeaderViewHolder(View view,MyHeaderClickListener listener) {
            super(view);
            this.mListener=listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onHeaderClick(v,getPosition());
            }
        }
    }
    static class SignViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView;
        private MySignClickListener mListener;
        public SignViewHolder(View view,MySignClickListener listener) {
            super(view);
            textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setBackgroundColor(Color.rgb(236, 236, 236));
            textView.setTextSize(14);
            textView.setTextColor(Color.rgb(156, 156, 156));
            textView.setPadding(20,10,20,10);
            textView.setSingleLine(true);
            this.mListener=listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onSignClick(v,getPosition());
            }
        }
    }
    static class LastViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public LastViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.last_text);

        }

    }

    static class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView top_content,bot_content;

        public ItemViewHolder(View view) {
            super(view);
            top_content = (TextView) view.findViewById(R.id.perseting_item_top_text);
            bot_content = (TextView) view.findViewById(R.id.perseting_item_bot_text);

        }
    }
    static class LinkViewHolder extends RecyclerView.ViewHolder{
        TextView top_content,bot_content;
        ImageView top_edit,bot_edit;
        private MyItemTopClickListener mListenerTop;
        private MyItemBottomClickListener mListenerBottom;

        public LinkViewHolder(View view,MyItemTopClickListener listenerTop,MyItemBottomClickListener listenerBottom) {
            super(view);
            top_content = (TextView) view.findViewById(R.id.perseting_item_top_text);
            top_edit = (ImageView) view.findViewById(R.id.perseting_item_top_edit);
            bot_content = (TextView) view.findViewById(R.id.perseting_item_bot_text);
            bot_edit = (ImageView) view.findViewById(R.id.perseting_item_bot_edit);
            this.mListenerTop = listenerTop;
            this.mListenerBottom = listenerBottom;
            top_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListenerTop != null){
                        mListenerTop.onItemTopClick(v,getPosition());
                    }
                }
            });
            bot_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListenerBottom != null){
                        mListenerBottom.onItemBottomClick(v,getPosition());
                    }
                }
            });
        }
    }
}
