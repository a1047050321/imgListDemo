package com.t.imglistdemo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.t.imglistdemo.R;
import com.t.imglistdemo.adapter.ImageRvAdapter;
import com.t.imglistdemo.common.Constant;
import com.t.imglistdemo.entity.Hit;
import com.t.imglistdemo.entity.ImageEntity;
import com.t.imglistdemo.net.HttpUrl;
import com.t.imglistdemo.net.IResponseListener;
import com.t.imglistdemo.net.NetModel;
import com.t.imglistdemo.util.StaggeredDividerItemDecoration;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView rv;


    private ImageRvAdapter adapter;

    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    private int pageCount = 1;

    private boolean isRefreshing = false;

    private boolean isLoadMore = false;

    private SmartRefreshLayout refreshlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        getImgList(pageCount,true);
    }

    private void initView() {
        rv = findViewById(R.id.rv_img);
        refreshlayout = findViewById(R.id.refreshlayout);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, RecyclerView.VERTICAL);
        rv.setLayoutManager(staggeredGridLayoutManager);
        rv.addItemDecoration(new StaggeredDividerItemDecoration(this, 10, 3));
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int[] first = new int[3];
                staggeredGridLayoutManager.findFirstCompletelyVisibleItemPositions(first);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && (first[0] == 1 || first[1] == 1)) {
                    staggeredGridLayoutManager.invalidateSpanAssignments();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });

        refreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                pageCount = 1;
                getImgList(pageCount, true);
            }
        });

        refreshlayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                pageCount++;
                getImgList(pageCount, false);
                refreshLayout.finishLoadMore();
            }
        });
    }

    private void setImgData(ImageEntity imageEntity, boolean isRefresh) {
        if (adapter == null) {
            adapter = new ImageRvAdapter(this, imageEntity.getHits());
            adapter.setImageRvListener(new ImageRvAdapter.ImageRvListener() {
                @Override
                public void onRvItemClick(Hit item) {
                    turn2Web(item);
                }
            });
            rv.setAdapter(adapter);
        }
        if (isRefresh) {
            refreshlayout.finishRefresh();
            adapter.setData(imageEntity.getHits());
        } else {
            refreshlayout.finishLoadMore();
            adapter.addData(imageEntity.getHits());
        }
    }

    private void turn2Web(Hit item) {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra(Constant.COMMON_ACTIVITY_EXTRA_KEY, item.getLargeImageURL());
        startActivity(intent);
    }

    private void getImgList(int page, final boolean isRefresh) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("key", Constant.API_KEY);
        params.put("page", page);
        params.put("per_page", 30);
        params.put("orientation", "vertical");
        NetModel.getInstance().getImageList(HttpUrl.BASE_URL, params, new IResponseListener<ImageEntity>() {
            @Override
            public void onSuccess(ImageEntity imageEntity) {
                setImgData(imageEntity,isRefresh);
            }

            @Override
            public void onFailure(int code, String msg) {

            }
        });
    }
}
