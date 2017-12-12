package br.com.rest.controlefacil.ui.activity.release;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.domain.dao.CategoryDAO;
import br.com.rest.controlefacil.domain.dao.PeriodDAO;
import br.com.rest.controlefacil.domain.dao.ReleaseDAO;
import br.com.rest.controlefacil.domain.event.CategorySelectedEvent;
import br.com.rest.controlefacil.domain.event.SelectedIconEvent;
import br.com.rest.controlefacil.domain.model.Category;
import br.com.rest.controlefacil.domain.model.CategoryIcons;
import br.com.rest.controlefacil.domain.model.Period;
import br.com.rest.controlefacil.domain.model.Release;
import br.com.rest.controlefacil.ui.activity.BaseActivity;
import br.com.rest.controlefacil.ui.activity.category.CategoryActivity;
import br.com.rest.controlefacil.ui.adapter.CategoryAdapter;
import br.com.rest.controlefacil.ui.adapter.CategoryIconsAdapter;
import br.com.rest.controlefacil.util.DateUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ReleaseActivity extends BaseActivity implements ReleaseContract.View, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.edt_value)
    AppCompatEditText edtValue;
    @BindView(R.id.edt_maturity)
    AppCompatEditText edtMaturity;
    @BindView(R.id.edt_category)
    AppCompatEditText edtCategory;
    @BindView(R.id.edt_description)
    AppCompatEditText edtDescription;
    @BindView(R.id.chk_payment)
    AppCompatCheckBox chkPayment;
    @BindView(R.id.chk_fixed_recipe)
    AppCompatCheckBox chkFixedRecipe;
    @BindView(R.id.chk_repeat)
    AppCompatCheckBox chkRepeat;
    @BindView(R.id.edt_number_of_times)
    AppCompatEditText edtNumberOfTimes;
    @BindView(R.id.edt_period)
    AppCompatEditText edtPeriod;
    @BindView(R.id.iv_background)
    CircleImageView ivBackground;
    @BindView(R.id.iv_icon)
    CircleImageView ivIcon;
    @BindView(R.id.toolbar)
    Toolbar toobar;

    @Inject
    ReleaseContract.Presenter presenter;
    @Inject
    CategoryDAO categoryDAO;
    @Inject
    PeriodDAO periodDAO;
    @Inject
    ReleaseDAO releaseDAO;
    @Inject
    Category currentCategory;
    @Inject
    Period currentPeriod;
    @Inject
    Release release;
    @Inject
    @Named("DialogPeriod")
    AlertDialog.Builder periodBuilder;
    @Inject
    @Named("DailogCategoryList")
    AlertDialog.Builder categoryListBuilder;
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
    CategoryAdapter categoryAdapter;
    @Inject
    CategoryIconsAdapter categoryIconsAdapter;
    @Inject
    EventBus eventBus;
    @Inject
    CategorySelectedEvent categorySelectedEvent;
    @Inject
    Category category;

    private String[] periods;
    private List<Period> periodList;
    private AlertDialog dialogCategory;
    private AlertDialog listIconsDialog;
    private Integer currentIcon;

    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        bindViews();
        component(this).inject(this);
        presenter.set(categoryDAO);
        presenter.set(releaseDAO);
        presenter.set(periodDAO);
        setSupportActionBar(toobar);
        getSupportActionBar().setTitle("Lançamento");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        edtPeriod.setKeyListener(null);
        edtCategory.setKeyListener(null);
        edtMaturity.setKeyListener(null);
        edtPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        edtMaturity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callMaturityDialog();
            }
        });

        edtMaturity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if (focus) callMaturityDialog();
            }
        });

        chkRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRepeat(chkRepeat.isChecked());
            }
        });

        edtPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = periodBuilder.create();
                alertDialog.show();
            }
        });

        edtCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callCategoryListDialog();
            }
        });
        init();
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth) {
        edtMaturity.setText(DateUtils.format(year, monthOfYear, dayOfMonth));
    }

    private void callMaturityDialog() {
        Calendar now = DateUtils.getInstance();
        year = now.get(Calendar.YEAR);
        month = now.get(Calendar.MONTH);
        day = now.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                ReleaseActivity.this,
                year,
                month,
                day
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    private void callCategoryListDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_category_list, null);
        categoryListBuilder.setView(dialogView);
        RecyclerView recyclerView = ButterKnife.findById(dialogView, R.id.rv_categories);
        CircleImageView ivBackground = ButterKnife.findById(dialogView, R.id.iv_background);
        CircleImageView ivIcon = ButterKnife.findById(dialogView, R.id.iv_icon);
        CircleImageView ivSettings = ButterKnife.findById(dialogView, R.id.iv_icon_settings);
        CircleImageView ivBackgroundSettings = ButterKnife.findById(dialogView, R.id.iv_background_settings);

        ivBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDialogCategory();
            }
        });

        ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDialogCategory();
            }
        });

        ivSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callCategoryActivity();
            }
        });

        ivBackgroundSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callCategoryActivity();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        List<Category> categories = presenter.findAllCategories();
        categoryAdapter.setCategories(categories);
        categoryAdapter.setScreen(CategoryAdapter.SCREEN_RELEASE_ACTIVITY);
        recyclerView.setAdapter(categoryAdapter);
        dialogCategory = categoryListBuilder.create();
        dialogCategory.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
            case R.id.menu_done:
                save();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_release, menu);

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        categoryDAO.close();
        periodDAO.close();
        releaseDAO.close();
    }

    private void init() {
        currentCategory = presenter.findFirstCategory();
        currentPeriod = presenter.findFirstPeriod();
        periodList = presenter.findAllPeriods();
        periods = presenter.periods(periodList);
        periodBuilder.setSingleChoiceItems(periods, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                currentPeriod = periodList.get(i);

                edtPeriod.setText(currentPeriod.getDescription());

                dialogInterface.dismiss();
            }
        });
        edtMaturity.setText(DateUtils.format());
        refreshCategory();
        showRepeat(chkRepeat.isChecked());
    }

    @Override
    public boolean validateForm() {
        boolean valid = true;
        if (edtValue.getText().toString().isEmpty())
            valid = false;
        if (edtDescription.getText().toString().isEmpty())
            valid = false;
        return valid;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showRepeat(boolean show) {
        if (show) {
            showRepeatItens(View.VISIBLE);
        } else {
            showRepeatItens(View.INVISIBLE);
        }
    }

    private void showRepeatItens(int visibility) {
        edtNumberOfTimes.setVisibility(visibility);
        edtPeriod.setVisibility(visibility);
    }

    private void save() {
        if (validateForm()) {
            release.setValue(Double.parseDouble(edtValue.getText().toString()));
            release.setMaturity(DateUtils.format(edtMaturity.getText().toString()));
            release.setCategory(currentCategory);
            release.setDescription(edtDescription.getText().toString());
            release.setPayment(chkPayment.isChecked());
            release.setFixedRecipe(chkFixedRecipe.isChecked());
            release.setRepeat(chkRepeat.isChecked());
            release.setNumberOfTimes(Integer.parseInt(edtNumberOfTimes.getText().toString()));
            release.setPeriod(currentPeriod);
            release.setId(0L);
            presenter.save(release);
        }
    }

    @Subscribe
    public void onEvent(CategorySelectedEvent categorySelectedEvent) {
        //currentCategory = categorySelectedEvent.getCategory();
        currentCategory.setIcon(categorySelectedEvent.getCategory().getIcon());
        refreshCategory();
        dialogCategory.dismiss();
    }

    @Subscribe
    public void onEvent(SelectedIconEvent selectedIconEvent) {
        currentIcon = selectedIconEvent.getIcon();
        ivIcon.setBackgroundResource(currentIcon);
        listIconsDialog.dismiss();
    }

    private void refreshCategory() {
        ivBackground.setImageResource(R.color.expensesColorSecondaryDark);
        ivIcon.setBackgroundResource(currentCategory.getIcon());
        edtCategory.setText("      " + currentCategory.getName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        eventBus.register(ReleaseActivity.this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(ReleaseActivity.this);
    }

    private void callCategoryActivity() {
        startActivity(new Intent(this, CategoryActivity.class));
    }

    public void callDialogCategory() {
        AlertDialog.Builder builder;
        int color;
        builder = expensesBuilder;
        color = R.color.expensesColorSecondaryDark;

        View view = getLayoutInflater().inflate(R.layout.dialog_category, null);
        CircleImageView ivBackground = ButterKnife.findById(view, R.id.iv_background);
        ivIcon = ButterKnife.findById(view, R.id.iv_icon);
        final AppCompatEditText edtName = ButterKnife.findById(view, R.id.edt_name);
        ivBackground.setImageResource(color);
        currentIcon = CategoryIcons.getDefaultCategoryIcon();

        String title = "Nova Categoria";
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
                .setTitle(title);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button btnSave = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (edtName.getText().toString().isEmpty()) {
                            showToast("Necessário informar uma descrição para categoria");
                        } else {
                            category.setId(0L);
                            category.setName(edtName.getText().toString());
                            category.setIcon(currentIcon);
                            category.setCategoryType(br.com.rest.controlefacil.domain.enums.Category.getCategoryType(br.com.rest.controlefacil.domain.enums.Category.EXPENSES));
                            presenter.save(category);
                            alertDialog.dismiss();
                            currentCategory = presenter.findLastCategory();
                            refreshCategory();
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
}
