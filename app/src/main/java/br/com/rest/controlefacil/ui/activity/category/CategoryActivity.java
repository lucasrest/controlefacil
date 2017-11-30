package br.com.rest.controlefacil.ui.activity.category;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.domain.event.CategoryChangeEvent;
import br.com.rest.controlefacil.ui.activity.BaseActivity;
import br.com.rest.controlefacil.ui.fragment.category.TabFragment;
import butterknife.BindView;

public class CategoryActivity extends BaseActivity implements CategoryContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;
    @Inject
    EventBus eventBus;
    @Inject
    CategoryContract.Presenter presenter;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        bindViews();
        component(this).inject(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_container, new TabFragment()).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        eventBus.register(CategoryActivity.this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(CategoryActivity.this);
    }

    @Subscribe
    public void onEvent(CategoryChangeEvent categoryChangeEvent) {
        presenter.onCategoryChangeEvent(categoryChangeEvent);
    }

    @Override
    public void onChangeScreenTheme(int colorPrimary, int colorPrimaryDark, int colorAccent) {
        toolbar.setBackgroundColor(getResources().getColor(colorPrimary));
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(colorPrimaryDark));
        }
        fabAdd.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(colorAccent)));
    }

    @Override
    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
