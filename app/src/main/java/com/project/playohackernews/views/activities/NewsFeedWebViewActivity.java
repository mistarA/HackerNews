package com.project.playohackernews.views.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.project.playohackernews.MainApplication;
import com.project.playohackernews.R;
import com.project.playohackernews.mvp.presenters.FeedDetailPresenter;
import com.project.playohackernews.mvp.views.IFeedDetailView;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anandmishra on 02/11/17.
 */

public class NewsFeedWebViewActivity extends AppCompatActivity implements IFeedDetailView{


    public static final String INTENT_FEED_DETAIL_URL_EXTRA = "INTENT_FEED_DETAIL_URL_EXTRA";

    @Inject
    FeedDetailPresenter feedDetailPresenter;

    private String url;

    @BindView(R.id.feed_detail_webview)
    WebView mWebView;

    @BindView(R.id.detil_page_loader)
    ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_web_view);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initializeDependencies();

        if (getIntent().getExtras() != null) {
            url = getIntent().getStringExtra(INTENT_FEED_DETAIL_URL_EXTRA);
        }
        getSupportActionBar().setTitle(getResources().getString(R.string.news_detail_title));

        feedDetailPresenter.setmFeedDetailView(this);

        //Loading the project detail for the first time
        feedDetailPresenter.initUi(url);
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
     * This method will initialize the dependencies needed
     */
    private void initializeDependencies() {
        ((MainApplication) getApplication()).getComponent().inject(this);
    }


    @Override
    public void initUi(String webViewUrl) {

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new CustomWebViewClient());
        mWebView.loadUrl(webViewUrl);
    }

    private class CustomWebViewClient extends WebViewClient{

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mProgressBar.setVisibility(View.GONE);
        }
    }


    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}
