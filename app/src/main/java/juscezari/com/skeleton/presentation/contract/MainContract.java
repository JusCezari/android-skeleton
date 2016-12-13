package juscezari.com.skeleton.presentation.contract;

/**
 * Created by julio on 21/11/16.
 */

public interface MainContract {

    interface Presenter extends BasePresenter<View>{

    }

    interface View extends BaseView<Presenter>{

    }

}
