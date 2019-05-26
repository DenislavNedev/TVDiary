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
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> namesOfShows = new ArrayList<>();
    private ArrayList<String> coverImagesOfShows = new ArrayList<>();
    private List<Show> shows;
    private Context mContext;
    private OnShowListener onShowListener;

    public RecyclerViewAdapter(List<Show> shows, OnShowListener onShowListener, Context mContext) {
        this.shows = shows;
        this.onShowListener = onShowListener;
        this.namesOfShows = getNamesOfShows(shows);
        this.coverImagesOfShows = getImagesOfShows(shows);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        return new ViewHolder(view, onShowListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.nameText.setText(namesOfShows.get(position));

        Glide.with(mContext)
                .asBitmap()
                .load(coverImagesOfShows.get(position))
                .into(viewHolder.coverImage);
    }

    @Override
    public int getItemCount() {
        return namesOfShows.size();
    }


    private ArrayList<String> getNamesOfShows(List<Show> showsList) {
        ArrayList<String> showsNames = new ArrayList<>();

        for (Show show : showsList) {
            showsNames.add(show.getName());
        }

        return showsNames;
    }

    private ArrayList<String> getImagesOfShows(List<Show> showsList) {
        ArrayList<String> showsImages = new ArrayList<>();

        for (Show show : showsList) {
            showsImages.add(show.getImage().getMedium());
        }
        return showsImages;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameText;
        ImageView coverImage;
        RelativeLayout parentLayout;
        OnShowListener onShowListener;

        public ViewHolder(@NonNull View itemView, OnShowListener onShowListener) {
            super(itemView);
            this.nameText = itemView.findViewById(R.id.text_name);
            this.coverImage = itemView.findViewById(R.id.coverImage);
            this.parentLayout = itemView.findViewById(R.id.parent_layout);
            this.onShowListener = onShowListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onShowListener.onShowLick(getAdapterPosition(), shows);
        }
    }

    public interface OnShowListener {
        void onShowLick(int position, List<Show> shows);
    }
}