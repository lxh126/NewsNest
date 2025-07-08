package com.app.luoxueheng.adapter;

//import static android.os.Build.VERSION_CODES.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.luoxueheng.NewsInfo;
import com.app.luoxueheng.R;
import com.app.luoxueheng.db.HistoryDbHelper;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;




public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyHolder> {
    private List<NewsInfo.DataDTO> mDataDTOList = new ArrayList<>();
    private Context mContext;
    public void setListData(List<NewsInfo.DataDTO> listData){
        this.mDataDTOList=listData;
        notifyDataSetChanged();
    }
    public void addListData(List<NewsInfo.DataDTO> listData){
        int startPosition=this.mDataDTOList.size();
        this.mDataDTOList.addAll(listData);
        notifyItemRangeChanged(startPosition, listData.size());
    }
    public NewsListAdapter(Context context){
        this.mContext=context;

    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载布局文件
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {
        //绑定数据
        NewsInfo.DataDTO dataDTO= mDataDTOList.get(position);
        holder.publisher.setText("来源："+dataDTO.getPublisher());
        holder.publishTime.setText(dataDTO.getPublishTime());
        holder.title.setText("标题："+dataDTO.getTitle());
        if(HistoryDbHelper.getInstance(mContext).isHistory(dataDTO.getNewsID())){
            holder.title.setTextColor(Color.parseColor( "#A8A8A8" ));
        }else{
            holder.title.setTextColor(Color.BLACK);
        }
        //加载图片
        String img=dataDTO.getrealimage(0);
        Glide.with(mContext).load(img).error(R.mipmap.image_error).into(holder.image);
        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null!=mOnItemClickListener){
                    mOnItemClickListener.onItemClick(dataDTO,position);
                    holder.title.setTextColor(Color.parseColor( "#A8A8A8" ));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataDTOList.size();
    }


    static class MyHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView publisher;
        TextView publishTime;
        TextView title;
        public MyHolder(@NonNull View itemView) {
               super(itemView);
               image=itemView.findViewById(R.id.image);
               publisher=itemView.findViewById(R.id.publisher);
               publishTime=itemView.findViewById(R.id. publishTime);
               title=itemView.findViewById(R.id.title);
        }
    }
    private onItemClickListener mOnItemClickListener;

    public onItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(onItemClickListener OnItemClickListener) {
        mOnItemClickListener = OnItemClickListener;
    }

    public interface onItemClickListener{
        //ResultDTO
        void onItemClick(NewsInfo.DataDTO dataDTO,int position);
    }

}
