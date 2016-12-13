package juscezari.com.skeleton.presentation.presenter;

import javax.inject.Inject;

import juscezari.com.skeleton.data.source.local.SharedPreferencesDataSource;
import juscezari.com.skeleton.presentation.contract.MainContract;

/**
 * Created by julio on 21/11/16.
 */

public class MainPresenter implements MainContract.Presenter{

    MainContract.View mView;

    @Inject
    SharedPreferencesDataSource mSharedPreferencesDataSource;

    @Inject
    public MainPresenter(SharedPreferencesDataSource sharedPreferencesDataSource) {
        this.mSharedPreferencesDataSource = sharedPreferencesDataSource;
    }

    @Override
    public void bindView(MainContract.View view) {
        mView = view;
    }

}
