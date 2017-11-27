package br.com.rest.controlefacil.domain.daos;

import br.com.rest.controlefacil.domain.models.User;
import io.realm.Realm;

/**
 * Created by LUCAS RODRIGUES on 25/11/2017.
 */

public class UserDAO {

    private Realm realm;

    public UserDAO() {
        realm = Realm.getDefaultInstance();
    }

    public void save(User user) {
        realm.beginTransaction();
        if (verifyIfExistsUserById(user.getId()))
            user.setId(autoIncrementId());
        else {
            realm.copyToRealm(user);
        }
        realm.commitTransaction();
    }

    public void delete(User user) {
        realm.beginTransaction();
        user.deleteFromRealm();
        realm.commitTransaction();
    }

    public static Long autoIncrementId() {
        Long key = 1L;
        Realm realm = Realm.getDefaultInstance();
        try {
            key = realm.where(User.class).max("id").longValue() + 1;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return key;
    }

    public boolean login(User user) {
        return (realm.where(User.class)
                .equalTo("email", user.getEmail())
                .equalTo("password", user.getPassword())
                .findFirst() != null);
    }

    private boolean verifyIfExistsUserById(Long id) {
        return (realm.where(User.class).equalTo("id", id).findFirst() != null);
    }

    public boolean verifyIfExistsUserByEmail(String email) {
        return (realm.where(User.class).equalTo("email", email).findFirst() != null);
    }

    public void close() {
        realm.close();
    }
}
