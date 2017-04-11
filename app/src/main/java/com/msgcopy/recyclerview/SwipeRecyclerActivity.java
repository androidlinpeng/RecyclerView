package com.msgcopy.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SwipeRecyclerActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefresh = null;

    private RecyclerView recyclerView = null;

    private LinearLayoutManager linearLayoutManager;

    private Adapter mAdapter ;

    private int lastVisibleItem;

    //是否正在加载更多的标志
    private boolean isMoreLoading=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_recycler);

        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeRefresh.setProgressBackgroundColorSchemeResource(android.R.color.white);
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        List<String> mlist = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            mlist.add("新闻"+i);
        }
        mAdapter = new Adapter(this,mlist);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new ItemListDivider(this));
        recyclerView.setAdapter(mAdapter);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<String> mlist = new ArrayList<String>();
                        for (int i = 0; i < 3; i++) {
                            mlist.add("新闻"+i);
                        }
                        mAdapter.addRefreshBeans(mlist);
                        swipeRefresh.setRefreshing(false);
                    }
                },3500);
            }
        });
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItem+1==mAdapter.getItemCount()){
                    if (!isMoreLoading){
                        isMoreLoading = true;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                List<String> mlist = new ArrayList<String>();
                                for (int i = 0; i < 3; i++) {
                                    mlist.add("新闻"+i);
                                }
                                mAdapter.addMoreBeans(mlist);
                                isMoreLoading = false;
                            }
                        },2000);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
        mAdapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(String text) {
                Toast.makeText(SwipeRecyclerActivity.this, "text:"+text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static class ItemListDivider extends RecyclerView.ItemDecoration{

        private Drawable drawable=null;

        public ItemListDivider(Context cxt){
            this.drawable=cxt.getResources().getDrawable(R.drawable.divider_article_list);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + drawable.getIntrinsicHeight();

                drawable.setBounds(left, top, right, bottom);
                drawable.draw(c);
            }
        }
    }
}













