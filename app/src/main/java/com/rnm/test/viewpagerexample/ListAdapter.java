package com.rnm.test.viewpagerexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Mahe on 11/15/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {


    private Context mContext;
    final private ListItemClickListener mOnClickListener;
    private List<Images> list;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public ListAdapter(Context context,  List<Images> imagesList, ListItemClickListener listener) {
        mContext = context;
        mOnClickListener = listener;
        list = imagesList;
    }


    @Override
    public ListAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.image_list, parent, false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ListViewHolder holder, int position) {

        Picasso.with(mContext).load("https://unsplash.it/"+mContext.getResources().getDisplayMetrics().widthPixels+"?image="+list.get(position).id)
                .placeholder(R.color.placeholder)
                .into(holder.view);


    }

    public Images getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).id;
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ForegroundImageView view;

        public ListViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.imageView);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();

            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
