package com.my.application;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("demos/marvel")
    Call<List<Superhero>> getSuperheroes();
}
