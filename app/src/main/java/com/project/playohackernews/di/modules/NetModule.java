package com.project.playohackernews.di.modules;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.playohackernews.network.HackerNewsApiInterface;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anandmishra on 02/11/17.
 */

@Module
@Singleton
public class NetModule {

    @Provides
    @Singleton
    Cache provideHttpCache(Context context){
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    @Named("baseUrl")
    String provideBaseUrl(){
        return "https://api.themoviedb.org";
    }


    @Singleton
    @Provides
    Gson provideGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttp(Cache cache){
        OkHttpClient client = new OkHttpClient();
        return  client;
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient, @Named("baseUrl") String url){
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl(url)
                .build();
    }

    @Provides
    @Singleton
    HackerNewsApiInterface provideGitApi(Retrofit retrofit){
        return retrofit.create(HackerNewsApiInterface.class);
    }
}
