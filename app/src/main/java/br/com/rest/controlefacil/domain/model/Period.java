package br.com.rest.controlefacil.domain.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by PROGRAMAÇÃO on 06/12/2017.
 */

public class Period extends RealmObject{

    @PrimaryKey
    private Long id;

    private int days;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
