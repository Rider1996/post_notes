package getpostapplication.rider.com.postsnotes.service;

import java.util.List;

import getpostapplication.rider.com.postsnotes.model.PostResponseModel;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface PostService {
    //list of post
    @GET("https://jsonplaceholder.typicode.com/posts")
    Call<List<PostResponseModel>> getPosts();

    @GET("https://jsonplaceholder.typicode.com/posts/")
    Call<PostResponseModel> createPosts(@Query("id") Integer id);

    @PUT("https://jsonplaceholder.typicode.com/posts/")
    Call<List<PostResponseModel>> updatePosts(@Query("id") Integer id);

    @DELETE("https://jsonplaceholder.typicode.com/posts/")
    Call<List<PostResponseModel>> deletePosts(@Query("id") Integer id);
}
