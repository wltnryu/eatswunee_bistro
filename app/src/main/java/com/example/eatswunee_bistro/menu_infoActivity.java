package com.example.eatswunee_bistro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class menu_infoActivity extends AppCompatActivity {

    TextView order_num, bistro_num, menu_name, date, menu_num;
    String order, bistro,menu, date_, menu_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 메뉴 따라 텍스트 반영
        order_num = findViewById(R.id.order_num);
        bistro_num = findViewById(R.id.bistro_num);
        menu_name = findViewById(R.id.menu_name);
        date = findViewById(R.id.date);
        menu_num = findViewById(R.id.menu_num);

        /** Fragment에서 전달받은 메뉴 정보 반영
         * - 결제 시 전송해야 할 데이터
         * bistro_name (식당 이름) / menu_name (메뉴 이름) / star_rate (평균 별점) / price (가격)
         */
        Intent intent = getIntent();
        order = intent.getStringExtra("order_num");
        bistro = intent.getStringExtra("bistro_num");
        menu = intent.getStringExtra("menu_name");
        date_ = intent.getStringExtra("date");
        menu_ = intent.getStringExtra("menu_num");


        order_num.setText(order);
        bistro_num.setText(bistro);
        menu_name.setText(menu);
        date.setText(date_);
        menu_num.setText("x" + menu_);
    }
}
