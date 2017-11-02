package com.project.playohackernews.mvp.presenters;

import com.project.playohackernews.mvp.views.IHackerNewsActivityView;
import com.project.playohackernews.network.HackerNewsApiInterface;

import javax.inject.Inject;

/**
 * Created by anandmishra on 02/11/17.
 */

public class HackerNewsPresenter extends Presenter {

    HackerNewsApiInterface hackerNewsApiInterface;
    IHackerNewsActivityView iHackerNewsActivityView;


    @Inject
    HackerNewsPresenter(HackerNewsApiInterface hackerNewsApiInterface) {
        this.hackerNewsApiInterface = hackerNewsApiInterface;
    }

    public void setHackerNewsActivityView(IHackerNewsActivityView hackerNewsActivityView) {
        this.iHackerNewsActivityView = iHackerNewsActivityView;
    }

}
