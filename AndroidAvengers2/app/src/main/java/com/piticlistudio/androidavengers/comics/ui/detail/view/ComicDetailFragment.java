package com.piticlistudio.androidavengers.comics.ui.detail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.piticlistudio.androidavengers.R;
import com.piticlistudio.androidavengers.base.fragment.BaseFragment;
import com.piticlistudio.androidavengers.comics.model.entity.Comic;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Fragment that displays the detail of a comic.
 * Created by jorge.garcia on 13/07/2016.
 */
public class ComicDetailFragment extends BaseFragment {

    private final static String ARG_COMIC_DATA = "data";
    @Bind(R.id.backgroundimage)
    ImageView backgroundimage;
    @Bind(R.id.cover)
    ImageView cover;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.description)
    TextView description;
    @Bind(R.id.pagesTv)
    TextView pagesTv;
    @Bind(R.id.issueTv)
    TextView issueTv;
    @Bind(R.id.linkTv)
    TextView linkTv;

    private Comic data;

    public static ComicDetailFragment newInstance(Comic data) {
        ComicDetailFragment fragment = new ComicDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_COMIC_DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.comic_detail_fragment, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getParent().setSupportActionBar(toolbar);
        if (getParent().getSupportActionBar() != null)
            getParent().getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getArguments() != null) {
            data = getArguments().getParcelable(ARG_COMIC_DATA);
            if (data != null)
                showData(data);
            else {
                // TODO request presenter by comic Id
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void showData(Comic data) {
        getApplicationComponent().picasso()
                .load(data.getThumbnailUri())
                .fit()
                .centerInside()
                .into(cover);

        if (data.getImagesUri().size() > 0) {
            // Load random image
            Random rand = new Random();
            int randomNum = rand.nextInt((data.getImagesUri().size()));
            getApplicationComponent().picasso()
                    .load(data.getImagesUri().get(randomNum))
                    .into(backgroundimage);
        }

        this.title.setText(data.getTitle());
        this.description.setText(data.getDescription());
        if (data.getIssueNumber() > 0) {
            this.issueTv.setText(String.valueOf(data.getIssueNumber()));
        }
        else {
            this.issueTv.setText(R.string.comic_detail_issue_number_unknown);
        }

        if (data.getPageCount() > 0) {
            this.pagesTv.setText(String.valueOf(data.getPageCount()));
        }
        else {
            this.pagesTv.setText(R.string.comic_detail_pages_unknown);
        }


    }


}
