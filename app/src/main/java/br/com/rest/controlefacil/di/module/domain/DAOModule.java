package br.com.rest.controlefacil.di.module.domain;

import br.com.rest.controlefacil.domain.dao.CategoryDAO;
import br.com.rest.controlefacil.domain.dao.UserDAO;
import dagger.Module;
import dagger.Provides;

/**
 * Created by LUCAS RODRIGUES on 25/11/2017.
 */

@Module
public class DAOModule {

    @Provides
    UserDAO providesUserDAO() {
        return new UserDAO();
    }

    @Provides
    CategoryDAO providesCategoryDAO(){
        return new CategoryDAO();
    }
}
