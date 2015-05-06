package com.wudebin.bicyclerental.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.wudebin.bicyclerental.activity.BaseActivity;
import com.wudebin.bicyclerental.imagecropper.CropImageActivity;
import com.wudebin.bicyclerental.util.BitmapUtil;

import java.io.File;
import java.util.Calendar;

import com.wudebin.bicyclerental.R;

public class ForgetReviewActivity extends BaseActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    public String mSDcardPath = Environment.getExternalStorageDirectory().getPath()+"/commonSchool";
    public String mPhotoName;
    private static final int TAKE_PHOTO = 1;
    private static final int FROM_GALLERY = 2;
    private static final int RESULT_CROPPER = 3;
    private FloatingActionButton mFabPhoto;
    private FloatingActionButton mFabCamera;
    private ImageView mImgPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_review);
        initToolbar();
//        init();
    }
    public void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.people_review));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.back_icon);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

//    public void init()
//    {
//        mFabPhoto= (FloatingActionButton) findViewById(R.id.fab_photo);
//        mFabCamera= (FloatingActionButton) findViewById(R.id.fab_camera);
//        mImgPhoto= (ImageView) findViewById(R.id.forget_review_photo);
//        mFabPhoto.setOnClickListener(this);
//        mFabCamera.setOnClickListener(this);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
//            case R.id.fab_camera:
//                takePhoto();
//                break;
//            case R.id.fab_photo:
//                fromGallery();
//                break;
        }
    }

    public void hasCachePath() {
        File file = new File(mSDcardPath);
        if (!file.exists()) {
            file.mkdir();
        }
    }
    public boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
    public void takePhoto() {
        if (hasSdcard()) {
            hasCachePath();
            mPhotoName = Calendar.getInstance().getTimeInMillis() + ".jpg";
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mSDcardPath, mPhotoName)));
            startActivityForResult(intent, TAKE_PHOTO);
        }
    }
    public void fromGallery(){
        if (hasSdcard()) {
            hasCachePath();
            mPhotoName = Calendar.getInstance().getTimeInMillis() + ".jpg";
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent,FROM_GALLERY);
        }
    }
    public void startCropImage( Uri uri,String filePath ) {
        Intent intent = new Intent(this,CropImageActivity.class);
        intent.setData(uri);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(filePath)));
        startActivityForResult(intent, RESULT_CROPPER);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TAKE_PHOTO && resultCode == RESULT_OK){
            Uri uri = Uri.fromFile(new File(mSDcardPath + "/" + mPhotoName));
            startCropImage(uri, mSDcardPath + "/" + mPhotoName);
        }else if(requestCode == FROM_GALLERY&&resultCode == RESULT_OK){
            startCropImage(data.getData(),mSDcardPath+"/"+mPhotoName);
        }else if(requestCode == RESULT_CROPPER&&resultCode == RESULT_OK){
            mImgPhoto.setImageBitmap( BitmapUtil.decodeSampledBitmapFromResource(mSDcardPath + "/" + mPhotoName, 200, 100));
        }
    }

}
