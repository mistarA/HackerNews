package com.project.playohackernews.mvp.presenters;

import android.support.annotation.NonNull;

import com.project.playohackernews.models.NewsFeed;
import com.project.playohackernews.mvp.views.IHackerNewsActivityView;
import com.project.playohackernews.network.HackerNewsApiInterface;
import com.project.playohackernews.utils.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by anandmishra on 02/11/17.
 */

public class HackerNewsPresenter extends Presenter {

    HackerNewsApiInterface hackerNewsApiInterface;
    IHackerNewsActivityView mHackerNewsActivityView;


    @Inject
    HackerNewsPresenter(HackerNewsApiInterface hackerNewsApiInterface) {
        this.hackerNewsApiInterface = hackerNewsApiInterface;
    }

    public void setHackerNewsActivityView(IHackerNewsActivityView hackerNewsActivityView) {
        this.mHackerNewsActivityView = hackerNewsActivityView;
    }

    public void getNewsFeedData(String queryType) {

        mHackerNewsActivityView.showLoading();
        compositeDisposable.add(hackerNewsApiInterface.getNewsFeedResult(Constants.BASE_URL, queryType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<NewsFeed>() {
                    @Override
                    public void onSuccess(@NonNull NewsFeed newsFeed) {

                        mHackerNewsActivityView.hideLoading();
                        mHackerNewsActivityView.setNewsFeedHitsData(newsFeed.getHits());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                }));
    }

    public void querySearch(String s) {

        Observable.just(s)
                .debounce(500, TimeUnit.MILLISECONDS)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return !s.isEmpty();
                    }
                })
                .distinctUntilChanged()
                .switchMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return Observable.just(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String value) {
                        getNewsFeedData(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
