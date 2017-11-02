package com.project.playohackernews.di.components;


import com.project.playohackernews.views.activities.HackerNewsActivity;
import com.project.playohackernews.di.modules.ApplicationModule;
import com.project.playohackernews.di.modules.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by anandmishra on 02/11/17.
 */

@Singleton
@Component (modules = { ApplicationModule.class, NetModule.class })
public interface ApplicationComponent {

    void inject(HackerNewsActivity mainActivity);

}
