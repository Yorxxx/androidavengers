package com.piticlistudio.androidavengers.base.fragment;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.piticlistudio.androidavengers.AndroidApplication;
import com.piticlistudio.androidavengers.comics.MarvelComicComponent;
import com.piticlistudio.androidavengers.dependencies.ApplicationComponent;

public abstract class BaseFragment extends Fragment {


    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getActivity().getApplication()).getApplicationComponent();
    }

    protected MarvelComicComponent getComicComponent() {
        return ((AndroidApplication) getActivity().getApplication()).getComicComponent();
    }

    @Nullable
    protected ActionBar getToolbar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    protected AppCompatActivity getParent() {
        return (AppCompatActivity) getActivity();
    }
}
