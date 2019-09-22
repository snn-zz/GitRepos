package com.example.network.requests.repolistrequest;

import com.example.network.viewmodels.RepoViewModel;

import java.util.List;

public interface RepoListListener {

    void onSuccess(List<RepoViewModel> allRepos);
    void onFailure();
}
