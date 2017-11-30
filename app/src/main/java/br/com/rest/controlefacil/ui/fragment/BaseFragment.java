package br.com.rest.controlefacil.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import br.com.rest.controlefacil.ControleFacilApplication;
import br.com.rest.controlefacil.di.component.DaggerUIComponent;
import br.com.rest.controlefacil.di.component.UIComponent;
import br.com.rest.controlefacil.di.module.ui.FragmentModule;
import br.com.rest.controlefacil.di.module.ui.UIModule;
import butterknife.ButterKnife;

/**
 * Created by PROGRAMAÇÃO on 30/11/2017.
 */

public class BaseFragment extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
    }

    private void bindViews(View view){
        ButterKnife.bind(this, view);
    }

    protected UIComponent component(){
        return DaggerUIComponent
                .builder()
                .appComponent(ControleFacilApplication.getAppComponent())
                .uIModule( new UIModule())
                .fragmentModule( new FragmentModule())
                .build();
    }
}
