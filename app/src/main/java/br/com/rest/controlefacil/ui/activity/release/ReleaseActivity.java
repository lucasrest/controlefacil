package br.com.rest.controlefacil.ui.activity.release;

import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.ui.activity.BaseActivity;
import br.com.rest.controlefacil.util.DateUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class ReleaseActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener {

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

    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        bindViews();
        init();
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
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth) {
        edtMaturity.setText(DateUtils.format(year, monthOfYear, dayOfMonth));
    }

    private void callMaturityDialog(){
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

    private void init(){
        edtMaturity.setText(DateUtils.format());
    }

}
