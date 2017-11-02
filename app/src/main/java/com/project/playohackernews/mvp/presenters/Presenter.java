package com.project.playohackernews.mvp.presenters;


import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by anandmishra on 02/11/17.
 */

//Presenter class for MVP pattern

public abstract class Presenter {

    CompositeDisposable compositeDisposable;

    public void start() {
        compositeDisposable = new CompositeDisposable();
    }

    public void stop() {
        compositeDisposable.clear();
    }

}
