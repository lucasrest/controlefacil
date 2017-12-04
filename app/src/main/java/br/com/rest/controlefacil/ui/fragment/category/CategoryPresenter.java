package br.com.rest.controlefacil.ui.fragment.category;

import android.content.Context;

import java.util.List;

import br.com.rest.controlefacil.domain.dao.CategoryDAO;
import br.com.rest.controlefacil.domain.model.Category;

/**
 * Created by PROGRAMAÇÃO on 04/12/2017.
 */

public class CategoryPresenter implements CategoryContract.Presenter {

    private CategoryContract.View view;
    private Context context;
    private CategoryDAO categoryDAO;

    public CategoryPresenter(CategoryContract.View view){
        this.view = view;
        //this.context = (Context) view.;
    }

    @Override
    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<Category> findAll(br.com.rest.controlefacil.domain.enums.Category category) {
        return categoryDAO.findAll(category);
    }

}
