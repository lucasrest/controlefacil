package br.com.rest.controlefacil.domain.dao;

import java.util.List;

import br.com.rest.controlefacil.domain.model.Release;
import io.realm.Realm;

/**
 * Created by LUCAS RODRIGUES on 07/12/2017.
 */

public class ReleaseDAO extends BaseDAO {

    public static Long autoIncrement(){
        Long id = 1L;
        Realm realm = Realm.getDefaultInstance();
        try {
            id = realm.where(Release.class).max("id").longValue() + 1;
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return id;
    }

    public void save(Release release){
        realm.beginTransaction();
        if (release.getId() == 0){
            release.setId(autoIncrement());
            realm.copyToRealm(release);
        }else {
            realm.copyToRealmOrUpdate(release);
        }
        realm.commitTransaction();
    }

    public List<Release> findAll(){
        return realm.where(Release.class)
                .findAll();
    }
}
