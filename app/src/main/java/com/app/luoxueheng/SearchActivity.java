package com.app.luoxueheng;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {
    private androidx.appcompat.widget.Toolbar search_toolbar;
    private TextInputEditText search_keyword,search_category;
    private MaterialButton start_date_button;
    private MaterialButton end_date_button;
//    private AutoCompleteTextView search_cat;
    private MaterialButton search_button;

    private static final List<String> allCategories = Arrays.asList(new String[]{"全部", "娱乐", "军事", "教育", "文化", "健康", "财经", "体育", "汽车", "科技", "社会"});

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        // 初始化控件
        search_toolbar = findViewById(R.id.search_toolbar);
        setSupportActionBar(search_toolbar);
        search_keyword = findViewById(R.id.search_keyword);
        start_date_button = findViewById(R.id.start_date_button);
        end_date_button = findViewById(R.id.end_date_button);
        search_category = findViewById(R.id.search_category);
        search_button = findViewById(R.id.search_button);
        // 设置 Toolbar
        search_toolbar.setNavigationOnClickListener(v -> finish());

        // 初始化日期按钮文本
        updateDateButtonText();

        // 关键词
        search_keyword.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                search_keyword.setHint("");
            } else {
                search_keyword.setHint("请输入关键词");
            }
        });
        search_category.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                search_keyword.setHint("");
            } else {
                search_keyword.setHint("请输入类别");
            }
        });

        // 设置日期按钮
        start_date_button.setOnClickListener(v -> showDatePickerDialog(true));
        end_date_button.setOnClickListener(v -> showDatePickerDialog(false));
        // 设置搜索类别
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, allCategories);

        search_button.setOnClickListener(v -> {
            String keyword = search_keyword.getText().toString().trim();
            String startDate = start_date_button.getText().toString().replace(getString(R.string.start_date) + ": ", "").trim();
            String endDate = end_date_button.getText().toString().replace(getString(R.string.end_date) + ": ", "").trim();
            String category = search_category.getText().toString().trim();


            Toast.makeText(this, "搜索关键词: " + keyword + ", 开始日期: " + startDate + ", 结束日期: " + endDate + ", 类别: " + category, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
            intent.putExtra("KEYWORD", keyword);
            intent.putExtra("START_DATE", startDate);
            intent.putExtra("END_DATE", endDate);
            intent.putExtra("CATEGORY", category);
            startActivity(intent);
        });

    }

    @SuppressLint("SetTextI18n")
    private void updateDateButtonText() {
        start_date_button.setText(getString(R.string.start_date) + ": 请选择日期");
        end_date_button.setText(getString(R.string.end_date) + ": 请选择日期");
    }

    private void showDatePickerDialog(final boolean isStart) {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SetTextI18n") DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(year, month, dayOfMonth);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    String date = sdf.format(selectedDate.getTime());

                    if (isStart) {
                        start_date_button.setText(getString(R.string.start_date) + ": " + date);
                    } else {
                        end_date_button.setText(getString(R.string.end_date) + ": " + date);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        // 设置按钮文本
        datePickerDialog.setButton(DatePickerDialog.BUTTON_POSITIVE, "确定", datePickerDialog);
        datePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "取消", datePickerDialog);

        datePickerDialog.show();
    }
}