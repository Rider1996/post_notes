package getpostapplication.rider.com.postsnotes.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import getpostapplication.rider.com.postsnotes.MainActivity;
import getpostapplication.rider.com.postsnotes.R;

public class EmptyFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout,container,false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void hideBackButton() {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).hideBackButton();
        }
    }

    public void showBackButton() {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showBackButton();
        }
    }

    public boolean isNetworkAvailable() {
        boolean isAvailable = false;
        if (getActivity() instanceof MainActivity) {
            isAvailable = ((MainActivity) getActivity()).isNetworkAvailable();
        }
        return isAvailable;
    }

    public void changeTitle(String title) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).changeTitle(title);
        }
    }

    public void actionBarColor() {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).actionBarColor();
        }
    }

    public static void showNoInternetConnection(Activity context, String title, String message
            , DialogInterface.OnClickListener dialogInterface) {
        if (context == null) return;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setMessage(message);
        if (title != null) alertDialog.setTitle(title);
        if (dialogInterface != null) alertDialog.setPositiveButton("Dismiss", dialogInterface);
        else alertDialog.setPositiveButton("Dismiss", null);
        alertDialog.create().show();
    }
}
