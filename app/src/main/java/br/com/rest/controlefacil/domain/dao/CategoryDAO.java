package br.com.rest.controlefacil.domain.dao;

import java.util.List;

import br.com.rest.controlefacil.domain.model.Category;
import io.realm.Realm;
import io.realm.Sort;

/**
 * Created by LUCAS RODRIGUES on 02/12/2017.
 */

public class CategoryDAO extends BaseDAO {

    public static Long autoIncrement() {
        Long id = 1L;
        Realm realm = Realm.getDefaultInstance();
        try {
            id = realm.where(Category.class).max("id").longValue() + 1;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void save(Category category) {
        realm.beginTransaction();
        if (category.getId() == 0) {
            category.setId(autoIncrement());
            realm.copyToRealm(category);
        } else {
            realm.copyToRealmOrUpdate(category);
        }
        realm.commitTransaction();
    }

    public List<Category> findAll(br.com.rest.controlefacil.domain.enums.Category category) {
        if (category == br.com.rest.controlefacil.domain.enums.Category.EXPENSES) {
            return realm.where(Category.class).equalTo("categoryType", 1)
                    .findAll()
                    .sort("name", Sort.ASCENDING);
        } else {
            return realm.where(Category.class).equalTo("categoryType", 2)
                    .findAll()
                    .sort("name", Sort.ASCENDING);
        }
    }

    public boolean checkIfThereIsMoreThanOneCategory(int categoryType) {
        return realm.where(Category.class)
                .equalTo("categoryType", categoryType)
                .count()
                > 1;
    }

    public boolean delete(Category category) {
        if (!checkIfThereIsMoreThanOneCategory(category.getCategoryType())) {
            return false;
        }
        realm.beginTransaction();
        category.deleteFromRealm();
        realm.commitTransaction();
        return true;
    }

    public Category findFirst() {
        return realm.where(Category.class).findFirst();
    }

    public List<Category> findByNameContains(String name){
        return realm.where(Category.class)
                .contains("name", name)
                .findAll();
    }

}
