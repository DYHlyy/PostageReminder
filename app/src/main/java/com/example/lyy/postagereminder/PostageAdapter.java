package com.example.lyy.postagereminder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by lyy on 2017/7/26.
 */

public class PostageAdapter extends RecyclerView.Adapter<PostageAdapter.ViewHolder> {
    private Context mContext;
    private List<Postage> mPostageList;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if ((mContext == null)) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.postage_item, parent, false);

        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Postage postage = mPostageList.get(position);
                Intent intent = new Intent(mContext, PostageActivity.class);
                intent.putExtra(PostageActivity.POSTAGE_NAME, postage.getName());
                intent.putExtra(PostageActivity.POSTAGE_IMAGE_ID, postage.getImageId());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Postage postage = mPostageList.get(position);
        holder.postageName.setText(postage.getName());
        Glide.with(mContext).load(postage.getImageId()).into(holder.postageImage);
    }

    @Override
    public int getItemCount() {
        return mPostageList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView postageImage;
        TextView postageName;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            postageImage = view.findViewById(R.id.postage_image);
            postageName = view.findViewById(R.id.postage_name);
        }
    }

    public PostageAdapter(List<Postage> postageList) {
        mPostageList = postageList;
    }
}


