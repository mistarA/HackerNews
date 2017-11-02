package com.project.playohackernews.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.playohackernews.R;
import com.project.playohackernews.models.Hit;
import com.project.playohackernews.views.activities.NewsFeedWebViewActivity;

import java.util.ArrayList;
import java.util.List;


public class HitRecyclerAdapter extends RecyclerView.Adapter<HitRecyclerAdapter.HitItemViewHolder> {
    private Context mContext;
    private List<Hit> mHitList;

    public HitRecyclerAdapter(Context context, List<Hit> projectModels) {
        this.mContext = context;
        this.mHitList = projectModels;
    }

    public void filterList(ArrayList<Hit> projectModelArrayList) {
        //This method will filter the list
        //here we are passing the filtered data
        //and assigning it to the list with notifydatasetchanged method
        this.mHitList = projectModelArrayList;
        notifyDataSetChanged();
    }

    public static class HitItemViewHolder extends RecyclerView.ViewHolder {

        TextView mHitTitle;
        TextView mHitAuthorName;

        public HitItemViewHolder(View itemView) {
            super(itemView);
            mHitTitle = (TextView) itemView.findViewById(R.id.hit_name);
            mHitAuthorName = (TextView) itemView.findViewById(R.id.hit_author_name);
        }
    }

    @Override
    public HitItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hit_item_layout, parent, false);
        return new HitItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HitItemViewHolder holder, int position) {

        final Hit hit = mHitList.get(position);
        holder.mHitTitle.setText(hit.getTitle());
        holder.mHitAuthorName.setText(hit.getAuthor());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsFeedWebViewActivity.class);
                intent.putExtra(NewsFeedWebViewActivity.INTENT_FEED_DETAIL_URL_EXTRA, hit.getUrl());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHitList == null ? 0 : mHitList.size();
    }

    public void addAllItems(List<Hit> projectModelList) {
        mHitList.addAll(projectModelList);
        notifyDataSetChanged();
    }

    public void addItems(Hit projectModel) {
        mHitList.add(projectModel);
        notifyDataSetChanged();
    }
}