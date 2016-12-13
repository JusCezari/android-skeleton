package juscezari.com.skeleton.presentation.internal.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import juscezari.com.skeleton.data.repository.local.SharedPreferencesRepository;
import juscezari.com.skeleton.data.source.local.SharedPreferencesDataSource;
import juscezari.com.skeleton.presentation.contract.MainContract;
import juscezari.com.skeleton.presentation.presenter.MainPresenter;

/**
 * Created by julio on 21/11/16.
 */

@Module
public class ApplicationModule {

    Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }

    // Presenters

    @Provides
    public MainContract.Presenter provideLoginPresenter(SharedPreferencesDataSource sharedPreferencesDataSource) {
        return new MainPresenter(sharedPreferencesDataSource);
    }

    // Data source

    @Singleton
    @Provides
    public SharedPreferencesDataSource providesSharedPreferencesDataSource(Context context) {
        return new SharedPreferencesRepository(context);
    }
}
