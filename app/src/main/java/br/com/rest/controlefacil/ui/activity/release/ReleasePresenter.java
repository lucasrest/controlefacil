package br.com.rest.controlefacil.ui.activity.release;

/**
 * Created by LUCAS RODRIGUES on 04/12/2017.
 */

public class ReleasePresenter implements ReleaseContract.Presenter {

    private ReleaseContract.View view;

    public ReleasePresenter(ReleaseContract.View view) {
        this.view = view;
    }


}
