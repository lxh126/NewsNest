package com.app.luoxueheng;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.app.luoxueheng.adapter.NewsListAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchResultActivity extends AppCompatActivity{
    private String baseUrl = "https://api2.newsminer.net/svc/news/queryNewsList?size=10";
    private View rootView;
    private RecyclerView recyclerView;
    private NewsListAdapter mNewsListAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private static final String ARG_PARAM = "title";
    private int currentPage=1;
    private boolean isLoading=false;
    private String keyword,startDate,endDate,cat;

    private String title;
    private Handler mHandler=new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==100){
                String data=(String) msg.obj;
                NewsInfo newsInfo=new Gson().fromJson(data,NewsInfo.class);
                //省略错误码
                if (newsInfo!=null){
                    //逻辑处理
                    if(null!=mNewsListAdapter){
                        //没写出getResult
                        if(currentPage==1){
                            mNewsListAdapter.setListData(newsInfo.getData());
                        }
                        else{
                            mNewsListAdapter.addListData(newsInfo.getData());
                        }
                    }
                }else{
                    Toast.makeText(SearchResultActivity.this, "获取数据失败，请稍后重试", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        keyword = intent.getStringExtra("KEYWORD");
        cat = intent.getStringExtra("CATEGORY");
        startDate = intent.getStringExtra("START_DATE");
        endDate = intent.getStringExtra("END_DATE");
        //初始化适配器
        setContentView(R.layout.activity_search_result2);
        mNewsListAdapter= new NewsListAdapter(SearchResultActivity.this);
        //初始化控件
        recyclerView=findViewById(R.id.recyclerView);
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        //设置adapter
        recyclerView.setAdapter(mNewsListAdapter);
        //rootView=inflater.inflate(R.layout.activity_search_result2, container, false);
        // recyclerView列表点击事件
        mNewsListAdapter.setOnItemClickListener(new NewsListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(NewsInfo.DataDTO dataDTO, int position) {
                //跳转到详情页
                Intent intent = new Intent(SearchResultActivity.this, NewsDetailsActivity.class);
                //传递对象的时候，该类一定要实现serializable
                intent.putExtra("dataDTO",dataDTO);
                startActivity(intent);

            }
    });
        //返回
        findViewById(R.id.search_result_toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1; // 为什么要重置 currentPage？
                try {
                    getHttpData();
                } catch (UnsupportedEncodingException e) {
                    Log.e("EncodingError", "Unsupported Encoding Exception", e);
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        //获取数据
        try {
            getHttpData();
        } catch (UnsupportedEncodingException e) {
            Log.e("EncodingError", "Unsupported Encoding Exception", e);
        }
        LinearLayoutManager layoutManager=new LinearLayoutManager(SearchResultActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                    loadMoreItems();
                }
            }
        });
    }

    private void getHttpData() throws UnsupportedEncodingException{
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //构造Request对象
        String url=baseUrl+"&startDate="+startDate+"&endDate="+endDate+"&words="+keyword+"&categories="+cat+"&page="+currentPage;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        //通过OkHttpClient和Request对象来构建Call对象
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("----------", "onFailure: "+e.toString());
                isLoading=false;
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String data=response.body().string();

                //不能在耗时操作里面更新ui，那么需要使用handler
                Message message=new Message();
                message.what=100;
                message.obj=data;
                //发送
                mHandler.sendMessage(message);
                isLoading=false;
            }
        });

    }
    private void loadMoreItems() {
        isLoading = true;
        currentPage++;
        try {
            getHttpData();
        } catch (UnsupportedEncodingException e) {
            Log.e("EncodingError", "Unsupported Encoding Exception", e);
        }
    }
}