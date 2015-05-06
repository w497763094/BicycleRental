package com.wudebin.bicyclerental.systemsetting;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wudebin.bicyclerental.R;
import com.wudebin.bicyclerental.constant.ActivityAction;
import com.wudebin.bicyclerental.fragment.AbstractBaseFragment;

public class SystemSettingFragment extends AbstractBaseFragment {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private  int[] mdata_text={R.string.changepwd,R.string.checkversion,R.string.feedback,R.string.contactus};
    private  int[] mdata_icon={R.drawable.sys_changepwd,R.drawable.sys_update,R.drawable.sys_feedback,R.drawable.sys_contactus};
    private Toolbar mToolbar;
    private SystemSettingRecycleAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private LinearLayout mLinearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_syssetting,null);
        mContext=inflater.getContext();
        initView(view);
        return view;
    }

    public void initView(View v)
    {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView= (RecyclerView)v.findViewById(R.id.syssetting_recycleview);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mLinearLayout=(LinearLayout)v.findViewById(R.id.sysseting_exit);
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });

        mToolbar=(Toolbar)v.findViewById(R.id.toolbar);
        mAdapter= new SystemSettingRecycleAdapter(mdata_text,mdata_icon);
        mToolbar.setTitle(R.string.menu_syssetting);
        mToolbar.setTitleTextColor(Color.WHITE);

        ((ActionBarActivity) getActivity()).setSupportActionBar(mToolbar);
        ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.ic_drawer);
//        mRecyclerView.addItemDecoration(new RecycleDivider(mContext,mLayoutManager.getOrientation()));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new SystemSettingRecycleAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent=new Intent();
                switch (postion){
                    case 0:
                        intent.setAction(ActivityAction.CHANGE_PWD_ACTIVITY);
                        startActivity(intent);
                        break;
                    case 1:
                        checkversion();
                        break;
                    case 2:
                        intent.setAction(ActivityAction.FEEDBACK_ACTIVITY);
                        startActivity(intent);
                        break;
                    case 3:
                        intent.setAction(ActivityAction.CONTACTUS_ACTIVITY);
                        startActivity(intent);
                        break;
                }
            }
        });

    }

    private void checkversion(){
        showdialog("http://www.baidu.com",true);
    }
    private void exit(){
        Toast.makeText(mContext, getString(R.string.exit), Toast.LENGTH_SHORT).show();
    }
    private void showdialog(final String url, boolean flag) {
        if (flag) {

            MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                    .title(getString(R.string.find_new_version))
                    .titleColor(R.color.toolbar_backgroud)
                    .content("版本号：1.0.1\n" +
                            " \n" +
                            "更新内容：\n" +
                            "1.修复稳定性\n" +
                            "2.又修复了稳定性\n" +
                            "3.修复部分BUG")
                    .contentColor(R.color.dividercolor).positiveText("更新")
                    .negativeText("取消").callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            super.onPositive(dialog);
                            Uri uri = Uri.parse(url);
                            Intent it = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(it);
                        }

                    })
                    .build();
            dialog.show();
        }else{
            Toast.makeText(getActivity(), getString(R.string.is_latest_version),
                    Toast.LENGTH_LONG).show();
        }
    }
}
