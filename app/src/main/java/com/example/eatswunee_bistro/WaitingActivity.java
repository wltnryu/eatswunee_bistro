package com.example.eatswunee_bistro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatswunee_bistro.api.Data;
import com.example.eatswunee_bistro.api.Result;
import com.example.eatswunee_bistro.api.RetrofitClient;
import com.example.eatswunee_bistro.api.ServiceApi;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WaitingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;

    private RetrofitClient retrofitClient;
    private ServiceApi serviceApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.settingSideNavBar();

        //더미 데이터 생성
//        ArrayList<String> list = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            list.add("Test Data" + i);
//        }

        //리사이클러뷰에 linearlayoutmanager 객체 지정
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //리사이클러뷰 api 통신
        retrofitClient = RetrofitClient.getInstance();
        serviceApi = RetrofitClient.getRetrofitInterface();

        serviceApi.getBistro("1").enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                Data data = result.getData();
                Log.d("retrofit", "Data success");
                customAdapter = new CustomAdapter(data.getOrdersList());
                customAdapter.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int pos) {
                        Intent intent = new Intent (WaitingActivity.this, BistroOrderActivity.class);
                        intent.putExtra("SelectedItem", pos);
                        launcher.launch(intent);
                    }
                });
                recyclerView.setAdapter(customAdapter);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });


        //리사이클러뷰 아이템 간격 조정
        RecyclerItemDecoActivity decoraion_height = new RecyclerItemDecoActivity(20);
        recyclerView.addItemDecoration(decoraion_height);

        //알림 툴바 설정
        //Toolbar toolbar = (Toolbar) findViewById(R.id.bistro_toolbar);
        //setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //커스텀 어댑터를 선언해준뒤 인자 값을 이용해서 setOnItemClickListener쓰기
        //CustomAdapter adapter1 = new CustomAdapter(list);
        //커스텀 리스너 객체 생성 및 전달
        //클릭 이벤트

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.toolbar_item, menu);

        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId ()) {
            case R.id.menu_home:
                startActivity (new Intent(this, WaitingActivity.class));
                return true;
            case R.id.menu_list:
                return true;
            case R.id.menu_money:
                return true;
            case R.id.action_category:
                startActivity (new Intent(this, BistroAlarmActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected (item);
        }
    }
    /***
     *  -> 사이드 네브바 세팅
     *   - 클릭 아이콘 설정
     *   - 아이템 클릭 이벤트 설정
     */
    public void settingSideNavBar()
    {
        // 툴바 생성
        Toolbar toolbar = findViewById(R.id.bistro_toolbar);
        setSupportActionBar(toolbar);

        // 사이드 메뉴를 오픈하기위한 아이콘 추가
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_density_medium_24);

        // 사이드 네브바 구현
        DrawerLayout drawLayout = (DrawerLayout) findViewById(R.id.drawer);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                WaitingActivity.this,
                drawLayout,
                toolbar,
                R.string.open,
                R.string.closed
        );

        // 사이드 네브바 클릭 리스너
        drawLayout.addDrawerListener((DrawerLayout.DrawerListener) actionBarDrawerToggle);

        // -> 사이드 네브바 아이템 클릭 이벤트 설정
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                if (id == R.id.menu_home) {
                    //제작 대기 중 화면
                    Intent intent = new Intent(getApplicationContext(), WaitingActivity.class);
                    startActivity(intent);
                } else if (id == R.id.menu_list) {
                    //제작 완료 목록
                    Intent intent = new Intent(getApplicationContext(), BistroDoneActivity.class);
                    startActivity(intent);
                } else if (id == R.id.menu_money) {
                    //매출 화면
                    Intent intent = new Intent(getApplicationContext(), BistroMoneyActivity.class);
                    startActivity(intent);
                }
                else if (id == R.id.action_category) {
                    //알림 화면
                    Intent intent = new Intent(getApplicationContext(), BistroAlarmActivity.class);
                    startActivity(intent);
                }

                DrawerLayout drawer = findViewById(R.id.drawer);
                //drawer.closeDrawer(androidx.core.view.GravityCompat.START);
                return true;
            }
        });

    }

    /***
     *  -> 뒤로가기시, 사이드 네브바 닫는 기능
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(androidx.core.view.GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

