package com.t.imglistdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.t.imglistdemo.R;
import com.t.imglistdemo.activity.MainActivity;
import com.t.imglistdemo.entity.Hit;
import com.t.imglistdemo.entity.ImageEntity;
import com.t.imglistdemo.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class ImageRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context ctx;
    private ArrayList<Hit> res;
    private ImageRvListener listener;

    public ImageRvAdapter(Context ctx, ArrayList<Hit> res) {
        this.ctx = ctx;
        this.res = res;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(ctx).inflate(R.layout.layout_item, null, false);
        ImgListHolder holder = new ImgListHolder(itemView);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        bindImgHoler((ImgListHolder) holder, res.get(position), position);
    }

    private void bindImgHoler(ImgListHolder holder, final Hit hit, int position) {
        Glide.with(ctx)
                .load(hit.getLargeImageURL())
                .crossFade()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.replace)
                .error(R.drawable.replace)
                .into(holder.getIv());
        holder.getTv().setText(String.valueOf(hit.getFavorites()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onRvItemClick(hit);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return res == null ? 0 : res.size();
    }

    public void setData(ArrayList<Hit> res) {
        this.res = res;
        notifyDataSetChanged();
    }

    public ArrayList<Hit> getData() {
        return res;
    }

    public void addData(ArrayList<Hit> addData) {
        this.res.addAll(addData);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }

    public interface ImageRvListener {
        void onRvItemClick(Hit item);
    }

    public void setImageRvListener(ImageRvListener listener) {
        this.listener = listener;
    }

}
