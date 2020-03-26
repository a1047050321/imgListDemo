package com.t.imglistdemo.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.t.imglistdemo.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImgListHolder extends RecyclerView.ViewHolder {

    private ImageView iv;
    private TextView tv;

    public ImgListHolder(@NonNull View itemView) {
        super(itemView);
        iv = itemView.findViewById(R.id.item_iv);
        tv = itemView.findViewById(R.id.item_tv_like);
    }

    public ImageView getIv() {
        return iv;
    }

    public void setIv(ImageView iv) {
        this.iv = iv;
    }

    public TextView getTv() {
        return tv;
    }

    public void setTv(TextView tv) {
        this.tv = tv;
    }
}
