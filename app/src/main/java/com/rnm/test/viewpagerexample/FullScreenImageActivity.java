package com.rnm.test.viewpagerexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mahe on 11/15/2017.
 */

public class FullScreenImageActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Images> list = getIntent().getParcelableArrayListExtra("arrayList");
        Log.d("FullScreenImageActivity","onCreate  "+list);
        if(list!= null) {
            int pos = getIntent().getIntExtra("pos", 1);
            Log.d("FullScreenImageActivity","onCreate-->  "+pos);
            setContentView(R.layout.activity_full_screen_image);
            ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
            ImagePagerAdapter adapter = new ImagePagerAdapter(this, list);
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(0);
            viewPager.setPageTransformer(true, new ZoomOutPageTransforme());


        }
    }


}
