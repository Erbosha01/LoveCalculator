package com.geektech.lovecalculator.hilt;

import android.content.Context;
import android.content.SharedPreferences;

import com.geektech.lovecalculator.network.LoveApi;
import com.geektech.lovecalculator.repository.Repository;
import com.geektech.lovecalculator.ui.adaptor.BoardAdaptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public LoveApi provideLoveApi() {
        return new Retrofit.Builder()
                .baseUrl("https://love-calculator.p.rapidapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(LoveApi.class);
    }

    @Provides
    public Repository provideRepository() {
        return new Repository(provideLoveApi());
    }

    @Provides
    public BoardAdaptor provideBoardAdaptor() {
        return new BoardAdaptor();
    }

    @Provides
    public SharedPreferences providePreferences(@ApplicationContext Context context) {
        return context.getSharedPreferences("forBoard", Context.MODE_PRIVATE);
    }
}
