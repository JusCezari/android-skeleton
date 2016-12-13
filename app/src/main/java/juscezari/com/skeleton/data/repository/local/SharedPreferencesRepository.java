package juscezari.com.skeleton.data.repository.local;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import juscezari.com.skeleton.data.source.local.SharedPreferencesDataSource;

/**
 * Created by julio on 21/11/16.
 */

public class SharedPreferencesRepository implements SharedPreferencesDataSource {

    Context mContext;

    public SharedPreferencesRepository() {
    }

    @Inject
    public SharedPreferencesRepository(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public String getDeviceId() {
        SharedPreferences sharedPreferences =
                mContext.getSharedPreferences("device_information", Context.MODE_PRIVATE);

        return sharedPreferences.getString("androidId", null);
    }

    @Override
    public void setDeviceId(String androidId) {
        SharedPreferences sharedPreferences =
                mContext.getSharedPreferences("device_information", Context.MODE_PRIVATE);

        sharedPreferences.edit()
                .putString("androidId", androidId)
                .apply();
    }
}
