package com.example.network.requests.repolistrequest;

public class RepoListRequestModel {

    private String userName;

    public RepoListRequestModel(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
