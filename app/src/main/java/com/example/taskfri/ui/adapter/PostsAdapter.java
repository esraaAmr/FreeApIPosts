package com.example.taskfri.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskfri.R;
import com.example.taskfri.data.model.ResponseItem;
import com.example.taskfri.databinding.ItemPostLayoutBinding;
import com.example.taskfri.ui.PostFragment;

import java.util.List;

import okhttp3.Response;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.postsViewHolder> {
    private List<ResponseItem> posts;
    private postAction postAction;
    ////////////////////////////////////////////////////// mis_understanding
    public void addPosts(List<ResponseItem> posts,postAction action) {
        this.posts = posts;
        this.postAction=action;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public postsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostLayoutBinding binding =
                ItemPostLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new postsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull postsViewHolder holder, int position) {
        ResponseItem items = posts.get(position);
        holder.binding.postIdText.setText(String.valueOf(items.getId()));
        holder.binding.postTitleText.setText(items.getTitle());
    }

    @Override
    public int getItemCount() {
        if (posts!=null)
            return posts.size();
        return 0;
    }

    public class postsViewHolder extends RecyclerView.ViewHolder {
        ItemPostLayoutBinding binding;
        public postsViewHolder(ItemPostLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    postAction.postClick(posts.get(getLayoutPosition()));
                }
            });
        }
    }
    public interface postAction{
        void postClick(ResponseItem post);
    }
}
