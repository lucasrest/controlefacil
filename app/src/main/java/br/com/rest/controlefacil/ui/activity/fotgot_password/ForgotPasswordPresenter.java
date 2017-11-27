package br.com.rest.controlefacil.ui.activity.fotgot_password;

import android.util.Log;

/**
 * Created by LUCAS RODRIGUES on 25/11/2017.
 */

public class ForgotPasswordPresenter implements ForgotPasswordContract.Presenter {

    private ForgotPasswordContract.View view;

    public ForgotPasswordPresenter(ForgotPasswordContract.View view){
        this.view = view;
    }

    @Override
    public void recoverAccount() {
        Log.i("TESTe", "Deu certo");
    }
}
