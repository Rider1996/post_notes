package getpostapplication.rider.com.postsnotes.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import getpostapplication.rider.com.postsnotes.Constance;
import getpostapplication.rider.com.postsnotes.R;
import getpostapplication.rider.com.postsnotes.viewmodel.PostsViewModel;

public class DisplayPostsFragment extends EmptyFragment{
    public static final String TAG = DisplayPostsFragment.class.getName();
    private PostsViewModel postsViewModel;
    private String title, description;
    TextView textViewTitle;
    TextView textViewDescription;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postsViewModel = ViewModelProviders.of(this).get(PostsViewModel.class);
        postsViewModel.clearLiveData();
        changeTitle(Constance.POSTS);
        actionBarColor();
        showBackButton();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (isNetworkAvailable()) {
            postsViewModel.getNetworkFailure().observe(getViewLifecycleOwner(), content -> {
                if (content != null)
                    showNoInternetConnection(getActivity(), Constance.NO_CONNECTION, content.getMessage(), (dialogInterface, i) -> connectionSettingsButton());
            });

        } else {
            showNoInternetConnection(getActivity(), Constance.NO_CONNECTION, getResources().getString(R.string.connect_to_wifi_or_turn_on_mobile_data), (dialogInterface, i) -> connectionSettingsButton());
        }
    }

    private void connectionSettingsButton() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        setupViews(view);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        postsViewModel.clearLiveData();
    }

    public void setupViews(View view) {
        textViewTitle = view.findViewById(R.id.textViewTitle);
        textViewDescription = view.findViewById(R.id.textViewDescription);

    }
}
