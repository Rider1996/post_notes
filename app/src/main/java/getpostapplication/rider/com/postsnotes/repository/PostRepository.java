package getpostapplication.rider.com.postsnotes.repository;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import java.util.List;
import getpostapplication.rider.com.postsnotes.ApiGateway;
import getpostapplication.rider.com.postsnotes.Constance;
import getpostapplication.rider.com.postsnotes.core.Content;
import getpostapplication.rider.com.postsnotes.model.PostResponseModel;
import getpostapplication.rider.com.postsnotes.service.PostService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {
    @SuppressLint("StaticFieldLeak")
    private static PostRepository instance;
    private PostService postService;
    private MutableLiveData<List<PostResponseModel>> postsResponseModelLiveData;
    private List<PostResponseModel> postResponseModels;
    PostResponseModel postResponseModel;
    private MutableLiveData<Content> networkFailure;


    public static PostRepository getInstance(Context context) {
        if (instance == null) instance = new PostRepository(context);
        return instance;
    }


    public PostRepository(Context context) {

        postService = ApiGateway.getPosts();
        postsResponseModelLiveData = new MutableLiveData<>();
        networkFailure = new MutableLiveData<>();
    }


    //method to call the posts

    public void getPosts() {
        clearLiveData();
        Call<List<PostResponseModel>> postsCall = postService.getPosts();
        postsCall.enqueue(new Callback<List<PostResponseModel>>() {
            @Override
            public void onResponse(Call<List<PostResponseModel>> call, Response<List<PostResponseModel>> response) {
                if (response.code() == 200) {
                    postResponseModels = response.body();
                    postsResponseModelLiveData.postValue(postResponseModels);
                }
            }

            @Override
            public void onFailure(Call<List<PostResponseModel>> call, Throwable t) {
                networkFailure.postValue(new Content(Content.Status.FAILED, Constance.NETWORK_ISSUES));

            }
        });
    }


    public MutableLiveData<List<PostResponseModel>> getPostsCall() {
        return postsResponseModelLiveData;
    }

    //method to create posts
    public void createPosts(int userId) {
        clearLiveData();
        Call<PostResponseModel> apiCall = postService.createPosts(userId);
        apiCall.enqueue(new Callback<PostResponseModel>() {
            @Override
            public void onResponse(Call<PostResponseModel> call, Response<PostResponseModel> response) {
                if (response.code() == 200) {
                    postResponseModel = response.body();
                    postsResponseModelLiveData.postValue(postResponseModels);
                }
            }

            @Override
            public void onFailure(Call<PostResponseModel> call, Throwable t) {
                networkFailure.postValue(new Content(Content.Status.FAILED, Constance.NETWORK_ISSUES));

            }
        });

    }


    public void editPosts(int userID) {
        clearLiveData();
        Call<List<PostResponseModel>> updatePosts = postService.updatePosts(userID);
        updatePosts.enqueue(new Callback<List<PostResponseModel>>() {
            @Override
            public void onResponse(Call<List<PostResponseModel>> call, Response<List<PostResponseModel>> response) {
                if (response.code() == 200) {
                    postResponseModels = response.body();
                    postsResponseModelLiveData.postValue(postResponseModels);
                }

            }

            @Override
            public void onFailure(Call<List<PostResponseModel>> call, Throwable t) {
                networkFailure.postValue(new Content(Content.Status.FAILED, Constance.NETWORK_ISSUES));
            }
        });

    }


    public void deletePosts(int userID) {
        clearLiveData();
        Call<List<PostResponseModel>> updatePosts = postService.deletePosts(userID);
        updatePosts.enqueue(new Callback<List<PostResponseModel>>() {
            @Override
            public void onResponse(Call<List<PostResponseModel>> call, Response<List<PostResponseModel>> response) {
                if (response.code() == 200) {
                    postResponseModels = response.body();
                    postsResponseModelLiveData.postValue(postResponseModels);
                }
            }

            @Override
            public void onFailure(Call<List<PostResponseModel>> call, Throwable t) {
                networkFailure.postValue(new Content(Content.Status.FAILED, Constance.NETWORK_ISSUES));
            }
        });

    }


    public MutableLiveData<Content> getNetworkFailure() {
        return networkFailure;
    }


    public void clearLiveData() {
        postsResponseModelLiveData = new MutableLiveData<>();
        postsResponseModelLiveData.postValue(null);
        networkFailure = new MutableLiveData<>();
        networkFailure.postValue(null);
    }
}
