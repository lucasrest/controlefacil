package br.com.rest.controlefacil.domain.dao;

import java.util.List;

import javax.inject.Inject;

import br.com.rest.controlefacil.domain.model.Category;
import io.realm.Realm;
import io.realm.Sort;

import static br.com.rest.controlefacil.ControleFacilApplication.getAppComponent;

/**
 * Created by LUCAS RODRIGUES on 02/12/2017.
 */

public class CategoryDAO {

    @Inject
    Realm realm;

    public CategoryDAO() {
        getAppComponent().inject(this);
    }

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

    public void save(Category category){
        realm.beginTransaction();
        category.setId(autoIncrement());
        realm.copyToRealm(category);
        realm.commitTransaction();
    }

    public void close(){
        realm.close();
    }

    public List<Category> findAll(br.com.rest.controlefacil.domain.enums.Category category){
        if (category == br.com.rest.controlefacil.domain.enums.Category.EXPENSES) {
            return realm.where(Category.class).equalTo("categoryType", 1)
                    .findAll()
                    .sort("name", Sort.ASCENDING);
        }else {
            return realm.where(Category.class).equalTo("categoryType", 2)
                    .findAll()
                    .sort("name", Sort.ASCENDING);
        }
    }

}
