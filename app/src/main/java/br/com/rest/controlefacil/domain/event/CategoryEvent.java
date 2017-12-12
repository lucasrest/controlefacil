package br.com.rest.controlefacil.domain.event;

import br.com.rest.controlefacil.domain.enums.Category;

/**
 * Created by LUCAS RODRIGUES on 11/12/2017.
 */

public class CategoryEvent {

    private Category category;

    public CategoryEvent(Category category) {
        this.category = category;
    }

    public Category getCategory(){
        return this.category;
    }
}
