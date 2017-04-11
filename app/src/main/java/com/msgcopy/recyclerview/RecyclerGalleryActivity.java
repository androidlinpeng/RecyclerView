package com.msgcopy.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class RecyclerGalleryActivity extends AppCompatActivity {

    private RecyclerView gallery_recycler;

    private GalleryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_gallery);

        gallery_recycler = (RecyclerView)findViewById(R.id.gallery_recycler);
        //固定高度
        gallery_recycler.setHasFixedSize(true);
        //创建布局管理器
        LinearLayoutManager layoutManager = new CustomLinearLayoutManager(this);
        //为了ScrollView可以显示RecyclerView 垂直布局
        //RecyclerView.LayoutManager layoutManager = new FullyLinearLayoutManager(this);
        //设置横向
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        //设置布局管理器
        gallery_recycler.setLayoutManager(layoutManager);
        //设置分割线
        gallery_recycler.addItemDecoration(new TestDecoration(this));
        //设置动画
        gallery_recycler.setItemAnimator(new DefaultItemAnimator());
        //创建适配器
        adapter = new GalleryAdapter(this,setData());
        //绑定适配器
        gallery_recycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new GalleryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int flag) {
                if (flag==1){
                    adapter.addItem("新加",0);
                }else {
                    adapter.removeItem(1);
                }
            }
        });

    }

    public List<String> setData(){
        List<String> mlist = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            mlist.add("新闻"+i);
        }
        Log.i("mlist",""+mlist.size());
        return mlist;
    }
}


























