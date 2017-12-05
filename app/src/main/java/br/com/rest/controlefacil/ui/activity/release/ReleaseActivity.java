package br.com.rest.controlefacil.ui.activity.release;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import br.com.rest.controlefacil.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ReleaseActivity extends AppCompatActivity {

    @BindView(R.id.edt_period)
    AppCompatEditText edtPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        ButterKnife.bind(this);
        edtPeriod.setKeyListener(null);
        edtPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ReleaseActivity.this, "As", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
