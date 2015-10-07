package com.myapp.administrator.myapplication.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapp.administrator.myapplication.R;

/**
 * Created by Administrator on 2015/10/7.
 */
public class BodyCheckFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_body_check_layout, container, false);
        return view;
    }
}
