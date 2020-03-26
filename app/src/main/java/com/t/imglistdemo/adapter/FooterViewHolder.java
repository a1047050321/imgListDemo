package com.t.imglistdemo.adapter;

import android.view.View;
import android.widget.TextView;

import com.t.imglistdemo.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FooterViewHolder extends RecyclerView.ViewHolder {

    private TextView tipTv;

    public FooterViewHolder(@NonNull View itemView) {
        super(itemView);
        tipTv = itemView.findViewById(R.id.tv_foot_tip);
    }


    public TextView getTipTv() {
        return tipTv;
    }

    public void setTipTv(TextView tipTv) {
        this.tipTv = tipTv;
    }
}
