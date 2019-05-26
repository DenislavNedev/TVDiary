package com.adastra.tvdiary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> mTextNames = new ArrayList<>();
    private ArrayList<String> imagesURL = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> mTextNames, ArrayList<String> imagesURL, Context mContext) {
        this.mTextNames = mTextNames;
        this.imagesURL = imagesURL;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.nameText.setText(mTextNames.get(i));

        Glide.with(mContext)
                .asBitmap()
                .load(imagesURL.get(i))
                .into(viewHolder.coverImage);
    }

    @Override
    public int getItemCount() {
        return mTextNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        ImageView coverImage;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.text_name);
            coverImage = itemView.findViewById(R.id.coverImage);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}