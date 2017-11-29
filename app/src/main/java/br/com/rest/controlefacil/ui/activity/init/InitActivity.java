package br.com.rest.controlefacil.ui.activity.init;

import android.os.Bundle;

import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.ui.activity.BaseActivity;

public class InitActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        bindViews();
    }
}
