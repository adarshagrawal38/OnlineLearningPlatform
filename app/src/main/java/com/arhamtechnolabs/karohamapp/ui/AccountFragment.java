package com.arhamtechnolabs.karohamapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arhamtechnolabs.karohamapp.ForgotPasswordActivity;
import com.arhamtechnolabs.karohamapp.ProfileActivity;
import com.arhamtechnolabs.karohamapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    //Account Page

    View root;

    LinearLayout editProfileView, changePasswordView, logoutView;


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        root = inflater.inflate(R.layout.fragment_account, container, false);

        editProfileView = root.findViewById(R.id.editProfileView);
        changePasswordView = root.findViewById(R.id.changePasswordView);
        logoutView = root.findViewById(R.id.logoutView);

        editProfileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        changePasswordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}
