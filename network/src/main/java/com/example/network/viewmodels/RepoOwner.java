package com.example.network.viewmodels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RepoOwner implements Serializable,Parcelable {

    @SerializedName("id")
    @Expose
    private String repoOwnerId;

    @SerializedName("login")
    @Expose
    private String repoOwnerName;

    @SerializedName("avatar_url")
    @Expose
    private String avatarImageUrl;

    protected RepoOwner(Parcel in) {
        repoOwnerId = in.readString();
        repoOwnerName = in.readString();
        avatarImageUrl = in.readString();
    }

    public static final Creator<RepoOwner> CREATOR = new Creator<RepoOwner>() {
        @Override
        public RepoOwner createFromParcel(Parcel in) {
            return new RepoOwner(in);
        }

        @Override
        public RepoOwner[] newArray(int size) {
            return new RepoOwner[size];
        }
    };

    public String getRepoOwnerId() {
        return repoOwnerId;
    }

    public String getRepoOwnerName() {
        return repoOwnerName;
    }

    public String getAvatarImageUrl() {
        return avatarImageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(repoOwnerId);
        dest.writeString(repoOwnerName);
        dest.writeString(avatarImageUrl);
    }
}
