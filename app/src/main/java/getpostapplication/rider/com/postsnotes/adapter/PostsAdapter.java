package getpostapplication.rider.com.postsnotes.adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import getpostapplication.rider.com.postsnotes.R;
import getpostapplication.rider.com.postsnotes.model.PostResponseModel;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsNewsHolder> {

    private List<PostResponseModel> postResponseModelsList;
    private Context context;
    private PostsAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(PostsAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public PostsAdapter(Context context, List<PostResponseModel> postResponseModelsList) {
        this.postResponseModelsList = postResponseModelsList;
        this.context = context;
    }

    public class PostsNewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textViewTitle, textViewDescription;
        public ImageView imageEditIcon,imageDeleteIcon;

        public PostsNewsHolder(View view) {
            super(view);
            textViewTitle = view.findViewById(R.id.titles);
            textViewDescription = view.findViewById(R.id.description);
            imageDeleteIcon = view.findViewById(R.id.delete_icon);
            imageEditIcon = view.findViewById(R.id.edit_icon);

            imageDeleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delete(getAdapterPosition());
                }
            });

            imageEditIcon.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.editor_posts);
                    dialog.setTitle("Title");
                    EditText title = (EditText) dialog.findViewById(R.id.titles);
                    EditText text = (EditText) dialog.findViewById(R.id.description);
                    Button button = (Button) dialog.findViewById(R.id.delete_icon);
                    Window window = dialog.getWindow();
                    WindowManager.LayoutParams wlp = window.getAttributes();
                    wlp.gravity = Gravity.CENTER;
                    wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
                    window.setAttributes(wlp);
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    dialog.show();
                }
            });

        }

        @Override
        public void onClick(View v) {

        }
    }
    @Override
    public PostsAdapter.PostsNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_layout, parent, false);
        return new PostsAdapter.PostsNewsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsNewsHolder holder, int position) {

        PostResponseModel postResponseModel = postResponseModelsList.get(position);

        if (postResponseModel != null) {
            holder.textViewTitle.setText(postResponseModel.title);
            holder.textViewDescription.setText(postResponseModel.body);
        }
    }

    @Override
    public int getItemCount() {
        return postResponseModelsList.size();
    }

    public void delete(int position) { //removes the row
        postResponseModelsList.remove(position);
        notifyItemRemoved(position);
    }

    public void edit(int position) { //removes the row
        postResponseModelsList.remove(position);
        notifyItemRemoved(position);
    }


}
