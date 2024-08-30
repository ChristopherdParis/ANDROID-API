package com.example.crud.network;

import com.example.crud.model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiUsers {
    @GET("users?page=2")
    Call<UserResponse> getUsuarios();
}
