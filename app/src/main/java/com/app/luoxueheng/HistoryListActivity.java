package com.app.luoxueheng;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.luoxueheng.adapter.NewsListAdapter;
import com.app.luoxueheng.db.HistoryDbHelper;
import com.app.luoxueheng.entity.HistoryInfo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class HistoryListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NewsListAdapter mNewsListAdapter;
    private List<NewsInfo.DataDTO> mDataDTOList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        //初始化控件
        recyclerView=findViewById(R.id.recyclerView);
        //初始化适配器
        mNewsListAdapter=new NewsListAdapter(this);
        //设置适配器
        recyclerView.setAdapter(mNewsListAdapter);
        //获取数据
        List<HistoryInfo> historyInfoList=HistoryDbHelper.getInstance(HistoryListActivity.this).queryHistoryListData(null);
        Gson gson=new Gson();
        for(int i=0;i< historyInfoList.size();i++){
            if(historyInfoList.get(i).getNew_json()!=null)mDataDTOList.add(gson.fromJson(historyInfoList.get(i).getNew_json(),NewsInfo.DataDTO.class));
        }
        //设置数据
        mNewsListAdapter.setListData(mDataDTOList);
        //recyclerView点击事件
        mNewsListAdapter.setOnItemClickListener(new NewsListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(NewsInfo.DataDTO dataDTO, int position) {
                //跳转到详情页
                Intent intent = new Intent(HistoryListActivity.this, NewsDetailsActivity.class);
                //传递对象的时候，该类一定要实现serializable
                intent.putExtra("dataDTO",dataDTO);
                startActivity(intent);
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