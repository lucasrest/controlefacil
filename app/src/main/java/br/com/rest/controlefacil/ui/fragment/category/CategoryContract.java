package br.com.rest.controlefacil.ui.fragment.category;

import java.util.List;

import br.com.rest.controlefacil.domain.dao.CategoryDAO;
import br.com.rest.controlefacil.domain.model.Category;

/**
 * Created by PROGRAMAÇÃO on 04/12/2017.
 */

public interface CategoryContract  {

    interface View {

    }

    interface Presenter {
        void setCategoryDAO(CategoryDAO categoryDAO);
        List<Category> findAll(br.com.rest.controlefacil.domain.enums.Category category);
    }
}
