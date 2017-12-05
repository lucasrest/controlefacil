package br.com.rest.controlefacil.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;

import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.ui.activity.BaseActivity;
import br.com.rest.controlefacil.ui.activity.release.ReleaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        startActivity(new Intent(this, ReleaseActivity.class));
    }
}
