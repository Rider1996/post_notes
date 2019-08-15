package getpostapplication.rider.com.postsnotes;
import getpostapplication.rider.com.postsnotes.service.PostService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiGateway {
    public static PostService getPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constance.POST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(PostService.class);
    }
}
