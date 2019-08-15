package getpostapplication.rider.com.postsnotes.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import getpostapplication.rider.com.postsnotes.core.Content;
import getpostapplication.rider.com.postsnotes.model.PostResponseModel;
import getpostapplication.rider.com.postsnotes.repository.PostRepository;


public class PostsViewModel extends AndroidViewModel {

    private PostRepository postRepository;
    private LiveData<List<PostResponseModel>> postResponseLiveData;



    public PostsViewModel(@NonNull Application application) {
        super(application);
        postRepository = postRepository.getInstance(application.getApplicationContext());
    }

    public void getPosts() {
        postRepository.getPosts();
    }


    public LiveData<List<PostResponseModel>> getPostsResponseModeLiveData() {

        if (postResponseLiveData == null) {
            postResponseLiveData = new MutableLiveData<>();
            postResponseLiveData = postRepository.getPostsCall();
        }

        return postResponseLiveData;
    }

    public void getCreatedPosts(int userID) {
        postRepository.createPosts(userID);
    }

    public void updatePosts( int userID) {
        postRepository.editPosts(userID);
    }

    public void deletePosts( int userID) {
        postRepository.deletePosts(userID);
    }

    public MutableLiveData<Content> getNetworkFailure() {
        return postRepository.getNetworkFailure();
    }

    public void clearLiveData() {
        postRepository.clearLiveData();
    }
}
