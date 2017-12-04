package br.com.rest.controlefacil.domain.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by LUCAS RODRIGUES on 02/12/2017.
 */

public class Category extends RealmObject {

    @PrimaryKey
    private Long id;
    private String name;
    private Integer icon;
    private int categoryType;

    public Category() {
    }

    public Category(Long id, String name, Integer icon, int categoryType) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.categoryType = categoryType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public int getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(int categoryType) {
        this.categoryType = categoryType;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon=" + icon +
                ", categoryType=" + categoryType +
                '}';
    }
}
