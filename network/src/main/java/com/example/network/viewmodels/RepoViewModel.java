package com.example.network.viewmodels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepoViewModel implements Parcelable {

    @SerializedName("name")
    @Expose
    private String repoName;

    @SerializedName("owner")
    @Expose
    private RepoOwner repoOwner;

    @SerializedName("description")
    @Expose
    private String repoDescription;

    @SerializedName("open_issues")
    @Expose
    private String repoOpenIssues;

    @SerializedName("stargazers_count")
    @Expose
    private String repoStarCount;

    private boolean isFavorite = false;

    public RepoViewModel() {
    }


    protected RepoViewModel(Parcel in) {
        repoName = in.readString();
        repoOwner = in.readParcelable(RepoOwner.class.getClassLoader());
        repoDescription = in.readString();
        repoOpenIssues = in.readString();
        repoStarCount = in.readString();
        isFavorite = in.readByte() != 0;
    }

    public static final Creator<RepoViewModel> CREATOR = new Creator<RepoViewModel>() {
        @Override
        public RepoViewModel createFromParcel(Parcel in) {
            return new RepoViewModel(in);
        }

        @Override
        public RepoViewModel[] newArray(int size) {
            return new RepoViewModel[size];
        }
    };

    public String getRepoName() {
        return repoName;
    }

    public RepoOwner getRepoOwner() {
        return repoOwner;
    }

    public String getRepoDescription() {
        return repoDescription;
    }

    public String getRepoOpenIssues() {
        return repoOpenIssues;
    }

    public String getRepoStarCount() {
        return repoStarCount;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(repoName);
        dest.writeParcelable(repoOwner, flags);
        dest.writeString(repoDescription);
        dest.writeString(repoOpenIssues);
        dest.writeString(repoStarCount);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
    }
}
