package br.com.rest.controlefacil.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import br.com.rest.controlefacil.util.Preferences;
import br.com.rest.controlefacil.di.scope.AppScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by LUCAS RODRIGUES on 24/11/2017.
 */

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @AppScope
    Application providesApplication() {
        return application;
    }

    @Provides
    Context providesContext(){
        return application.getApplicationContext();
    }

    @Provides
    @AppScope
    SharedPreferences providesSharedPreferences(Application application){
        return application.getSharedPreferences(Preferences.FILE_NAME, application.MODE_PRIVATE);
    }

    @Provides
    @AppScope
    SharedPreferences.Editor providesSharePreferencesEditor(SharedPreferences sharedPreferences){
        return sharedPreferences.edit();
    }
}
