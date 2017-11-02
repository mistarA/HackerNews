package com.project.playohackernews;

import android.app.Application;

import com.project.playohackernews.di.components.ApplicationComponent;
import com.project.playohackernews.di.components.DaggerApplicationComponent;
import com.project.playohackernews.di.modules.ApplicationModule;
import com.project.playohackernews.di.modules.NetModule;

/**
 * Created by anandmishra on 02/11/17.
 */

public class MainApplication extends Application{

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .netModule(new NetModule())
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent(){
        return this.applicationComponent;
    }
}
