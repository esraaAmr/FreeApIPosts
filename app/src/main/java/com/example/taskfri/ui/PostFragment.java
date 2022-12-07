package com.example.taskfri.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.taskfri.R;
import com.example.taskfri.data.model.ResponseItem;
import com.example.taskfri.data.source.remote.RetrofitClient;
import com.example.taskfri.databinding.FragmentPostsBinding;
import com.example.taskfri.ui.adapter.PostsAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class PostFragment extends Fragment implements PostsAdapter.postAction{
    private FragmentPostsBinding binding;
    private PostsAdapter postsAdapter;
    public PostFragment() {
        // Required empty public constructor
    }
    private void initUi(){
        postsAdapter = new PostsAdapter();
        binding.postsRecycler.setAdapter(postsAdapter);
        binding.postsRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void getPosts(){
        RetrofitClient.getServices().getResponse().enqueue(new Callback<List<ResponseItem>>() {
            @Override
            public void onResponse(Call<List<ResponseItem>> call, retrofit2.Response<List<ResponseItem>> response) {
                if (response.isSuccessful())
                    postsAdapter.addPosts(response.body(),PostFragment.this);
            }
            @Override
            public void onFailure(Call<List<ResponseItem>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding=FragmentPostsBinding.bind(view);
        initUi();
        getPosts();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void postClick(ResponseItem post) {
        Navigation.findNavController(getView()).navigate(PostFragmentDirections.actionPostsFragmentToPostsDetailsFragment(post.getId()));
    }
}