package com.example.gitrepos.ui;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gitrepos.R;
import com.example.gitrepos.ui.base.BaseActivity;
import com.example.gitrepos.utils.Constants;
import com.example.network.viewmodels.RepoViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoDetailActivity extends BaseActivity {

    @BindView(R.id.activity_repo_detail_owner_image_view)
    ImageView repoOwnerImageView;

    @BindView(R.id.activity_repo_detail_owner_name)
    TextView repoOwnerNameTextView;

    @BindView(R.id.activity_repo_detail_star_data)
    TextView repoStartCountTextView;

    @BindView(R.id.activity_repo_detail_open_issue_data)
    TextView repoOpenIssueTextView;

    @BindView(R.id.activity_repo_detail_description)
    TextView repoDescriptionTextView;


    RepoViewModel detailRepo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_detail);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null) {
            detailRepo = getIntent().getExtras().getParcelable(Constants.DETAIL_REPO_BUNDLE_NAME);
            setRepoDetailData(detailRepo);
        }


        setActivityTitle(detailRepo.getRepoName());
        addLeftItem(R.mipmap.left_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RepoDetailActivity.this.onBackPressed();
            }
        });

        setFavoriteType(detailRepo.isFavorite());

    }

    private void setFavoriteType(boolean isFavorite){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            View toolbarView = actionBar.getCustomView();
            ImageView favoriteImageView = toolbarView.findViewById(R.id.app_bar_favorite_image_view);
            if(isFavorite) {
                favoriteImageView.setImageResource(R.mipmap.favourite_set);
                favoriteImageView.setTag("set");
            }else{
                favoriteImageView.setImageResource(R.mipmap.favourite_unset);
                favoriteImageView.setTag("unset");
            }
            favoriteImageView.setOnClickListener(favoriteItemClickListener);
        }
    }

    View.OnClickListener favoriteItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getTag().equals("unset")) {
                ((ImageView) v).setImageBitmap(null);
                v.setBackground(getDrawable(R.mipmap.favourite_set));
                v.setTag("set");
                addFavoriteList();
            } else{
                ((ImageView) v).setImageBitmap(null);
                v.setBackground(getDrawable(R.mipmap.favourite_unset));
                v.setTag("unset");
                removeFavoriteList();
            }
        }
    };

    private void setRepoDetailData(RepoViewModel detailRepo){
        if (!TextUtils.isEmpty(detailRepo.getRepoOwner().getAvatarImageUrl())) {
            Glide.with(this)
                    .load(detailRepo.getRepoOwner().getAvatarImageUrl())
                    .into(repoOwnerImageView);
        }

        repoOwnerNameTextView.setText(detailRepo.getRepoOwner().getRepoOwnerName());
        repoStartCountTextView.setText(detailRepo.getRepoStarCount());
        repoOpenIssueTextView.setText(detailRepo.getRepoOpenIssues());
        repoDescriptionTextView.setText(detailRepo.getRepoDescription());

    }

    private void addFavoriteList(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(RepoDetailActivity.this);
        prefs.edit().putBoolean(detailRepo.getRepoOwner().getRepoOwnerId()+detailRepo.getRepoName(), true).apply();
    }

    private void removeFavoriteList(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(RepoDetailActivity.this);
        prefs.edit().putBoolean(detailRepo.getRepoOwner().getRepoOwnerId()+detailRepo.getRepoName(), false).apply();
    }

}
