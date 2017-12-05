package br.com.rest.controlefacil.di.module.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.ContextThemeWrapper;

import javax.inject.Named;

import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.ui.activity.BaseActivity;
import br.com.rest.controlefacil.ui.activity.category.CategoryContract;
import br.com.rest.controlefacil.ui.activity.category.CategoryPresenter;
import br.com.rest.controlefacil.ui.activity.fotgot_password.ForgotPasswordContract;
import br.com.rest.controlefacil.ui.activity.fotgot_password.ForgotPasswordPresenter;
import br.com.rest.controlefacil.ui.activity.login.LoginContract;
import br.com.rest.controlefacil.ui.activity.login.LoginPresenter;
import br.com.rest.controlefacil.ui.activity.release.ReleaseContract;
import br.com.rest.controlefacil.ui.activity.release.ReleasePresenter;
import br.com.rest.controlefacil.ui.activity.user.UserContract;
import br.com.rest.controlefacil.ui.activity.user.UserPresenter;
import br.com.rest.controlefacil.ui.adapter.CategoryAdapter;
import br.com.rest.controlefacil.ui.adapter.CategoryIconsAdapter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by LUCAS RODRIGUES on 25/11/2017.
 */

@Module
public class UIModule {

    private CategoryContract.View categoryView;
    private ForgotPasswordContract.View forgotPasswordView;
    private LoginContract.View loginView;
    private UserContract.View userView;
    private ReleaseContract.View releaseView;

    public UIModule() {
    }

    public UIModule(BaseActivity baseActivity) {
        if (baseActivity instanceof LoginContract.View)
            this.loginView = (LoginContract.View) baseActivity;
        else if (baseActivity instanceof UserContract.View)
            this.userView = (UserContract.View) baseActivity;
        else if (baseActivity instanceof ForgotPasswordContract.View)
            this.forgotPasswordView = (ForgotPasswordContract.View) baseActivity;
        else if (baseActivity instanceof CategoryContract.View)
            this.categoryView = (CategoryContract.View) baseActivity;
        else if (baseActivity instanceof ReleaseContract.View)
            this.releaseView = (ReleaseContract.View) baseActivity;
    }

    @Provides
    ForgotPasswordContract.Presenter providesForgotPasswordPresenter() {
        return new ForgotPasswordPresenter(forgotPasswordView);
    }

    @Provides
    LoginContract.Presenter providesLoginPresenter() {
        return new LoginPresenter(loginView);
    }

    @Provides
    UserContract.Presenter providesUserPresenter() {
        return new UserPresenter(userView);
    }

    @Provides
    CategoryContract.Presenter providesCategoryPresenter() {
        return new CategoryPresenter(categoryView);
    }

    @Provides
    @Named("DialogExpensesCategoryActivity")
    AlertDialog.Builder providesExpensesAlert() {
        return new AlertDialog.Builder(new ContextThemeWrapper((Context) categoryView, R.style.DialogExpensesCategory));
    }

    @Provides
    @Named("DialogRecipesCategoryActivity")
    AlertDialog.Builder providesRecipesAlert() {
        return new AlertDialog.Builder(new ContextThemeWrapper((Context) categoryView, R.style.DialogRecipesCategory));
    }

    @Provides
    @Named("DialogListIconsCategory")
    AlertDialog.Builder providesDialogListIcons() {
        Activity activity = (Activity) categoryView;
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(activity, R.style.DialogDefault));
        builder.setTitle("Escolha um ícone");
        return builder;
    }

    @Provides
    CategoryIconsAdapter providesCategoryIconsAdapter() {
        return new CategoryIconsAdapter();
    }

    @Provides
    CategoryAdapter providesCategoryAdapater() {
        return new CategoryAdapter();
    }

    @Provides
    ReleaseContract.Presenter providesReleasePresenter() {
        return new ReleasePresenter(releaseView);
    }
}
