package com.app.luoxueheng;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.app.luoxueheng.db.PreferenceDbHelper;
import com.google.android.material.button.MaterialButton;

public class SettingActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    private MaterialButton button1, button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button12,buttonsure;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        button5=findViewById(R.id.button5);
        button6=findViewById(R.id.button6);
        button7=findViewById(R.id.button7);
        button8=findViewById(R.id.button8);
        button9=findViewById(R.id.button9);
        button10=findViewById(R.id.button10);
        button11=findViewById(R.id.button11);
        button12=findViewById(R.id.button12);
        //可能爆空指针
        buttonsure=findViewById(R.id.buttonsure);
        //button1 科技
        if(!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("科技")){
            button1.setBackgroundColor(Color.RED);
        }else{
            button1.setBackgroundColor(Color.WHITE);
        }
        button1.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 当按钮被按下时
                    if (!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("科技")) {
                        button1.setBackgroundColor(Color.WHITE);
                        PreferenceDbHelper.getInstance(SettingActivity.this).addPreference("科技");
                    } else {
                        button1.setBackgroundColor(Color.RED);
                        PreferenceDbHelper.getInstance(SettingActivity.this).deletepreference("科技");
                    }
                }
                return false;
            }
        });
        //button2
        if(!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("社会")){
            button2.setBackgroundColor(Color.RED);
        }else{
            button2.setBackgroundColor(Color.WHITE);
        }
        button2.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 当按钮被按下时
                    if (!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("社会")) {
                        button2.setBackgroundColor(Color.WHITE);
                        PreferenceDbHelper.getInstance(SettingActivity.this).addPreference("社会");
                    } else {
                        button2.setBackgroundColor(Color.RED);
                        PreferenceDbHelper.getInstance(SettingActivity.this).deletepreference("社会");
                    }
                }
                return false;
            }
        });
        //button3
        if(!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("体育")){
            button3.setBackgroundColor(Color.RED);
        }else{
            button3.setBackgroundColor(Color.WHITE);
        }
        button3.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 当按钮被按下时
                    if (!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("体育")) {
                        button3.setBackgroundColor(Color.WHITE);
                        PreferenceDbHelper.getInstance(SettingActivity.this).addPreference("体育");
                    } else {
                        button3.setBackgroundColor(Color.RED);
                        PreferenceDbHelper.getInstance(SettingActivity.this).deletepreference("体育");
                    }
                }
                return false;
            }
        });
        //button4
        if(!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("娱乐")){
            button4.setBackgroundColor(Color.RED);
        }else{
            button4.setBackgroundColor(Color.WHITE);
        }
        button4.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 当按钮被按下时
                    if (!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("娱乐")) {
                        button4.setBackgroundColor(Color.WHITE);
                        PreferenceDbHelper.getInstance(SettingActivity.this).addPreference("娱乐");
                    } else {
                        button4.setBackgroundColor(Color.RED);
                        PreferenceDbHelper.getInstance(SettingActivity.this).deletepreference("娱乐");
                    }
                }
                return false;
            }
        });
        //button5
        if(!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("汽车")){
            button5.setBackgroundColor(Color.RED);
        }else{
            button5.setBackgroundColor(Color.WHITE);
        }
        button5.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 当按钮被按下时
                    if (!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("汽车")) {
                        button5.setBackgroundColor(Color.WHITE);
                        PreferenceDbHelper.getInstance(SettingActivity.this).addPreference("汽车");
                    } else {
                        button5.setBackgroundColor(Color.RED);
                        PreferenceDbHelper.getInstance(SettingActivity.this).deletepreference("汽车");
                    }
                }
                return false;
            }
        });
        //button6
        if(!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("教育")){
            button6.setBackgroundColor(Color.RED);
        }else{
            button6.setBackgroundColor(Color.WHITE);
        }
        button6.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 当按钮被按下时
                    if (!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("教育")) {
                        button6.setBackgroundColor(Color.WHITE);
                        PreferenceDbHelper.getInstance(SettingActivity.this).addPreference("教育");
                    } else {
                        button6.setBackgroundColor(Color.RED);
                        PreferenceDbHelper.getInstance(SettingActivity.this).deletepreference("教育");
                    }
                }
                return false;
            }
        });
        //button7
        if(!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("文化")){
            button7.setBackgroundColor(Color.RED);
        }else{
            button7.setBackgroundColor(Color.WHITE);
        }
        button7.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 当按钮被按下时
                    if (!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("文化")) {
                        button7.setBackgroundColor(Color.WHITE);
                        PreferenceDbHelper.getInstance(SettingActivity.this).addPreference("文化");
                    } else {
                        button7.setBackgroundColor(Color.RED);
                        PreferenceDbHelper.getInstance(SettingActivity.this).deletepreference("文化");
                    }
                }
                return false;
            }
        });
        //8
        if(!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("健康")){
            button8.setBackgroundColor(Color.RED);
        }else{
            button8.setBackgroundColor(Color.WHITE);
        }
        button8.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 当按钮被按下时
                    if (!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("健康")) {
                        button8.setBackgroundColor(Color.WHITE);
                        PreferenceDbHelper.getInstance(SettingActivity.this).addPreference("健康");
                    } else {
                        button8.setBackgroundColor(Color.RED);
                        PreferenceDbHelper.getInstance(SettingActivity.this).deletepreference("健康");
                    }
                }
                return false;
            }
        });
        //9
        if(!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("教育")){
            button9.setBackgroundColor(Color.RED);
        }else{
            button9.setBackgroundColor(Color.WHITE);
        }
        button9.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 当按钮被按下时
                    if (!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("军事")) {
                        button9.setBackgroundColor(Color.WHITE);
                        PreferenceDbHelper.getInstance(SettingActivity.this).addPreference("军事");
                    } else {
                        button9.setBackgroundColor(Color.RED);
                        PreferenceDbHelper.getInstance(SettingActivity.this).deletepreference("军事");
                    }
                }
                return false;
            }
        });
        //10
        if(!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("财经")){
            button10.setBackgroundColor(Color.RED);
        }else{
            button10.setBackgroundColor(Color.WHITE);
        }
        button10.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 当按钮被按下时
                    if (!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("财经")) {
                        button10.setBackgroundColor(Color.WHITE);
                        PreferenceDbHelper.getInstance(SettingActivity.this).addPreference("财经");
                    } else {
                        button10.setBackgroundColor(Color.RED);
                        PreferenceDbHelper.getInstance(SettingActivity.this).deletepreference("财经");
                    }
                }
                return false;
            }
        });
        //11
        if(!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("其它")){
            button11.setBackgroundColor(Color.RED);
        }else{
            button11.setBackgroundColor(Color.WHITE);
        }
        button11.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 当按钮被按下时
                    if (!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("其它")) {
                        button11.setBackgroundColor(Color.WHITE);
                        PreferenceDbHelper.getInstance(SettingActivity.this).addPreference("其它");
                    } else {
                        button11.setBackgroundColor(Color.RED);
                        PreferenceDbHelper.getInstance(SettingActivity.this).deletepreference("其它");
                    }
                }
                return false;
            }
        });
        //12
        if(!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("全部")){
            button12.setBackgroundColor(Color.RED);
        }else{
            button12.setBackgroundColor(Color.WHITE);
        }
        button12.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 当按钮被按下时
                    if (!PreferenceDbHelper.getInstance(SettingActivity.this).isPreference("全部")) {
                        button12.setBackgroundColor(Color.WHITE);
                        PreferenceDbHelper.getInstance(SettingActivity.this).addPreference("全部");
                    } else {
                        button12.setBackgroundColor(Color.RED);
                        PreferenceDbHelper.getInstance(SettingActivity.this).deletepreference("全部");
                    }
                }
                return false;
            }
        });
        findViewById(R.id.buttonsure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsure.setBackgroundColor(Color.RED);
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}