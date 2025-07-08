package com.app.luoxueheng;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.luoxueheng.adapter.NewsListAdapter;
import com.app.luoxueheng.db.CollectionDbHelper;
import com.app.luoxueheng.entity.CollectionInfo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CollectionListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NewsListAdapter mNewsListAdapter;
    private List<NewsInfo.DataDTO> mDataDTOList=new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onResume() {
        super.onResume();
        //获取数据
        List<CollectionInfo> collectionInfoList= CollectionDbHelper.getInstance(CollectionListActivity.this).queryCollectionListData(null);
        mDataDTOList.clear(); // 清除旧数据
        Gson gson=new Gson();
        for(int i=0;i< collectionInfoList.size();i++){
            mDataDTOList.add(gson.fromJson(collectionInfoList.get(i).getNew_json(),NewsInfo.DataDTO.class));
        }
        //设置数据
        mNewsListAdapter.setListData(mDataDTOList);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_list);
        //初始化控件
        recyclerView=findViewById(R.id.recyclerView);
        //初始化适配器
        mNewsListAdapter=new NewsListAdapter(this);
        //设置适配器
        recyclerView.setAdapter(mNewsListAdapter);
//        //获取数据
//        List<CollectionInfo> collectionInfoList= CollectionDbHelper.getInstance(CollectionListActivity.this).queryCollectionListData(null);
//        Gson gson=new Gson();
//        for(int i=0;i< collectionInfoList.size();i++){
//            mDataDTOList.add(gson.fromJson(collectionInfoList.get(i).getNew_json(),NewsInfo.DataDTO.class));
//        }
//        //设置数据
//        mNewsListAdapter.setListData(mDataDTOList);
        //recyclerView点击事件
        mNewsListAdapter.setOnItemClickListener(new NewsListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(NewsInfo.DataDTO dataDTO, int position) {
                //跳转到详情页
                Intent intent = new Intent(CollectionListActivity.this, NewsDetailsActivity.class);
                //传递对象的时候，该类一定要实现serializable
                intent.putExtra("dataDTO",dataDTO);
                startActivity(intent);
                //finish();
            }
        });
        //返回
        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}