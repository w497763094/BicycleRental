package com.wudebin.bicyclerental.personalsetting;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;
import com.nineoldandroids.view.ViewHelper;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.wudebin.bicyclerental.R;
import com.wudebin.bicyclerental.constant.ActivityAction;
import com.wudebin.bicyclerental.fragment.AbstractBaseFragment;
import com.wudebin.bicyclerental.observablerecyclerview.ObservableRecyclerView;
import com.wudebin.bicyclerental.observablerecyclerview.ObservableScrollViewCallbacks;
import com.wudebin.bicyclerental.observablerecyclerview.ScrollState;
import com.wudebin.bicyclerental.observablerecyclerview.ScrollUtils;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PersonalSettingFragment extends AbstractBaseFragment implements ObservableScrollViewCallbacks {
//    private static final String ACTION_CHANGEPHONEMAIL = "com.howdo.commonschool.action.changephonemail";
    private static final int ACTIVITY_USERPHTOT_CODE = 1;
    private static final int ACTION_TYPE_PHONE = 0;
    private static final int ACTION_TYPE_MAIL = 1;
    private Context mContext;
    private MaterialEditText mEditTextsign, EditTextmail;
    private ImageView mPhoto;
    private String mPhotourl = "/sdcard/howdo/photo.jpg";

    private static final float MAX_TEXT_SCALE_DELTA = 0.3f;
    private static final boolean TOOLBAR_IS_STICKY = true;

    private View mToolbarView;
    private View mImageView;
    private View mImageViewbottom;
    private View mOverlayView;
    private View mRecyclerViewBackground;
    private ObservableRecyclerView mRecyclerView;
    private TextView name;
    private int mActionBarSize;
    private int mFlexibleSpaceImageHeight;
    private int mToolbarColor;
    private Toolbar mToolbar;
    private PersonSettingRecyclerAdapter mAdapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personsetting, null);
        mContext = view.getContext();
        initView(view);
        return view;
    }

    public void initView(View v) {
        mToolbar = (Toolbar) v.findViewById(R.id.toolbar);
        mToolbar.setBackgroundColor(Color.TRANSPARENT);
        ((ActionBarActivity) getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_drawer);
        mPhoto = (ImageView) v.findViewById(R.id.perset_photo);
        mImageView = v.findViewById(R.id.perset_photo);
        mImageViewbottom = v.findViewById(R.id.perset_photo_bottom);
        mOverlayView = v.findViewById(R.id.perset_overlay);
        mRecyclerViewBackground = v.findViewById(R.id.perset_list_background);
        mRecyclerView = (ObservableRecyclerView) v.findViewById(R.id.perset_recycler);
        name = (TextView) v.findViewById(R.id.perset_title);
        getpersondetail();
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "修改名字", Toast.LENGTH_SHORT).show();
            }
        });

        mFlexibleSpaceImageHeight = getResources().getDimensionPixelSize(R.dimen.perset_image_height);
        mActionBarSize = getActionBarSize();
        mToolbarColor = getResources().getColor(R.color.toolbar_backgroud);


        mRecyclerView.setScrollViewCallbacks(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(false);
        final View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.perset_recycler_header, null);
        headerView.post(new Runnable() {
            @Override
            public void run() {
                headerView.getLayoutParams().height = mFlexibleSpaceImageHeight;
            }
        });
        mAdapter = new PersonSettingRecyclerAdapter(mContext, getDummyData(2), headerView);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemBottomClickListener(new PersonSettingRecyclerAdapter.MyItemBottomClickListener() {
            @Override
            public void onItemBottomClick(View view, int postion) {
//                Intent intent = new Intent();
//                intent.setAction(ACTION_CHANGEPHONEMAIL);
//                intent.putExtra("type", 2);
//                startActivityForResult(intent, ACTION_TYPE_MAIL);
            }
        });
        mAdapter.setOnItemTopClickListener(new PersonSettingRecyclerAdapter.MyItemTopClickListener() {
            @Override
            public void onItemTopClick(View view, int postion) {
//                Intent intent = new Intent();
//                intent.setAction(ACTION_CHANGEPHONEMAIL);
//                intent.putExtra("type", 1);
//                startActivityForResult(intent, ACTION_TYPE_PHONE);
            }
        });
        mAdapter.setOnHeaderClickListener(new PersonSettingRecyclerAdapter.MyHeaderClickListener() {
            @Override
            public void onHeaderClick(View view, int postion) {
                Intent intent = new Intent();
                intent.setAction(ActivityAction.CHANGEPHONT_ACTIVITY);
                startActivityForResult(intent, ACTIVITY_USERPHTOT_CODE);
            }
        });
        mAdapter.setOnSignClickListener(new PersonSettingRecyclerAdapter.MySignClickListener() {
            @Override
            public void onSignClick(View view, int postion) {
                Toast.makeText(mContext, "修改个性签名", Toast.LENGTH_SHORT).show();
            }
        });

        mToolbarView = v.findViewById(R.id.toolbar);
        if (!TOOLBAR_IS_STICKY) {
            mToolbarView.setBackgroundColor(Color.TRANSPARENT);
        }
        getActivity().setTitle(null);


        final View contentView = getActivity().getWindow().getDecorView().findViewById(android.R.id.content);
        contentView.post(new Runnable() {
            @Override
            public void run() {
                mRecyclerViewBackground.getLayoutParams().height = contentView.getHeight();
            }
        });

        final float scale = 1 + MAX_TEXT_SCALE_DELTA;
        mRecyclerViewBackground.post(new Runnable() {
            @Override
            public void run() {
                ViewHelper.setTranslationY(mRecyclerViewBackground, mFlexibleSpaceImageHeight);
            }
        });
        ViewHelper.setTranslationY(mOverlayView, mFlexibleSpaceImageHeight);
        name.post(new Runnable() {
            @Override
            public void run() {
                ViewHelper.setTranslationY(name, (int) (mFlexibleSpaceImageHeight - name.getHeight() * scale));
                ViewHelper.setPivotX(name, 0);
                ViewHelper.setPivotY(name, 0);
                ViewHelper.setScaleX(name, scale);
                ViewHelper.setScaleY(name, scale);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == ACTIVITY_USERPHTOT_CODE) {
            boolean state = data.getBooleanExtra("state", false);
            if (true == state) {
                mPhoto.setImageBitmap(BitmapUtil.GetBitmapSDcard("photo.jpg", 100));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getpersondetail() {

    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        // Translate overlay and image
        float flexibleRange = mFlexibleSpaceImageHeight - mActionBarSize;
        int minOverlayTransitionY = mActionBarSize - mOverlayView.getHeight();
        ViewHelper.setTranslationY(mOverlayView, ScrollUtils.getFloat(-scrollY, minOverlayTransitionY, 0));
        ViewHelper.setTranslationY(mImageView, ScrollUtils.getFloat(-scrollY / 2, minOverlayTransitionY, 0));
        ViewHelper.setTranslationY(mImageViewbottom, ScrollUtils.getFloat(-scrollY / 2, minOverlayTransitionY, 0));
        // Translate list background
        ViewHelper.setTranslationY(mRecyclerViewBackground, Math.max(0, -scrollY + mFlexibleSpaceImageHeight));

        // Change alpha of overlay
        ViewHelper.setAlpha(mOverlayView, ScrollUtils.getFloat((float) scrollY / flexibleRange, 0, 1));

        // Scale title text
        float scale = 1 + ScrollUtils.getFloat((flexibleRange - scrollY) / flexibleRange, 0, MAX_TEXT_SCALE_DELTA);
        setPivotXToTitle();
        ViewHelper.setPivotY(name, 0);
        ViewHelper.setScaleX(name, scale);
        ViewHelper.setScaleY(name, scale);

        // Translate title text
        int maxTitleTranslationY = (int) (mFlexibleSpaceImageHeight - name.getHeight() * scale);
        int titleTranslationY = maxTitleTranslationY - scrollY;
        if (TOOLBAR_IS_STICKY) {
            titleTranslationY = Math.max(0, titleTranslationY);
        }
        ViewHelper.setTranslationY(name, titleTranslationY);


        if (TOOLBAR_IS_STICKY) {
            // Change alpha of toolbar background
            if (-scrollY + mFlexibleSpaceImageHeight <= mActionBarSize) {
                mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(1, mToolbarColor));
            } else {
                mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, mToolbarColor));
            }
        } else {
            // Translate Toolbar
            if (scrollY < mFlexibleSpaceImageHeight) {
                ViewHelper.setTranslationY(mToolbarView, 0);
            } else {
                ViewHelper.setTranslationY(mToolbarView, -scrollY);
            }
        }

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setPivotXToTitle() {
        Configuration config = getResources().getConfiguration();
        if (Build.VERSION_CODES.JELLY_BEAN_MR1 <= Build.VERSION.SDK_INT
                && config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
            ViewHelper.setPivotX(name, getActivity().findViewById(android.R.id.content).getWidth());
        } else {
            ViewHelper.setPivotX(name, 0);
        }
    }

    protected int getActionBarSize() {
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[]{R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedArray a = getActivity().obtainStyledAttributes(typedValue.data, textSizeAttr);
        int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        return actionBarSize;
    }

    public static ArrayList<String> getDummyData(int num) {
        ArrayList<String> items = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            items.add("Item " + i);
        }
        return items;
    }
}
