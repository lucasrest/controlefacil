package br.com.rest.controlefacil.ui.activity;

import android.support.v7.app.AppCompatActivity;

import br.com.rest.controlefacil.di.component.DaggerUIComponent;
import br.com.rest.controlefacil.di.component.UIComponent;
import br.com.rest.controlefacil.di.module.ui.UIModule;
import butterknife.ButterKnife;

import static br.com.rest.controlefacil.ControleFacilApplication.getAppComponent;

/**
 * Created by LUCAS RODRIGUES on 28/11/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected void bindViews() {
        ButterKnife.bind(this);
    }

    protected UIComponent component(BaseActivity baseActivity) {
        return DaggerUIComponent
                .builder()
                .appComponent(getAppComponent())
                .uIModule(new UIModule(baseActivity))
                .build();
    }

}
