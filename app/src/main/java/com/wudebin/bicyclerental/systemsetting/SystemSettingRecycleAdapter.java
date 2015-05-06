package com.wudebin.bicyclerental.systemsetting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wudebin.bicyclerental.R;

public class SystemSettingRecycleAdapter extends RecyclerView.Adapter<SystemSettingRecycleAdapter.ViewHolder> {

    private int[] mdata_text;
    private int[] mdata_icon;
    private Context mContext;
    private MyItemClickListener mItemClickListener;
    public interface MyItemClickListener {
        public void onItemClick(View view, int postion);
    }
    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mItemClickListener = listener;
    }
    public SystemSettingRecycleAdapter(int[] mydata_text,int[] mydata_cion) {

        this.mdata_text = mydata_text;
        this.mdata_icon = mydata_cion;
    }

    @Override
    public int getItemCount() {
        return mdata_text.length;
    }

    @Override
    public void onBindViewHolder(ViewHolder arg0, int arg1) {
        arg0.text.setText(mdata_text[arg1]);
        arg0.icon.setImageResource(mdata_icon[arg1]);
        if (arg1+1 == getItemCount()) {
            arg0.line.setVisibility(View.GONE);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        mContext = arg0.getContext();
        View v = LayoutInflater.from(mContext).inflate(R.layout.syssetting_item, null);
        return new ViewHolder(v,mItemClickListener);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView icon;
        private TextView text;
        private ImageView line;
        private MyItemClickListener mListener;

        public ViewHolder(View itemView,MyItemClickListener listener) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.sysseting_item_icon);
            text = (TextView) itemView.findViewById(R.id.sysseting_item_text);
            line = (ImageView) itemView.findViewById(R.id.sysseting_line);
            this.mListener = listener;
            itemView.setOnClickListener(this);
            getPosition();
        }
        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onItemClick(v,getPosition());
            }
        }
    }

}

