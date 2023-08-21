package com.my.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<Superhero> superheroList;
    private RecyclerView recyclerView;
    private SuperheroAdapter superheroAdapter;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // ProgressDialog create karen
        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching names..");
        // ProgressDialog ko show karen
        dialog.show();
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Superhero>> call = apiService.getSuperheroes();

        call.enqueue(new Callback<List<Superhero>>() {
            @Override
            public void onResponse(Call<List<Superhero>> call, Response<List<Superhero>> response) {
                dialog.dismiss();
                if (response.isSuccessful()){
                    superheroList =response.body();
                    superheroAdapter =new SuperheroAdapter(superheroList,MainActivity.this);
                    recyclerView.setAdapter(superheroAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Superhero>> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }


}