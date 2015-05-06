package com.wudebin.bicyclerental.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.wudebin.bicyclerental.activity.BaseActivity;
import com.wudebin.bicyclerental.constant.ActivityAction;

import com.wudebin.bicyclerental.R;


public class GuideActivity extends BaseActivity {

    private static final int[] resource = new int[]{R.drawable.welcome1, R.drawable.welcome4,
            R.drawable.welcome3, R.drawable.welcome4};

    private Button mBtnLogin;
    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_guide);
        init();

        MyFragmentStatePager adapter = new MyFragmentStatePager(getSupportFragmentManager());
        ColorAnimationView colorAnimationView = (ColorAnimationView) findViewById(R.id.ColorAnimationView);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        colorAnimationView.setmViewPager(viewPager, resource.length);
        colorAnimationView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("TAG", "onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("TAG", "onPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e("TAG", "onPageScrollStateChanged");
            }
        });
        // Four : Also ,you can call this method like this:
        // colorAnimationView.setmViewPager(viewPager,this,resource.length,0xffFF8080,0xff8080FF,0xffffffff,0xff80ff80);

    }


    public void init()
    {
        mBtnLogin= (Button) findViewById(R.id.guide_login);
        mBtnRegister= (Button) findViewById(R.id.guide_register);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(ActivityAction.LOGIN_ACTIVITY);
                startActivity(intent);
            }
        });
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(ActivityAction.REGISTER_ACTIVITY);
                startActivity(intent);
            }
        });
    }

    public class MyFragmentStatePager
            extends FragmentStatePagerAdapter {

        public MyFragmentStatePager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new MyFragment(position);
        }

        @Override
        public int getCount() {
            return resource.length;
        }
    }

    @SuppressLint("ValidFragment") public class MyFragment
            extends Fragment {
        private int position;

        public MyFragment(int position) {
            this.position = position;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(resource[position]);
            return imageView;
        }
    }
}
