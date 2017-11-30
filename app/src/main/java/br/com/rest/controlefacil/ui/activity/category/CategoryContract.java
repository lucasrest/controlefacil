package br.com.rest.controlefacil.ui.activity.category;

import br.com.rest.controlefacil.domain.event.CategoryChangeEvent;

/**
 * Created by PROGRAMAÇÃO on 30/11/2017.
 */

public interface CategoryContract {

    interface View {
        void onChangeScreenTheme(int colorPrimary, int colorPrimaryDark, int colorAccent);
        void setTitle(String title);
    }

    interface Presenter {
        void onCategoryChangeEvent(CategoryChangeEvent categoryChangeEvent);
    }
}
