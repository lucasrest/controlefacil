package br.com.rest.controlefacil.domain.event;

import br.com.rest.controlefacil.domain.model.Category;

/**
 * Created by LUCAS RODRIGUES on 11/12/2017.
 */

public class CategorySelectedEvent{

    private Category category;

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}
