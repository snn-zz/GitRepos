package com.example.network.connection;

import com.example.network.viewmodels.RepoViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ConnectionService {

    @GET("users/{user}/repos")
    Call<List<RepoViewModel>> getListRepos(@Path("user") String user);
}
