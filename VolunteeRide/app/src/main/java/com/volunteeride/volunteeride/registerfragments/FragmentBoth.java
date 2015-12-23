package com.volunteeride.volunteeride.registerfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.volunteeride.volunteeride.R;

/**
 * Created by mthosani on 12/21/15.
 */
public class FragmentBoth extends Fragment{

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     @return A new instance of fragment Fragment3.
     */
    public static FragmentBoth newInstance() {
        return new FragmentBoth();
    }

    public FragmentBoth() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_both, container, false);
        return rootView;
    }
}
