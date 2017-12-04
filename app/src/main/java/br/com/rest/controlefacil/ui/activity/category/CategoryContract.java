package br.com.rest.controlefacil.ui.activity.category;

import br.com.rest.controlefacil.domain.dao.CategoryDAO;
import br.com.rest.controlefacil.domain.event.CategoryChangeEvent;
import br.com.rest.controlefacil.domain.model.Category;

/**
 * Created by PROGRAMAÇÃO on 30/11/2017.
 */

public interface CategoryContract {

    interface View {
        void onChangeScreenTheme(int colorPrimary, int colorPrimaryDark, int colorAccent);
        void setTitle(String title);
        void showToast(String message);
    }

    interface Presenter {
        void onCategoryChangeEvent(CategoryChangeEvent categoryChangeEvent);
        void save(Category category);
        void setCategoryDAO(CategoryDAO categoryDAO);
    }
}
