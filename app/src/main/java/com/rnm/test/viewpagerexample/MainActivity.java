package com.rnm.test.viewpagerexample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends Activity implements ListAdapter.ListItemClickListener {

    RecyclerView recyclerView;
    ListAdapter adapter;
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int PHOTO_COUNT = 16;
    ProgressBar empty;
    List<Images> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        empty = (ProgressBar) findViewById(R.id.empty);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));


        //recyclerView.setLayoutManager(gridLayoutManager);
       // recyclerView.addItemDecoration(new GridMarginDecoration(R.dimen.grid_item_spacing));
        recyclerView.setHasFixedSize(true);

        RetrofitService service = new RestAdapter.Builder()
                .setEndpoint(RetrofitService.ENDPOINT)
                .build()
                .create(RetrofitService.class);
        service.getFeed(new Callback<List<Images>>() {
            @Override
            public void success(List<Images> imagesList, Response response) {
                arrayList = imagesList;
                adapter = new ListAdapter(MainActivity.this, imagesList.subList(imagesList.size() - PHOTO_COUNT, imagesList
                        .size()), MainActivity.this);
                recyclerView.setAdapter(adapter);
                empty.setVisibility(View.GONE);

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(getClass().getCanonicalName(), "Error retrieving Unsplash feed:", error);
            }
        });
        Log.d(TAG,"arraayList "+arrayList);
    }



    @Override
    public void onListItemClick(int clickedItemIndex) {

        Log.d(TAG, "onListItemClick "+clickedItemIndex);
        Intent i = new Intent(this, FullScreenImageActivity.class);
        i.putExtra("pos", clickedItemIndex);
        i.putParcelableArrayListExtra("arrayList", (ArrayList<? extends Parcelable>) arrayList);
        startActivity(i);


    }

    private static class GridMarginDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public GridMarginDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.top = space;
            outRect.right = space;
            outRect.bottom = space;
        }
    }
}


