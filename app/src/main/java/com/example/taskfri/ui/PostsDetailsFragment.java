package com.example.taskfri.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.taskfri.R;
import com.example.taskfri.data.model.ResponseItem;
import com.example.taskfri.data.source.remote.RetrofitClient;
import com.example.taskfri.databinding.FragmentPostsDetailsBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostsDetailsFragment extends Fragment {
    private FragmentPostsDetailsBinding binding;
    public PostsDetailsFragment() {
        // Required empty public constructor
    }
    public static PostsDetailsFragment newInstance(String param1, String param2) {
        PostsDetailsFragment fragment = new PostsDetailsFragment();
        return fragment;
    }
    private void bindUi(ResponseItem post){
        binding.postIdText.setText(String.valueOf(post.getId()));
        binding.postTitleText.setText(post.getTitle());
        binding.postUserIdText.setText(String.valueOf(post.getUserId()));
        binding.postDiscText.setText(post.getBody());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int postId = PostsDetailsFragmentArgs.fromBundle(getArguments()).getId();
        getPost(postId);

    }

    private void getPost(int postId) {
        RetrofitClient.getServices().getPost(postId).enqueue(new Callback<ResponseItem>() {
            @Override
            public void onResponse(Call<ResponseItem> call, Response<ResponseItem> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    bindUi(response.body());
                }

            }

            @Override
            public void onFailure(Call<ResponseItem> call, Throwable t) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentPostsDetailsBinding.bind(view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}