package br.com.rest.controlefacil.domain.di;

import br.com.rest.controlefacil.domain.daos.UserDAO;
import br.com.rest.controlefacil.domain.models.User;
import dagger.Component;

/**
 * Created by LUCAS RODRIGUES on 27/11/2017.
 */
@Component(
        modules = {
                DAOModule.class,
                ModelModule.class
        }
)
public interface DomainComponent {

    User user();

    UserDAO userDAO();
}
