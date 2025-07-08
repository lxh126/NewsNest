
package com.app.luoxueheng;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.app.luoxueheng.adapter.NewsListAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class TabNewsFragment extends Fragment {


    private String baseUrl = "https://api2.newsminer.net/svc/news/queryNewsList?size=10&startDate=2015-08-31&endDate=2024-09-02&words&categories=";
    private View rootView;
    private RecyclerView recyclerView;
    private NewsListAdapter mNewsListAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private static final String ARG_PARAM = "title";
    private int currentPage=1;
    private boolean isLoading=false;

    private String title;
    private Handler mHandler=new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==100){
                String data=(String) msg.obj;
                NewsInfo newsInfo=new Gson().fromJson(data,NewsInfo.class);
                if (newsInfo!=null){
                    //逻辑处理
                    if(null!=mNewsListAdapter){
                        if(currentPage==1){
                            mNewsListAdapter.setListData(newsInfo.getData());
                        }
                        else{
                            mNewsListAdapter.addListData(newsInfo.getData());
                        }
                    }
                }else{
                    Toast.makeText(getActivity(), "获取数据失败，请稍后重试", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
    public TabNewsFragment() {

    }

    public static TabNewsFragment newInstance(String param) {
        TabNewsFragment fragment = new TabNewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_tab_news, container, false);
        //初始化控件
        recyclerView=rootView.findViewById(R.id.recyclerView);
        swipeRefreshLayout=rootView.findViewById(R.id.swipeRefreshLayout);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化适配器
        mNewsListAdapter= new NewsListAdapter(getActivity());
        //设置adapter
        recyclerView.setAdapter(mNewsListAdapter);
        // recyclerView列表点击事件
        mNewsListAdapter.setOnItemClickListener(new NewsListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(NewsInfo.DataDTO dataDTO, int position) {
                //跳转到详情页
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                //传递对象的时候，该类一定要实现serializable
                intent.putExtra("dataDTO",dataDTO);
                startActivity(intent);


            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                try {
                    Toast.makeText(getActivity(), "正在刷新", Toast.LENGTH_SHORT).show();
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
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //为RecyclerView设置滚动监视器
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();//获取了当前屏幕上可见的RecyclerView项的数量
                int totalItemCount = layoutManager.getItemCount();//获取了RecyclerView中所有项的总数。
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();//这行代码获取了当前屏幕上第一个可见项在适配器中的位置。

                if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                    Toast.makeText(getActivity(), "正在努力加载更多新闻", Toast.LENGTH_SHORT).show();
                    loadMoreItems();
                }////判断来检查是否滚动到了底部三个条件同时满足：1.确保当前没有正在加载数据的操作，避免重复加载
            }
        });//2.第一个可见项的位置加上屏幕上可见项的数量大于等于总项数时，可以认为已经滚动到底部3.确保第一个可见项的位置是有效的，即不是负值。
    }

    private void getHttpData() throws UnsupportedEncodingException{
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        String encodedtitle=Objects.equals(title, "全部") ? "" : URLEncoder.encode(title, StandardCharsets.UTF_8.toString());
        String url=baseUrl+encodedtitle+"&page="+currentPage;
        //构造Request对象
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