package com.project.playohackernews.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.playohackernews.MainApplication;
import com.project.playohackernews.R;
import com.project.playohackernews.mvp.presenters.HackerNewsPresenter;
import com.project.playohackernews.mvp.views.IHackerNewsActivityView;

import javax.inject.Inject;

public class HackerNewsActivity extends AppCompatActivity implements IHackerNewsActivityView {

    @Inject
    HackerNewsPresenter mHackerNewsPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacker_news);


        initDependencies();
    }

    /**
     * This method will inject the required field injections that are needed in this activity
     */
    private void initDependencies() {
        ((MainApplication)getApplication()).getComponent().inject(this);
    }
}
