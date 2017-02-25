package com.talk.snaily.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talk.snaily.R;

/**
 * Created by Administrator on 15/09/2016.
 */
public class Fragment_2 extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        return  inflater.inflate(R.layout.fragement_two,container,false);

    }
}
