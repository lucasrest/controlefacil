package br.com.rest.controlefacil.util.di;

import android.app.Application;
import android.content.SharedPreferences;

import java.util.HashMap;

import javax.inject.Singleton;

import br.com.rest.controlefacil.util.Preferences;
import dagger.Module;
import dagger.Provides;

/**
 * Created by LUCAS RODRIGUES on 26/11/2017.
 */

@Module
public class UtilModule {

    @Provides
    HashMap<String, String> providesHashMap(){
        return new HashMap<>();
    }

    @Provides
    Preferences providesPreferences(){
        return new Preferences();
    }
}
