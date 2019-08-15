package getpostapplication.rider.com.postsnotes.core;

public class Content {
    private final Status status;
    private final String message;

    public Content(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static final Content LOADED;
    public static final Content LOADING;
    public static final Content FAILURE;

    static {
        LOADED = new Content(Status.SUCCESS, "success");
        LOADING = new Content(Status.RUNNING, "loading");
        FAILURE = new Content(Status.FAILED, "failure");
    }

    public enum Status {
        RUNNING,
        SUCCESS,
        FAILED
    }

}
