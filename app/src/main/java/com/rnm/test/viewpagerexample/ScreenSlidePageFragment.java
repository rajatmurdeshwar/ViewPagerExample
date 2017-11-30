package com.rnm.test.viewpagerexample;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Mahe on 11/16/2017.
 */

public class ScreenSlidePageFragment extends Fragment {

    private ImageView images;
    private Bitmap myBitmap;
    private Integer itemData;

    public ScreenSlidePageFragment() {
        Log.d("ScreenSlidePageFragment","no-arg Constr ");

    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ScreenSlidePageFragment","onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fullscreen_image, container, false);
        images = rootView.findViewById(R.id.imageView);
         setImageInViewPager();
        return rootView;
    }

    public void setImageInViewPager() {
        try {
            //if image size is too large. Need to scale as below code.
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            myBitmap = BitmapFactory.decodeResource(getResources(), itemData, options);
            if (options.outWidth > 3000 || options.outHeight > 2000) {
                options.inSampleSize = 4;
            } else if (options.outWidth > 2000 || options.outHeight > 1500) {
                options.inSampleSize = 3;
            } else if (options.outWidth > 1000 || options.outHeight > 1000) {
                options.inSampleSize = 2;
            }
            options.inJustDecodeBounds = false;
            myBitmap = BitmapFactory.decodeResource(getResources(), itemData,options);

            if (myBitmap != null) {
                try {
                    if (images != null) {
                        images.setImageBitmap(myBitmap);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
        }
    }

    public void setImageList(Integer integer) {
        this.itemData = integer;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (myBitmap != null) {
            myBitmap.recycle();
            myBitmap = null;
        }
    }
}
