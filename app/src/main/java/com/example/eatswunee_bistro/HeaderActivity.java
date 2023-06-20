package com.example.eatswunee_bistro;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eatswunee_bistro.api.Data;
import com.example.eatswunee_bistro.api.Result;
import com.example.eatswunee_bistro.api.RetrofitClient;
import com.example.eatswunee_bistro.api.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeaderActivity extends AppCompatActivity {

    private RetrofitClient retrofitClient;
    private ServiceApi serviceApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appbar_header);


    }
}
