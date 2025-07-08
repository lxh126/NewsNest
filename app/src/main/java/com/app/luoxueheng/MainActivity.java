package com.app.luoxueheng;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.app.luoxueheng.db.PreferenceDbHelper;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab_layout;
    private ViewPager2 viewPager;
    private NavigationView nav_view;
    String keyword = ""; // 确保这个变量已经被赋值
    String startDate = "2023-01-01"; // 确保这个变量已经被赋值
    String endDate = "2023-01-31";
    String category="";// 确保这个变量已经被赋值
    private Button buttontosearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        List<String> titles=new ArrayList<>();
        if(!PreferenceDbHelper.getInstance(MainActivity.this).isPreference("科技")){
            titles.add(new String("科技"));
        }
        if(!PreferenceDbHelper.getInstance(MainActivity.this).isPreference("社会")){
            titles.add(new String("社会"));
        }
        if(!PreferenceDbHelper.getInstance(MainActivity.this).isPreference("体育")){
            titles.add(new String("体育"));
        }
        if(!PreferenceDbHelper.getInstance(MainActivity.this).isPreference("娱乐")){
            titles.add(new String("娱乐"));
        }
        if(!PreferenceDbHelper.getInstance(MainActivity.this).isPreference("汽车")){
            titles.add(new String("汽车"));
        }
        if(!PreferenceDbHelper.getInstance(MainActivity.this).isPreference("教育")){
            titles.add(new String("教育"));
        }
        if(!PreferenceDbHelper.getInstance(MainActivity.this).isPreference("文化")){
            titles.add(new String("文化"));
        }
        if(!PreferenceDbHelper.getInstance(MainActivity.this).isPreference("健康")){
            titles.add(new String("健康"));
        }
        if(!PreferenceDbHelper.getInstance(MainActivity.this).isPreference("军事")){
            titles.add(new String("军事"));
        }
        if(!PreferenceDbHelper.getInstance(MainActivity.this).isPreference("财经")){
            titles.add(new String("财经"));
        }
        if(!PreferenceDbHelper.getInstance(MainActivity.this).isPreference("其它")){
            titles.add(new String("其它"));
        }
        if(!PreferenceDbHelper.getInstance(MainActivity.this).isPreference("全部")){
            titles.add(new String("全部"));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        tab_layout =findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.viewPager);
        nav_view=findViewById(R.id.nav_view);
        buttontosearch=findViewById(R.id.buttontosearch);
        buttontosearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_history){
                    //跳转历史记录
                    Intent intent=new Intent(MainActivity.this, HistoryListActivity.class);
                    startActivity(intent);
                }
                if(item.getItemId()==R.id.nav_gallery){
                    //跳转收藏记录
                    Intent intent=new Intent(MainActivity.this, CollectionListActivity.class);
                    startActivity(intent);
                }
                if(item.getItemId()==R.id.nav_setting){
                    //跳转列表分类设置
                    Intent intent=new Intent(MainActivity.this, SettingActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });
        //viewPager需要设置一个adapter
        viewPager.setAdapter(new FragmentStateAdapter(this){
            @NonNull
            @Override
            public Fragment createFragment(int position){
                String title=titles.get(position);
                TabNewsFragment tabNewsFragment=TabNewsFragment.newInstance(title);
                return tabNewsFragment;
            }
            @Override
            public int getItemCount(){
                return titles.size();
            }
        });
        //tab_layout点击事件
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                //设置viewPager选中当前页
                viewPager.setCurrentItem(tab.getPosition(),false);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab){

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab){

            }
        });
        //tab_layout和viewPager关联在一起
        TabLayoutMediator tablayoutMediator=new TabLayoutMediator(tab_layout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles.get(position));
            }
        });
        tablayoutMediator.attach();;
    }
}


