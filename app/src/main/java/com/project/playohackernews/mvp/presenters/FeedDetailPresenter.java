package com.project.playohackernews.mvp.presenters;

import com.project.playohackernews.mvp.views.IFeedDetailView;

import javax.inject.Inject;

/**
 * Created by anandmishra on 02/11/17.
 */

public class FeedDetailPresenter extends Presenter {

    private IFeedDetailView mFeedDetailView;


    @Inject
    FeedDetailPresenter(){

    }
    public void setmFeedDetailView(IFeedDetailView mFeedDetailView) {
        this.mFeedDetailView = mFeedDetailView;
    }

    public void initUi(String url) {
        mFeedDetailView.initUi(url);
    }
}
