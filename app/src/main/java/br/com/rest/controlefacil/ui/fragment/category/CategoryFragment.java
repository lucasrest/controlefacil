package br.com.rest.controlefacil.ui.fragment.category;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.domain.dao.CategoryDAO;
import br.com.rest.controlefacil.domain.enums.Category;
import br.com.rest.controlefacil.domain.event.ChangeInCategoryListEvent;
import br.com.rest.controlefacil.ui.activity.category.CategoryActivity;
import br.com.rest.controlefacil.ui.adapter.CategoryAdapter;
import br.com.rest.controlefacil.ui.fragment.BaseFragment;
import butterknife.BindView;

public class CategoryFragment extends BaseFragment implements CategoryContract.View {
    private static final String CATEGORY = "category";
    @BindView(R.id.rv_categories)
    RecyclerView recyclerView;
    @Inject
    CategoryAdapter categoryAdapter;
    @Inject
    CategoryDAO categoryDAO;
    @Inject
    List<br.com.rest.controlefacil.domain.model.Category> categories;
    @Inject
    CategoryContract.Presenter presenter;
    @Inject
    EventBus eventBus;

    private Category category;

    public CategoryFragment() {
    }

    public static CategoryFragment newInstance(Category category, CategoryFragment fragment, Bundle args) {
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
        component(this).inject(this);
        presenter.setCategoryDAO(categoryDAO);
        categories = presenter.findAll(category);
        categoryAdapter.setActivity((CategoryActivity) getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        categoryAdapter.setCategories(categories);
        recyclerView.setAdapter(categoryAdapter);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        categoryDAO.close();
    }

    @Override
    public void onStart() {
        super.onStart();
        eventBus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    @Subscribe
    public void onEvent(ChangeInCategoryListEvent changeInCategoryListEvent){
        categories = presenter.findAll(category);
        categoryAdapter.notifyDataSetChanged();
    }
}
