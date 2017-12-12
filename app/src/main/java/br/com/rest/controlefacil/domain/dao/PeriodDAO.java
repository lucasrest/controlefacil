package br.com.rest.controlefacil.domain.dao;

import java.util.List;

import br.com.rest.controlefacil.domain.model.Period;
import io.realm.Realm;

/**
 * Created by LUCAS RODRIGUES on 09/12/2017.
 */

public class PeriodDAO extends BaseDAO {


    public static Long autoIncrement() {
        Long id = 1L;
        try {
            Realm realm = Realm.getDefaultInstance();
            id = realm.where(Period.class).max("id").longValue() + 1;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<Period> findAll() {
        return realm.where(Period.class).findAll();
    }

    public Period findFirst(){ return realm.where(Period.class).findFirst(); }
}
