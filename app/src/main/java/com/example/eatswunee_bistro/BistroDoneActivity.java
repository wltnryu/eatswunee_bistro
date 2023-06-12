package com.example.eatswunee_bistro;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class BistroDoneActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bistro_done);

        //더미데이터 생성
        ArrayList<String> testDataSet = new ArrayList<>();
        for (int i = 0; i<20; i++) {
            testDataSet.add("TestData" + i);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
        recyclerView.setLayoutManager(linearLayoutManager);

        BistroDoneAdapter bistroDoneAdapter = new BistroDoneAdapter(testDataSet);
        recyclerView.setAdapter(bistroDoneAdapter);

        //리사이클러뷰 아이템 간격 조정
        RecyclerItemDecoActivity decoraion_height = new RecyclerItemDecoActivity(20);
        recyclerView.addItemDecoration(decoraion_height);

        this.settingSideNavBar();
    }

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
                startActivity (new Intent (this, WaitingActivity.class));
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
                BistroDoneActivity.this,
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
