package getpostapplication.rider.com.postsnotes.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import getpostapplication.rider.com.postsnotes.Constance;
import getpostapplication.rider.com.postsnotes.R;
import getpostapplication.rider.com.postsnotes.adapter.PostsAdapter;
import getpostapplication.rider.com.postsnotes.model.PostResponseModel;
import getpostapplication.rider.com.postsnotes.viewmodel.PostsViewModel;

public class PostsFragment extends EmptyFragment {

    public static final String TAG = PostsFragment.class.getName();
    private PostsViewModel postsViewModel;
    TextView textViewTitle;
    TextView textDescription;
    RecyclerView recyclerViewSportsItem;
    ProgressBar progressBar;
    ConstraintLayout constraintLayoutTitle;
    private List<PostResponseModel> postResponseModelsList;
    private PostsAdapter postsAdapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postsViewModel = ViewModelProviders.of(this).get(PostsViewModel.class);
        changeTitle(Constance.POSTS);
        actionBarColor();
        hideBackButton();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        postResponseModelsList = new ArrayList<>();

        if (isNetworkAvailable()) {
            postsViewModel.getPosts();

            progressBar.setVisibility(View.VISIBLE);

            postsViewModel.getPostsResponseModeLiveData().observe(getViewLifecycleOwner(), new Observer<List<PostResponseModel>>() {
                @Override
                public void onChanged(@Nullable final List<PostResponseModel>  model) {
                    if (model != null) {

                        progressBar.setVisibility(View.GONE);

                        if (PostsFragment.this.getActivity() == null) return;
                        recyclerViewSportsItem.setBackgroundColor(ContextCompat.getColor(PostsFragment.this.getActivity(), R.color.colorInactiveText));
                        //constraintLayoutTitle.setBackgroundColor(ContextCompat.getColor(PostsFragment.this.getActivity(), R.color.colorTertiaryTint60));

                        textDescription.setText(model.get(4).body);
                        textViewTitle.setText(model.get(4).title);

                        postResponseModelsList = model;

                        postsAdapter = new PostsAdapter(PostsFragment.this.getContext(), postResponseModelsList);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(PostsFragment.this.getContext(), RecyclerView.VERTICAL, false);
                        recyclerViewSportsItem.setLayoutManager(layoutManager);
                        recyclerViewSportsItem.setAdapter(postsAdapter);
                        postsAdapter.setOnItemClickListener(new PostsAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View itemView, int position) {
                                DisplayPostsFragment displayPostsFragment = new DisplayPostsFragment();
                                FragmentManager fm = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                fragmentTransaction.addToBackStack(PostsFragment.TAG);
                                fragmentTransaction.replace(R.id.posts_container, displayPostsFragment, DisplayPostsFragment.TAG);
                                fragmentTransaction.commit();

                            }
                        });

                    }
                }
            });

            postsViewModel.getNetworkFailure().observe(getViewLifecycleOwner(), content -> {
                if (content != null)
                    showNoInternetConnection(getActivity(), Constance.NO_CONNECTION, content.getMessage(), (dialogInterface, i) -> connectionSettingsButton());
            });
        } else {
            progressBar.setVisibility(View.GONE);
            showNoInternetConnection(getActivity(), Constance.NO_CONNECTION, getResources().getString(R.string.connect_to_wifi_or_turn_on_mobile_data), (dialogInterface, i) -> connectionSettingsButton());
        }
    }

    private void connectionSettingsButton() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        setupViews(view);
        return view;

    }

    @Override
    public void onPause() {
        super.onPause();
        postsViewModel.clearLiveData();
    }


    public void setupViews(View view){
        textViewTitle =view.findViewById(R.id.textViewTitle);
        textDescription =view.findViewById(R.id.textViewDescription);
        recyclerViewSportsItem =view.findViewById(R.id.recyclerViewPostsItem);
        progressBar = view.findViewById(R.id.progressBar);


    }
}
