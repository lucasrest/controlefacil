package br.com.rest.controlefacil.di.module.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.rest.controlefacil.domain.model.Category;
import br.com.rest.controlefacil.domain.model.Period;
import br.com.rest.controlefacil.domain.model.Release;
import br.com.rest.controlefacil.domain.model.User;
import dagger.Module;
import dagger.Provides;

/**
 * Created by LUCAS RODRIGUES on 25/11/2017.
 */

@Module
public class ModelModule {

    @Provides
    Category providesCategory() {
        return new Category();
    }

    @Provides
    List<Category> providesCategories() {
        return new ArrayList<>();
    }

    @Provides
    Period providesPeriod(){
        return new Period();
    }

    @Provides
    Release providesRelease() {
        return new Release();
    }

    @Provides
    User providesUser() {
        return new User();
    }

}
