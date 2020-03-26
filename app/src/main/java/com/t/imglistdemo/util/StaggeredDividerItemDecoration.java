package com.t.imglistdemo.util;

import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class StaggeredDividerItemDecoration  extends RecyclerView.ItemDecoration {
    private Context context;
    private float interval;
    private int spanCount;

    public StaggeredDividerItemDecoration(Context context, float interval, int spanCount) {
        this.context = context;
        this.interval = interval;
        this.spanCount = spanCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
        int spanIndex = params.getSpanIndex();
        int interval = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                this.interval, context.getResources().getDisplayMetrics());

        if (spanIndex % spanCount == 0) {
            outRect.right = interval / 2;
        } else {
            outRect.left = interval / 2;
        }

        outRect.bottom = interval;
    }
}
