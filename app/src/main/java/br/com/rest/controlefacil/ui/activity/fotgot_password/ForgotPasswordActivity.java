package br.com.rest.controlefacil.ui.activity.fotgot_password;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import javax.inject.Inject;

import br.com.rest.controlefacil.ControleFacilApplication;
import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.ui.di.DaggerUIComponent;
import br.com.rest.controlefacil.ui.di.UIModule;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordContract.View{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Inject
    ForgotPasswordContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind( this );
        DaggerUIComponent
                .builder()
                .appComponent(ControleFacilApplication.getAppComponent() )
                .uIModule( new UIModule( this ))
                .build()
                .inject( this );
        setSupportActionBar( toolbar );
        getSupportActionBar().setTitle( R.string.forgot_title );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() ){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @OnClick(R.id.btn_recover)
    public void recoverAccount(){
        presenter.recoverAccount();
    }
}
