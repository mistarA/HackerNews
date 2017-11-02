package com.project.playohackernews.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.project.playohackernews.MainApplication;
import com.project.playohackernews.R;
import com.project.playohackernews.adapters.HitRecyclerAdapter;
import com.project.playohackernews.models.Hit;
import com.project.playohackernews.models.NewsFeed;
import com.project.playohackernews.mvp.presenters.HackerNewsPresenter;
import com.project.playohackernews.mvp.views.IHackerNewsActivityView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

public class HackerNewsActivity extends AppCompatActivity implements IHackerNewsActivityView {

    @Inject
    HackerNewsPresenter mHackerNewsPresenter;
    private List<Hit> newsFeedHitsData;

    @BindView(R.id.hit_list_rv)
    RecyclerView mHitDataRecyclerView;

    @BindView(R.id.pbLoader)
    ProgressBar mPbLoader;

    @BindView(R.id.search_feeds_et)
    EditText mSearchEditText;

    private HitRecyclerAdapter mHitRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacker_news);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initDependencies();

        mHackerNewsPresenter.setHackerNewsActivityView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHackerNewsPresenter.start();

        if (newsFeedHitsData == null) {
            mSearchEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mHackerNewsPresenter.querySearch(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    @Override
    public void showLoading() {
        mPbLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mPbLoader.setVisibility(View.GONE);
    }


    @Override
    protected void onStop() {
        super.onStop();
        mHackerNewsPresenter.stop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This method will inject the required field injections that are needed in this activity
     */
    private void initDependencies() {
        ((MainApplication)getApplication()).getComponent().inject(this);
    }

    @Override
    public void setNewsFeedHitsData(List<Hit> newsFeedHitsData) {
        this.newsFeedHitsData = newsFeedHitsData;
        mHitRecyclerAdapter = new HitRecyclerAdapter(this, newsFeedHitsData);
        mHitDataRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mHitDataRecyclerView.setAdapter(mHitRecyclerAdapter);
    }
}
