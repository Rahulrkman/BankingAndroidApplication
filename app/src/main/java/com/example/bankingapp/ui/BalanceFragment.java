package com.example.bankingapp.ui;

import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bankingapp.MainActivity;
import com.example.bankingapp.R;
import com.example.bankingapp.adapters.BalanceAdapter;
import com.example.bankingapp.adapters.FromAdapter;
import com.example.bankingapp.util.Util;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BalanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BalanceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView mRecyclerView;
    BalanceAdapter mUserAdapter;
    private ArrayList<String> phoneNo, name, balance, email;
    public BalanceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BalanceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BalanceFragment newInstance(String param1, String param2) {
        BalanceFragment fragment = new BalanceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_balance, container, false);
        OnBackPressedCallback callback = new OnBackPressedCallback(true ) {
            @Override
            public void handleOnBackPressed() {
                Util.homeFragment = new HomeFragment();
                MainActivity.sFragmentManager.beginTransaction().replace(R.id.layout_container, Util.homeFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        phoneNo = new ArrayList<>();
        name = new ArrayList<>();
        balance = new ArrayList<>();
        email = new ArrayList<>();
        displayData();

        mRecyclerView = view.findViewById(R.id.recyclerFrom);
        mUserAdapter = new BalanceAdapter(getContext(), phoneNo, name, balance, email);
        mRecyclerView.setAdapter(mUserAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;


    }
    private void displayData() {
        Cursor cursor = Util.db.allData();
        while (cursor.moveToNext()){
            phoneNo.add(cursor.getString(1));
            name.add(cursor.getString(2));
            balance.add(String.valueOf(cursor.getInt(3)));
            email.add(cursor.getString(4));
        }
    }
}