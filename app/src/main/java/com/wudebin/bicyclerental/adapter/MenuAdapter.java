package com.wudebin.bicyclerental.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wudebin.bicyclerental.R;


/**
 * Created by wudebin on 2015/3/24.
 */
public class MenuAdapter extends BaseAdapter {
    private int mSelect=0;
    private int[] mMenuIcon;
    private int[] mMenuIconChecked;
    private String[] mMenuItem;
    private Context mContext;
    public MenuAdapter(Context context)
    {
        this.mContext=context;
        mMenuIcon=new int[]{R.drawable.menu_systemset_normal,R.drawable.menu_systemset_normal};
        mMenuIconChecked=new int[]{R.drawable.menu_systemset_press,R.drawable.menu_systemset_press};
        mMenuItem=new String[]{ mContext.getString(R.string.menu_rental),mContext.getString(R.string.menu_syssetting)};
    }
    public void changeSelected(int positon){
        if(positon != mSelect){
            mSelect = positon;
            notifyDataSetChanged();
        }
    }
    @Override
    public int getCount() {
        return mMenuIcon.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null)
        {
            holder=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.slidingmenu_listitem,null);
            holder.layout= (LinearLayout) convertView.findViewById(R.id.menu_item_layout);
            holder.line=convertView.findViewById(R.id.menu_line);
            holder.image= (ImageView) convertView.findViewById(R.id.menu_icon);
            holder.title= (TextView) convertView.findViewById(R.id.menu_item);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        holder.title.setText(mMenuItem[position]);
        if(position==4){
            holder.line.setVisibility(View.VISIBLE);
        }
        if(mSelect==position)
        {
            holder.layout.setBackgroundColor(mContext.getResources().getColor(R.color.menu_item_backgroud_press));
            holder.image.setImageResource(mMenuIconChecked[position]);
            holder.title.setTextColor(mContext.getResources().getColor(R.color.toolbar_backgroud));
        }else
        {
            holder.layout.setBackgroundColor(mContext.getResources().getColor(R.color.menu_backgroud));
            holder.image.setImageResource(mMenuIcon[position]);
            holder.title.setTextColor(mContext.getResources().getColor(android.R.color.white));
        }
        return convertView;
    }

    class ViewHolder {
        public LinearLayout layout;
        public View line;
        public ImageView image;
        public TextView title;
    }
}
