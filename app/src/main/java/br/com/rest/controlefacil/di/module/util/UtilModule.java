package br.com.rest.controlefacil.di.module.util;

import java.util.HashMap;

import br.com.rest.controlefacil.di.scope.AppScope;
import br.com.rest.controlefacil.util.Preferences;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by LUCAS RODRIGUES on 26/11/2017.
 */

@Module
public class UtilModule {

    @Provides
    HashMap<String, String> providesHashMap() {
        return new HashMap<>();
    }

    @Provides
    Preferences providesPreferences() {
        return new Preferences();
    }

    @Provides
    Realm providesRealm(){
        return Realm.getDefaultInstance();
    }
}
