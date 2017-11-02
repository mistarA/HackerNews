package com.project.playohackernews.mvp.views;

import com.project.playohackernews.models.Hit;
import com.project.playohackernews.models.NewsFeed;

import java.util.List;

/**
 * Created by anandmishra on 15/11/16.
 */

public interface IHackerNewsActivityView {


    void showLoading();

    void hideLoading();

    void setNewsFeedHitsData(List<Hit> newsFeedHitsData);
}
