package juscezari.com.skeleton.presentation.contract;

/**
 * Created by julio on 21/11/16.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);

    void showLoading();

    void hideLoading();

}
