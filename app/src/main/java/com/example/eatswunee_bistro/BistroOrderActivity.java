package com.example.eatswunee_bistro;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatswunee_bistro.api.Data;
import com.example.eatswunee_bistro.api.Result;
import com.example.eatswunee_bistro.api.RetrofitClient;
import com.example.eatswunee_bistro.api.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BistroOrderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlarmAdapter alarmAdapter;
    private RetrofitClient retrofitClient;
    private ServiceApi serviceApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bistro_order);

        //ImageButton imageButton = findViewById(R.id.imageButton2);
        Button imagebutton = findViewById(R.id.button3);
        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
            }
        });

        //리사이클러뷰 api 통신
        retrofitClient = RetrofitClient.getInstance();
        serviceApi = RetrofitClient.getRetrofitInterface();
        //TextView order_id = findViewById(R.id.order_id);
        TextView order_num = findViewById(R.id.order_num);
        TextView menu_name = findViewById(R.id.menu_name);
        TextView menu_cnt = findViewById(R.id.menu_cnt);

        //orderid 선언
        String temp = getIntent().getStringExtra("order_id");

        serviceApi.getBistro2(Integer.parseInt(temp)).enqueue(new Callback<Result>() {
//        serviceApi.getBistro2(1).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                Data data = result.getData();
                Log.d("retrofit", "Data fetch success");
                order_num.setText(data.getOrder_num());
                String Total = "";
                String Totalnum = "";
                for(int i = 0; i<data.getMenusList().size(); i++){
                    Total += data.getMenusList().get(i).getMenuName() + '\n';
                    Totalnum += data.getMenusList().get(i).getMenuCnt() + '\n';
                }
                menu_name.setText(Total);
                menu_cnt.setText(Totalnum);

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("retrofit", t.getMessage());
            }
        });
    }

}