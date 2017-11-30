package com.rnm.test.viewpagerexample;

import android.content.ContentValues;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Mahe on 11/17/2017.
 */

public class ImagePagerAdapter extends PagerAdapter{

    Context mContext;
    List<Images> imagesList;

    public ImagePagerAdapter(Context context,final List<Images> list) {
        this.mContext = context;
        this.imagesList =list;
    }


    @Override
    public int getCount() {
        if(imagesList.size()!= 0){
            return imagesList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.fullscreen_image, container, false);
         ImageView i =v.findViewById(R.id.imageView);
//        Picasso.with(mContext).load("https://picsum.photos/200/300?image="+imagesList.get(position).id)
//                .placeholder(R.color.placeholder)
//                .into(i);
        Picasso.with(mContext).load("https://unsplash.it/"+mContext.getResources().getDisplayMetrics().widthPixels+"?image="+imagesList.get(position).id)
                .placeholder(R.color.placeholder)
                .into(i);

        ((ViewPager) container).addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);
    }
}
