package com.wudebin.bicyclerental.personalsetting;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.os.StatFs;
import android.widget.ImageView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.wudebin.bicyclerental.util.LogUtil;

import org.apache.http.Header;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BitmapUtil {
    public static void downloadImage(String url, final ImageView iamgeview) {
        AsyncHttpClient client = new AsyncHttpClient();
        String[] allowedTypes = new String[] {
                "image/png", "image/jpeg", "image/jpg"
        };
        client.post(url, new BinaryHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        if (bytes.length > 0) {
                            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            BitmapUtil.saveBmpToSd(bmp, "photo.jpg", 100);
                            iamgeview.setImageBitmap(bmp);

                        }
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                    }
                }
        );
    }


	/***
	 * Image compression
	 */
	public static Bitmap getBitmap(Bitmap bitmap, int screenWidth,
			int screenHight) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scale = (float) screenWidth / w;
		float scale2 = (float) screenHight / h;
		matrix.postScale(scale, scale);
		return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
	}

	/***
	 * Save the picture to the SD card
	 */
	private static int FREE_SD_SPACE_NEEDED_TO_CACHE = 1;
	private static int MB = 1024 * 1024;
	public final static String DIR = "/sdcard/howdo";

	public static void saveBmpToSd(Bitmap bm, String name, int quantity) {
		if (FREE_SD_SPACE_NEEDED_TO_CACHE > freeSpaceOnSd()) {
			return;
		}
		if (!Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState()))
			return;
		String filename = name;
		File dirPath = new File(DIR);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}

		File file = new File(DIR + "/" + filename);
		try {
			file.createNewFile();
			OutputStream outStream = new FileOutputStream(file);
			bm.compress(Bitmap.CompressFormat.JPEG, quantity, outStream);
			outStream.flush();
			outStream.close();

		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

    /***
     * Read the SDcard picture
     */
	public static Bitmap GetBitmapSDcard(String picname, int quantity) {

		Bitmap map = null;
		if (picname == null)
			return null;

		if (Exist(DIR + "/" + picname)) {
			map = BitmapFactory.decodeFile(DIR + "/" + picname);
		} else {
			LogUtil.e("BitmapUtil.GetBitmap", "pic not exist");
		}
		return map;
	}

	/***
	 * Determine the image exists
	 */
	public static boolean Exist(String url) {
		File file = new File( url);
		return file.exists();
	}

	/**get SDcard  remaining space */
	private static int freeSpaceOnSd() {
		StatFs stat = new StatFs(Environment.getExternalStorageDirectory()
				.getPath());
		double sdFreeMB = ((double) stat.getAvailableBlocks() * (double) stat
				.getBlockSize()) / MB;

		return (int) sdFreeMB;
	}

}
