package br.com.rest.controlefacil.domain.dao;

import javax.inject.Inject;

import br.com.rest.controlefacil.ControleFacilApplication;
import br.com.rest.controlefacil.domain.model.Category;
import io.realm.Realm;

import static br.com.rest.controlefacil.ControleFacilApplication.getAppComponent;

/**
 * Created by LUCAS RODRIGUES on 07/12/2017.
 */

public class BaseDAO{

    @Inject
    Realm realm;

    public BaseDAO(){
        getAppComponent().inject(this);
    }

    public void close(){
        realm.close();
    }
}
