package com.project.playohackernews.network;

import com.project.playohackernews.models.NewsFeed;
import com.project.playohackernews.utils.Constants;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by anandmishra on 02/11/17.
 */

public interface HackerNewsApiInterface {

    @GET
    Single<NewsFeed> getNewsFeedResult(@Url String url, @Query(Constants.QUERY_KEYWORD) String queryType);
}
