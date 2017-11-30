package br.com.rest.controlefacil.ui.activity.category;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.ui.activity.BaseActivity;
import br.com.rest.controlefacil.ui.fragment.category.TabFragment;
import butterknife.BindView;

public class CategoryActivity extends BaseActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        bindViews();
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.expensesColorPrimary));
        this.getWindow().setStatusBarColor(getResources().getColor(R.color.expensesColorPrimaryDark));
        fabAdd.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.expensesColorAccent)));
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_container, new TabFragment()).commit();

    }
}
