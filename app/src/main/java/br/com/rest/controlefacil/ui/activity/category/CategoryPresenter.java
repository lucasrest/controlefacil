package br.com.rest.controlefacil.ui.activity.category;

import android.app.Activity;
import android.content.Context;

import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.domain.enums.Category;
import br.com.rest.controlefacil.domain.event.CategoryChangeEvent;

/**
 * Created by PROGRAMAÇÃO on 30/11/2017.
 */

public class CategoryPresenter implements CategoryContract.Presenter {

    private CategoryContract.View view;
    private Context context;

    public CategoryPresenter(CategoryContract.View view) {
        this.view = view;
        context = (Activity) view;
    }

    @Override
    public void onCategoryChangeEvent(CategoryChangeEvent categoryChangeEvent) {
        if (categoryChangeEvent.getCategory() == Category.EXPENSES) {
            view.setTitle(context.getString(R.string.title_expenses));
            view.onChangeScreenTheme(R.color.expensesColorPrimary, R.color.expensesColorPrimaryDark, R.color.expensesColorAccent);
        } else {
            view.setTitle(context.getString(R.string.title_recipes));
            view.onChangeScreenTheme(R.color.recipesColorPrimary, R.color.recipesColorPrimaryDark, R.color.recipesColorAccent);
        }
    }
}