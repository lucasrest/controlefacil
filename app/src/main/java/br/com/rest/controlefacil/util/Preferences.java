package br.com.rest.controlefacil.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;

import java.util.HashMap;

import javax.inject.Inject;

import br.com.rest.controlefacil.ControleFacilApplication;

import static br.com.rest.controlefacil.ControleFacilApplication.getAppComponent;


/**
 * Created by LUCAS RODRIGUES on 26/11/2017.
 */

public class Preferences {

    public static final String FILE_NAME = "ControleFacil";
    public static final String KEY_EMAIL = "email";
    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    SharedPreferences.Editor editor;
    @Inject
    HashMap<String, String> data;

    public Preferences() {
        getAppComponent()
                .inject(this);
    }

    public void save(String email) {
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    public HashMap<String, String> getData() {
        data.put(KEY_EMAIL, sharedPreferences.getString(KEY_EMAIL, null));
        return data;
    }


}
