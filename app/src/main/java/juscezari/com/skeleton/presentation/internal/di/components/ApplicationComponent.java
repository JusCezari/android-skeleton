package juscezari.com.skeleton.presentation.internal.di.components;

import javax.inject.Singleton;

import dagger.Component;
import juscezari.com.skeleton.presentation.view.activity.MainActivity;
import juscezari.com.skeleton.presentation.internal.di.modules.ApplicationModule;

/**
 * Created by julio on 21/11/16.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);

}
