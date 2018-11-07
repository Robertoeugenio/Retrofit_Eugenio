package com.example.rober.retrofiteugenio;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import keilane.com.retrofit.dominio.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
public class MainActivity extends AppCompatActivity {
    private TextView mUserIdTextView;
    private TextView mIdTextView;
    private TextView mTitleTextView;
    private TextView mBodyTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        ModeloApi apiService = retrofit.create(ModeloApi.class);
        Call<Modelo> call = apiService.getModelo(1);
        call.enqueue(new Callback<Modelo>() {
            @Override
            public void onResponse(Call<Modelo> call, Response<Modelo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    writeViewData(Objects.requireNonNull(response.body()));
                }
            }
            @Override
            public void onFailure(Call<Modelo> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void writeViewData(Modelo modelo) {
        mUserIdTextView.setText(modelo.getUserId().toString());
        mIdTextView.setText(Integer.toString(modelo.getId()));
        mBodyTextView.setText(modelo.getBody());
        mTitleTextView.setText(modelo.getTitle());
    }
    private void initViews() {
        mUserIdTextView = findViewById(R.id.user_id);
        mIdTextView = findViewById(R.id.id);
        mTitleTextView = findViewById(R.id.title);
        mBodyTextView = findViewById(R.id.body);
    }
}