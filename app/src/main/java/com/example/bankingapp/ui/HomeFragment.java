package com.example.bankingapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bankingapp.MainActivity;
import com.example.bankingapp.R;
import com.example.bankingapp.util.Util;

import static com.example.bankingapp.MainActivity.sFragmentManager;

public class HomeFragment extends Fragment {
    private Button mButton,mButton1;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        Log.d("TAG", "onCreateView: ");

        mButton = view.findViewById(R.id.homeButton);
        mButton1 = view.findViewById(R.id.homeButton1);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.homeFragment = new FromFragment();
                sFragmentManager.beginTransaction().replace(R.id.layout_container, Util.homeFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
            }
        });
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.UserFragment = new UserFragment();
                sFragmentManager.beginTransaction().replace(R.id.layout_container, Util.UserFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();

            }
        });
        return view;

    }


}