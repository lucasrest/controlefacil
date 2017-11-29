package br.com.rest.controlefacil.ui.activity.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.ui.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
    }
}
