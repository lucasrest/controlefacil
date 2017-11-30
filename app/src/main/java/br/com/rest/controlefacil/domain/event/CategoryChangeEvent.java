package br.com.rest.controlefacil.domain.event;

import br.com.rest.controlefacil.domain.enums.Category;

/**
 * Created by PROGRAMAÇÃO on 30/11/2017.
 */

public class CategoryChangeEvent {

    private Category category;

    public CategoryChangeEvent(Category category) {
        this.category = category;
    }

    public Category getCategory(){
        return this.category;
    }
}
