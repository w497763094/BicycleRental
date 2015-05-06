package com.wudebin.bicyclerental.personalsetting;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;


import com.wudebin.bicyclerental.R;
import com.wudebin.bicyclerental.activity.BaseActivity;
import com.wudebin.bicyclerental.imagecropper.CropImageActivity;

import java.io.File;


public class UserPhotoActivity extends BaseActivity {
    private static final String ACTION_TAKEPHOTO="com.howdo.howdostudent.action.corpimage";
    private static final String ACTION_COMMONSCHOOL="android.intent.action.MAIN";
    public static final String ACTION_BROADCAST="broadcast";
    private static final int TAKE_PHOTO = 0;
    private static final int FROM_GALLERY = 1;
    private static final int RESULT_CROPPER=3;
    private ImageView mtakephoto,mtakeFile;
    private int window_width, window_height;
    private DragImageView mDragImageView;
    private int state_height;
    private ViewTreeObserver viewTreeObserver;
    private String picname,picurl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userphoto);

        initview();
        getPIC();

    }



    private void  initview(){
     mtakephoto=(ImageView)findViewById(R.id.userphoto_btn_photo);
     mtakeFile=(ImageView)findViewById(R.id.userphoto_btn_file);
     mtakephoto.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             picurl=BitmapUtil.DIR;
             takePhoto();
         }
     });
     mtakeFile.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             picurl=BitmapUtil.DIR;
             fromGallery();
         }
     });
    }
    private void getPIC(){
        WindowManager manager = getWindowManager();
        window_width = manager.getDefaultDisplay().getWidth();
        window_height = manager.getDefaultDisplay().getHeight();

        mDragImageView = (DragImageView) findViewById(R.id.userphoto_show);

        Bitmap bmp = null;
        try {
            bmp = BitmapUtil.GetBitmapSDcard("photo.jpg",100);
            mDragImageView.setImageBitmap(BitmapUtil.getBitmap(bmp,window_width,window_height));
            mDragImageView.setmActivity(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        viewTreeObserver = mDragImageView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                if (state_height == 0) {
                    Rect frame = new Rect();
                    getWindow().getDecorView()
                            .getWindowVisibleDisplayFrame(frame);
                    state_height = frame.top;
                    mDragImageView.setScreen_H(window_height-state_height);
                    mDragImageView.setScreen_W(window_width);
                }

            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TAKE_PHOTO && resultCode == this.RESULT_OK){
            Uri uri = Uri.fromFile(new File(picurl + "/" + picname));
            startCropImage(uri, picurl + "/" + picname);
        }else if(requestCode == FROM_GALLERY&&resultCode == this.RESULT_OK){
            startCropImage(data.getData(), picurl + "/" + picname);
        }else if(requestCode == RESULT_CROPPER&&resultCode == this.RESULT_OK){
            Intent backintent = new Intent();
            backintent.setAction(ACTION_COMMONSCHOOL);
            backintent.putExtra("state",true);
            setResult(Activity.RESULT_OK,backintent);
            finish();
        }
    }

    public void takePhoto() {
        if (hasSdcard()) {
            hasCachePath();
            picname="photo.jpg";
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(picurl, picname)));
            startActivityForResult(intent, TAKE_PHOTO);
        }
    }
    public void fromGallery(){
        if (hasSdcard()) {
            hasCachePath();
            picname="photo.jpg";
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent,FROM_GALLERY);
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

    public void hasCachePath() {
        File file = new File(picurl);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public void startCropImage( Uri uri,String filePath ) {
        Intent intent = new Intent(this,CropImageActivity.class);
        intent.setData(uri);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(filePath)));
        startActivityForResult(intent, RESULT_CROPPER);
    }

}
