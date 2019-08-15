package getpostapplication.rider.com.postsnotes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostResponseModel {
    @SerializedName("userId")
    @Expose
    public int userId;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("body")
    @Expose
    public String body;
}
