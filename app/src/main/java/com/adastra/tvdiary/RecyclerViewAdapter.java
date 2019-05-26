package com.adastra.tvdiary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> mTextNames = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> mTextNames, Context mContext) {
        this.mTextNames = mTextNames;
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
    }

    @Override
    public int getItemCount() {
        return mTextNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.text_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}