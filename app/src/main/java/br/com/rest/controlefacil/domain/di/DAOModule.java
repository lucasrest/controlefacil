package br.com.rest.controlefacil.domain.di;

import br.com.rest.controlefacil.domain.daos.UserDAO;
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
}
