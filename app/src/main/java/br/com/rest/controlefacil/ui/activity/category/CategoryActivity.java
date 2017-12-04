package br.com.rest.controlefacil.ui.activity.category;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.domain.dao.CategoryDAO;
import br.com.rest.controlefacil.domain.enums.Category;
import br.com.rest.controlefacil.domain.event.CategoryChangeEvent;
import br.com.rest.controlefacil.domain.event.SelectedIconEvent;
import br.com.rest.controlefacil.domain.model.CategoryIcons;
import br.com.rest.controlefacil.ui.activity.BaseActivity;
import br.com.rest.controlefacil.ui.adapter.CategoryIconsAdapter;
import br.com.rest.controlefacil.ui.fragment.category.TabFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryActivity extends BaseActivity implements CategoryContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;
    @Inject
    CategoryDAO categoryDAO;
    @Inject
    EventBus eventBus;
    @Inject
    CategoryContract.Presenter presenter;
    @Inject
    @Named("DialogExpensesCategoryActivity")
    AlertDialog.Builder expensesBuilder;
    @Inject
    @Named("DialogRecipesCategoryActivity")
    AlertDialog.Builder recipesBuilder;
    @Inject
    @Named("DialogListIconsCategory")
    AlertDialog.Builder listIconsBuilder;
    @Inject
    CategoryIconsAdapter categoryIconsAdapter;
    @Inject
    br.com.rest.controlefacil.domain.model.Category category;

    private Category currentCategory;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private CircleImageView ivIcon;
    private AlertDialog listIconsDialog;
    private Integer currentIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        bindViews();
        component(this).inject(this);
        presenter.setCategoryDAO(categoryDAO);
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
        currentCategory = categoryChangeEvent.getCategory();
        presenter.onCategoryChangeEvent(categoryChangeEvent);
    }

    @Subscribe
    public void onEvent(SelectedIconEvent selectedIconEvent) {
        currentIcon = selectedIconEvent.getIcon();
        ivIcon.setBackgroundResource(currentIcon);
        listIconsDialog.dismiss();
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
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.fab_add)
    public void newCategory() {
        callDialogCategory();
    }

    private void callDialogCategory() {
        AlertDialog.Builder builder;
        int color;
        if (currentCategory == Category.EXPENSES) {
            builder = expensesBuilder;
            color = R.color.expensesColorSecondaryDark;
        } else {
            builder = recipesBuilder;
            color = R.color.recipesColorSecondaryDark   ;
        }

        View view = getLayoutInflater().inflate(R.layout.dialog_category, null);
        CircleImageView ivBackground = ButterKnife.findById(view, R.id.iv_background);
        ivIcon = ButterKnife.findById(view, R.id.iv_icon);
        final AppCompatEditText edtName = ButterKnife.findById(view, R.id.edt_name);
        ivBackground.setImageResource(color);
        currentIcon = CategoryIcons.getDefaultCategoryIcon();
        ivIcon.setBackgroundResource(currentIcon);

        ivBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDialogListIcons();
            }
        });
        ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDialogListIcons();
            }
        });

        builder.setView(view)
        .setPositiveButton("Salvar", null)
                .setNegativeButton("Cancelar", null)
                .setTitle("Nova Categoria");
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button b = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (edtName.getText().toString().isEmpty()) {
                            showToast("Necessário informar uma descrição para categoria");
                        } else {
                            category.setName(edtName.getText().toString());
                            category.setIcon(currentIcon);
                            category.setCategoryType(Category.getCategoryType(currentCategory));
                            presenter.save(category);
                            alertDialog.dismiss();
                        }
                    }
                });
            }
        });

        alertDialog.show();
    }

    private void callDialogListIcons() {
        View view = getLayoutInflater().inflate(R.layout.dialog_list_icons_category, null);
        listIconsBuilder.setView(view);
        RecyclerView recyclerView = view.findViewById(R.id.rv_icons);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(categoryIconsAdapter);
        listIconsDialog = listIconsBuilder.create();
        listIconsDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        categoryDAO.close();
    }
}
