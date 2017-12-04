package br.com.rest.controlefacil.di.module.ui;

import android.os.Bundle;

import javax.inject.Named;

import br.com.rest.controlefacil.domain.enums.Category;
import br.com.rest.controlefacil.ui.fragment.BaseFragment;
import br.com.rest.controlefacil.ui.fragment.category.CategoryContract;
import br.com.rest.controlefacil.ui.fragment.category.CategoryFragment;
import br.com.rest.controlefacil.ui.fragment.category.CategoryPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by PROGRAMAÇÃO on 30/11/2017.
 */

@Module
public class FragmentModule {

    private CategoryContract.View categoryView;

    public FragmentModule() {
    }

    public FragmentModule(BaseFragment fragment) {
        if (fragment instanceof CategoryContract.View)
            this.categoryView = (CategoryContract.View) fragment;
    }

    @Provides
    CategoryFragment providesCategoryFragment() {
        return new CategoryFragment();
    }

    @Provides
    Bundle providesBundle() {
        return new Bundle();
    }

    @Provides
    @Named("ExpensesFragment")
    CategoryFragment providesExpensesFragment(CategoryFragment categoryFragment, Bundle bundle) {
        return CategoryFragment.newInstance(Category.EXPENSES, categoryFragment, bundle);
    }

    @Provides
    @Named("RecipesFragment")
    CategoryFragment providesRecipesFragment(CategoryFragment categoryFragment, Bundle bundle) {
        return CategoryFragment.newInstance(Category.RECIPES, categoryFragment, bundle);
    }

    @Provides
    CategoryContract.Presenter providesCategoryPresenter(){
        return new CategoryPresenter(categoryView);
    }

}
