package juscezari.com.skeleton.presentation;

import android.app.Application;

import com.facebook.stetho.Stetho;

import juscezari.com.skeleton.BuildConfig;
import juscezari.com.skeleton.presentation.internal.di.components.ApplicationComponent;
import juscezari.com.skeleton.presentation.internal.di.components.DaggerApplicationComponent;
import juscezari.com.skeleton.presentation.internal.di.modules.ApplicationModule;

/**
 * Created by android on 13/12/16.
 */

public class App extends Application{

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

        // Dagger init
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(getApplicationContext()))
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
