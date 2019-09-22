package com.example.gitrepos.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gitrepos.R;
import com.example.gitrepos.helpers.RecyclerViewAdapter;
import com.example.network.viewmodels.RepoViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoListAdapter extends RecyclerViewAdapter<RepoListAdapter.RepoListViewHolder> {
    private final List<RepoViewModel> repoList;
    private final RepoItemClickListener repoItemClickListener;

    public RepoListAdapter(List<RepoViewModel> repoList, RepoItemClickListener repoItemClickListener) {
        this.repoList = repoList;
        this.repoItemClickListener = repoItemClickListener;

    }

    public interface RepoItemClickListener {
        void onRepoItemClicked(RepoViewModel clickedRepo);
    }

    @Override
    public RepoListAdapter.RepoListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_repo, viewGroup, false);

        return new RepoListAdapter.RepoListViewHolder(itemView,repoItemClickListener);
    }

    @Override
    public void onBindViewHolder(RepoListAdapter.RepoListViewHolder repoListViewHolder, int i) {
        String repoName = repoList.get(i).getRepoName();
        boolean isFavorite = repoList.get(i).isFavorite();
        repoListViewHolder.repoNameTextView.setText(repoName);
        if(isFavorite){
            repoListViewHolder.repoFavoriteImageView.setImageResource(R.mipmap.favourite_set);
        }else {
            repoListViewHolder.repoFavoriteImageView.setImageBitmap(null);
        }
    }


    @Override
    public int getItemCount() {
        return repoList != null ? repoList.size() : 0;
    }


    class RepoListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final RepoItemClickListener repoItemClickListener;

        @BindView(R.id.item_repo_repo_name_text_view)
        TextView repoNameTextView;

        @BindView(R.id.item_repo_set_favourite_image)
        ImageView repoFavoriteImageView;


        public RepoListViewHolder(View itemView,@NonNull RepoItemClickListener repoItemClickListener) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.repoItemClickListener = repoItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                repoItemClickListener.onRepoItemClicked(repoList.get(position));
            }
        }
    }
}

