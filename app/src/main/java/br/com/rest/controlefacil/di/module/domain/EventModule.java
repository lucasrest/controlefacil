package br.com.rest.controlefacil.di.module.domain;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Named;

import br.com.rest.controlefacil.domain.enums.Category;
import br.com.rest.controlefacil.domain.event.CategoryChangeEvent;
import dagger.Module;
import dagger.Provides;

/**
 * Created by PROGRAMAÇÃO on 30/11/2017.
 */

@Module
public class EventModule {

    @Provides
    EventBus providesEventBus(){
        return EventBus.getDefault();
    }

    @Provides
    @Named("ExpensesCategoryChangeEvent")
    CategoryChangeEvent providesExpensesCategoryChangeEvent() {
        return new CategoryChangeEvent(Category.EXPENSES);
    }

    @Provides
    @Named("RecipesCategoryChangeEvent")
    CategoryChangeEvent providesRecipesCategoryChangeEvent() {
        return new CategoryChangeEvent(Category.RECIPES);
    }

}
