package br.com.rest.controlefacil.ui.fragment.category;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.domain.enums.Category;
import br.com.rest.controlefacil.ui.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import static br.com.rest.controlefacil.R.style.ExpensesTheme;

public class CategoryFragment extends BaseFragment {

    private static final String CATEGORY = "category";
    @BindView(R.id.txt)
    TextView textView;
    private Category category;

    public CategoryFragment() {
    }

    public static CategoryFragment newInstance(Category category, CategoryFragment fragment, Bundle args ) {
        args.putParcelable(CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = (Category) getArguments().getParcelable(CATEGORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);
        super.onViewCreated(view, savedInstanceState);
        return view;
    }

}
