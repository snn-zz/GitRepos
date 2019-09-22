package com.example.network.requests.repolistrequest;

import com.example.network.connection.ConnectionService;
import com.example.network.connection.RetrofitInstance;
import com.example.network.viewmodels.RepoViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RepoListRequest {
    private RepoListListener listener;
    private RepoListRequestModel requestModel;

    public RepoListRequest() {
    }

    public void callRequest() {
        Retrofit retrofit = RetrofitInstance.getInstance();

        ConnectionService service = retrofit.create(ConnectionService.class);
        Call<List<RepoViewModel>> repos = service.getListRepos(requestModel.getUserName());
        repos.enqueue(new Callback<List<RepoViewModel>>() {
            @Override
            public void onResponse(Call<List<RepoViewModel>> call, Response<List<RepoViewModel>> response) {
                listener.onSuccess(response.body());
            }
            @Override
            public void onFailure(Call<List<RepoViewModel>> call, Throwable t) {
                listener.onFailure();
            }
        });
    }

    public void setListener(RepoListListener listener) {
        this.listener = listener;
    }

    public void setRequestModel(RepoListRequestModel requestModel) {
        this.requestModel = requestModel;
    }
}

