package com.dineshwayaman.retrofitedu.Services;

import com.dineshwayaman.retrofitedu.Models.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("users")
    Call<List<Users>> getUsers();

}
