package com.app.luoxueheng;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.luoxueheng.db.AbstractDbHelper;
import com.app.luoxueheng.db.CollectionDbHelper;
import com.app.luoxueheng.db.HistoryDbHelper;
import com.bumptech.glide.Glide;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NewsDetailsActivity extends AppCompatActivity {

    private NewsInfo.DataDTO dataDTO;
    private Toolbar toolbar;
    private ImageView imageView;
    private ImageButton myButton;
    private boolean isSelected=false;
    public String content;
    private TextView textViewcontent,textViewtitle,textViewsource,textViewtime,textViewabstract;
    private LinearLayout layoutContainer;
    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news_details);
        //初始化控件
        toolbar = findViewById(R.id.toolbar);
        myButton = findViewById(R.id.myButton);
        final int[] flag = {0};
        textViewcontent=findViewById(R.id.textViewcontent);
        textViewtitle=findViewById(R.id.textViewtitle);
        textViewsource=findViewById(R.id.textViewsource);
        textViewtime=findViewById(R.id.textViewtime);
        textViewabstract=findViewById(R.id.textViewabstract);
        layoutContainer = findViewById(R.id.linearLayout);
        //获取传递的数据
        dataDTO = (NewsInfo.DataDTO) getIntent().getSerializableExtra("dataDTO");
        //设置数据
        if (null != dataDTO) {
            toolbar.setTitle(dataDTO.getTitle());
            textViewtitle.setText(dataDTO.getTitle());
            textViewsource.setText("来源：" + dataDTO.getPublisher());
            textViewtime.setText("时间：" + dataDTO.getPublishTime());
            textViewcontent.setText(dataDTO.getContent());
            // 假设这里有一个图片URL的列表
            ArrayList<String> imageUrls = new ArrayList<>();
            int num = dataDTO.getImagenum();
            for (int i = 0; i < num; i++) {
                imageUrls.add(dataDTO.getrealimage(i));
            }

            // 动态添加ImageView并加载图片
            for (String url : imageUrls) {
                ImageView imageView = new ImageView(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                imageView.setLayoutParams(params);
                imageView.setAdjustViewBounds(true); // 根据图片的实际大小调整ImageView的大小（可选）
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP); // 图片的缩放类型（可选）
                // 使用Glide加载图片
                Glide.with(this).load(url).into(imageView);
                // 将ImageView添加到LinearLayout中
                layoutContainer.addView(imageView);
            }
            //播放视频
            if(dataDTO.getVideo()!=""){
                VideoView videoView1 = new VideoView(this);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);   //全屏
                // 获取当前屏幕的密度
                float density = getResources().getDisplayMetrics().density;

                // 将 dp 转换为 px
                int widthInPx = (int) (450 * density + 0.5f);
                int heightInPx = (int) (300 * density + 0.5f);

                // 创建 LinearLayout.LayoutParams 对象
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        widthInPx, // 使用转换后的像素值
                        heightInPx); // 使用转换后的像素值

                videoView1.setLayoutParams(params);
               // Uri uri = Uri.parse(  dataDTO.getVideo());
                Uri uri=Uri.parse("https://video19.ifeng.com/video09/2024/07/04/p7214559664992686519-102-173234.mp4?reqtype=tsl");
                videoView1.setVideoURI(uri);
                videoView1.requestFocus();
                videoView1.start();
                layoutContainer.addView(videoView1);
            }
            //生成ai摘要
            String text=dataDTO.getContent();
            String newsID=dataDTO.getNewsID();
            if(AbstractDbHelper.getInstance(NewsDetailsActivity.this).isAbstract(dataDTO.getNewsID())==false||AbstractDbHelper.getInstance(NewsDetailsActivity.this).getAbstractByNewsID(dataDTO.getNewsID())==null){
                sendPostRequest(text,dataDTO.getNewsID());
            } else{
                String abstractText =AbstractDbHelper.getInstance(NewsDetailsActivity.this).getAbstractByNewsID(dataDTO.getNewsID());

                textViewabstract.setText("摘要已生成:"+abstractText);
            }


            //添加历史记录
            String dataDTOJson = new Gson().toJson(dataDTO);
            HistoryDbHelper.getInstance(NewsDetailsActivity.this).addHistory(null, dataDTO.getNewsID(), dataDTOJson);
            //添加收藏
            if (!CollectionDbHelper.getInstance(NewsDetailsActivity.this).isCollection(dataDTO.getNewsID())) {
                myButton.setBackgroundResource(R.drawable.button_notselected);
            } else {
                myButton.setBackgroundResource(R.drawable.button_selected);
            }
            myButton.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        // 当按钮被按下时
                        if (!CollectionDbHelper.getInstance(NewsDetailsActivity.this).isCollection(dataDTO.getNewsID())) {
                            myButton.setBackgroundResource(R.drawable.button_selected);
                            isSelected = true;
                            CollectionDbHelper.getInstance(NewsDetailsActivity.this).addCollection(null, dataDTO.getNewsID(), dataDTOJson);
                        } else {
                            myButton.setBackgroundResource(R.drawable.button_notselected);
                            CollectionDbHelper.getInstance(NewsDetailsActivity.this).delete(dataDTO.getNewsID());
                            isSelected = false;
                        }
                    }
                    return false;
                }
            });

        }

        //返回
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    public void loadImage(View view,String url) {
        Glide.with(this).load(url).into(imageView);
    }
    // 在其他方法中调用sendMessageAndGetReply
    private void sendPostRequest(String text,String newsID) {
        text="你好,请根据以下内容为我生成一篇不多于300字的摘要"+text;
        //需要将新闻文本中所有换行符\n去除 否则将无法生成摘要
        text = text.replaceAll("\\r?\\n", "");
        OkHttpClient client = new OkHttpClient();
        client = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String jsonData = String.format("{\"model\": \"glm-4\",\"messages\": [{\"role\": \"user\",\"content\": \"%s\"}]}", text);
        System.out.println(jsonData);
        RequestBody body = RequestBody.create(jsonData, JSON);

        Request request = new Request.Builder()
                .url("https://open.bigmodel.cn/api/paas/v4/chat/completions")
                .header("Authorization", "Bearer d9e8bcee0e8723b89c16c7bd2ef08199.wRiKecdRi6i3ttis") // 替换为你的实际token
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> textViewabstract.setText("请求失败: " + e.getMessage()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    runOnUiThread(() -> textViewabstract.setText("请求未成功: " + response.code()));
                } else {
                    // 假设响应是一个简单的JSON字符串
                    String responseBody = response.body().string();
                    System.out.println(responseBody);
                    // 创建ObjectMapper实例
                    ObjectMapper objectMapper = new ObjectMapper();

                    // 读取JSON字符串为JsonNode对象
                    JsonNode rootNode = objectMapper.readTree(responseBody);

                    // 导航到choices数组的第一个元素
                    JsonNode choicesNode = rootNode.path("choices").get(0);

                    // 从choices的第一个元素中提取message对象
                    JsonNode messageNode = choicesNode.path("message");

                    // 从message对象中提取content字段
                    content = messageNode.path("content").asText();
                    //JSONObject jsonObj=Json.parseObject(responseBody);
                    // 在这里处理响应数据，然后更新UI
                    // 假设这是你的responseBody，一个包含content字段的JSON字符串
                    runOnUiThread(() -> textViewabstract.setText("新闻摘要（由模型glm-4生成）: " + content));
                    AbstractDbHelper.getInstance(NewsDetailsActivity.this).addAbstract(newsID,content);
                }
            }
        });
        }

    }


