package com.t.imglistdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class CustomRecyclerView extends RecyclerView {

    private static final String TAG = "CustomRecyclerView";

    private View emptyView;

    public CustomRecyclerView(@NonNull Context context) {
        super(context);
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private AdapterDataObserver dataObserve = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            Adapter<?> adapter = getAdapter();
            if (adapter != null && emptyView != null) {
                if (adapter.getItemCount() <= 0) {
                    Log.d(TAG," 显示");
                    emptyView.setVisibility(View.VISIBLE);
                    CustomRecyclerView.this.setVisibility(View.GONE);
                } else {
                    Log.d(TAG," 隐藏");
                    emptyView.setVisibility(View.GONE);
                    CustomRecyclerView.this.setVisibility(View.VISIBLE);
                }
            }
        }
    };

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(dataObserve);
        }
        dataObserve.onChanged();
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }
}
