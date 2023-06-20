package com.example.eatswunee_bistro;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatswunee_bistro.api.Data;
import com.example.eatswunee_bistro.api.Result;
import com.example.eatswunee_bistro.api.RetrofitClient;
import com.example.eatswunee_bistro.api.ServiceApi;
import com.example.eatswunee_bistro.api.Notification;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BistroAlarmActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlarmAdapter alarmAdapter;
    private RetrofitClient retrofitClient;
    private ServiceApi serviceApi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bistro_alarm);

        Button button = findViewById(R.id.returnbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        //더미 데이터 생성
//        ArrayList<String> list = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            list.add("Test Data" + i);
//        }

        //리사이클러뷰에 linearlayoutmanager 객체 지정
        RecyclerView recyclerView = findViewById(R.id.a_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //리사이클러뷰 api 통신
        retrofitClient = RetrofitClient.getInstance();
        serviceApi = RetrofitClient.getRetrofitInterface();
        Call<Result> call = serviceApi.getBistro3("1");
        Data data = new Data();
        alarmAdapter = new AlarmAdapter(data.getNotifications());
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    Log.e(TAG, "isSuccessful() : " + response.body());
                    Result result = response.body();
                    Data data = result.getData();
                    Log.d("retrofit", "Data fetch success");
                    alarmAdapter = new AlarmAdapter(data.getNotifications());
                    alarmAdapter.setOnItemClickListener(new AlarmAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View v, int pos) {
                            Intent intent = new Intent (BistroAlarmActivity.this, BistroOrderActivity.class);
                            intent.putExtra("SelectedItem", pos);
                            launcher.launch(intent);
                        }
                    });
                    recyclerView.setAdapter(alarmAdapter);
                } else {
                    Log.e(TAG, "isSuccessful()이 아님 : " + response.body());
                }

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("retrofit", t.getMessage());
            }
        });


        //리사이클러뷰에 Adapter 객체 지정
        //AlarmAdapter adapter = new AlarmAdapter(list);
        //recyclerView.setAdapter(adapter);

        //리사이클러뷰 아이템 간격 조정
        RecyclerItemDecoActivity decoraion_height = new RecyclerItemDecoActivity(20);
        recyclerView.addItemDecoration(decoraion_height);

        //this.settingSideNavBar();

        //알림 툴바 설정
        //Toolbar toolbar = (Toolbar) findViewById(R.id.bistro_toolbar);
        //setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayUseLogoEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //커스텀 어댑터를 선언해준뒤 인자 값을 이용해서 setOnItemClickListener쓰기
        //AlarmAdapter adapter1 = new AlarmAdapter(list);
        //커스텀 리스너 객체 생성 및 전달
        //클릭 이벤트
//        adapter1.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int pos) {
//                Intent intent = new Intent(BistroAlarmActivity.this, BistroOrderActivity.class);
//                intent.putExtra("SelectedItem", pos);
//                launcher.launch(intent);
//            }
//        });
    }
    //launcher 선언
    //startActivityForResult 대체
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK)
                    {
                        Intent intent = result.getData();
                    }
                }
            });
}
