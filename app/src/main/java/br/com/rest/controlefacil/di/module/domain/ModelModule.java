package br.com.rest.controlefacil.di.module.domain;

import br.com.rest.controlefacil.domain.model.User;
import dagger.Module;
import dagger.Provides;

/**
 * Created by LUCAS RODRIGUES on 25/11/2017.
 */

@Module
public class ModelModule {

    @Provides
    User providesUser(){
        return new User();
    }

}
