package br.com.rest.controlefacil.ui.activity.privacy_policies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.ui.activity.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PrivacyPoliciesActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policies);
        bindViews();
        setSupportActionBar( toolbar );
        getSupportActionBar().setTitle(R.string.privacy_policies_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                super.onOptionsItemSelected( item );
        }
        return true;
    }
}
