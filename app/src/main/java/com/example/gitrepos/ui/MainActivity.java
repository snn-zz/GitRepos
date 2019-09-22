package com.example.gitrepos.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.gitrepos.R;
import com.example.gitrepos.adapter.RepoListAdapter;
import com.example.gitrepos.ui.base.BaseActivity;
import com.example.gitrepos.utils.Constants;
import com.example.network.requests.repolistrequest.RepoListListener;
import com.example.network.requests.repolistrequest.RepoListRequest;
import com.example.network.requests.repolistrequest.RepoListRequestModel;
import com.example.network.viewmodels.RepoViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements RepoListListener,RepoListAdapter.RepoItemClickListener {

    private RepoListRequest request;
    private RepoListRequestModel requestModel;

    @BindView(R.id.activity_main_repo_user_name)
    EditText userNameEditText;
    @BindView(R.id.activity_main_repo_list_recycler_view)
    RecyclerView repoListRecyclerView;

    private RepoListAdapter repoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        request  = new RepoListRequest();
        request.setListener(this);
        if(!TextUtils.isEmpty(userNameEditText.getText().toString())) {
            getAllRepos(userNameEditText.getText().toString());
        }
    }

    private void getAllRepos(String userName){
        requestModel = new RepoListRequestModel(userName);
        request.setRequestModel(requestModel);
        request.callRequest();
    }

    @OnClick(R.id.activity_main_get_repo_list_button)
    void getRepoListButtonClick(){
        getAllRepos(userNameEditText.getText().toString());
        hideKeyboard(this);
    }

    @Override
    public void onSuccess(List<RepoViewModel> allRepos) {
        controlRepoIsFavorite(allRepos);
        repoListAdapter = new RepoListAdapter(allRepos,MainActivity.this);
        repoListRecyclerView.setAdapter(repoListAdapter);
        repoListRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    public void onFailure() {
    }

    @Override
    public void onRepoItemClicked(RepoViewModel clickedRepo) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.DETAIL_REPO_BUNDLE_NAME,clickedRepo);
        openActivity(bundle, RepoDetailActivity.class,
                0, R.anim.activity_exit_to_left);
    }

    private void controlRepoIsFavorite(List<RepoViewModel> allRepos){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        for (RepoViewModel repoModel : allRepos) {
            repoModel.setFavorite(prefs.getBoolean(repoModel.getRepoOwner().getRepoOwnerId()+repoModel.getRepoName(), false));
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
