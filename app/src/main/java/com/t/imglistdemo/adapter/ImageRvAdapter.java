package com.t.imglistdemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.t.imglistdemo.R;
import com.t.imglistdemo.entity.Hit;
import com.t.imglistdemo.util.ScreenUtil;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context ctx;
    private ArrayList<Hit> res;
    private ImageRvListener listener;
    private RequestOptions options;
    private int itemWidth;

    @SuppressLint("CheckResult")
    public ImageRvAdapter(Context ctx, ArrayList<Hit> res) {
        this.ctx = ctx;
        this.res = res;
        options = new RequestOptions();
        options.skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .placeholder(R.drawable.replace)
                .error(R.drawable.replace);
        itemWidth = ScreenUtil.getInstance(ctx.getApplicationContext()).getScreenWidth() / 3;
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
        String targetUrl = hit.getWebformatURL();
        if (null == holder || null == targetUrl || targetUrl.equals("")) {
            return;
        }

        float radio = ((float) hit.getImageHeight()) / hit.getImageWidth();
        ViewGroup.LayoutParams params = holder.getIv().getLayoutParams();
        params.width = itemWidth;
        params.height = (int) (itemWidth * radio);

        Glide.with(ctx)
                .load(targetUrl)
                .apply(options)
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
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        ImgListHolder imgListHolder = (ImgListHolder) holder;
        Glide.with(ctx).clear(imgListHolder.getIv());
        super.onViewRecycled(holder);
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
