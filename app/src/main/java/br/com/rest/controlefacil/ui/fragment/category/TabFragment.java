package br.com.rest.controlefacil.ui.fragment.category;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.domain.event.CategoryChangeEvent;
import br.com.rest.controlefacil.ui.fragment.BaseFragment;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment extends BaseFragment {

    private final static int tabs = 2;
    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.vp_container)
    ViewPager viewPager;
    @Inject
    @Named("ExpensesFragment")
    CategoryFragment expensesFragment;
    @Inject
    @Named("RecipesFragment")
    CategoryFragment recipesFragment;
    @Inject
    @Named("ExpensesCategoryChangeEvent")
    CategoryChangeEvent expensesEvent;
    @Inject
    @Named("RecipesCategoryChangeEvent")
    CategoryChangeEvent recipesEvent;
    @Inject
    EventBus eventBus;

    public TabFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        super.onViewCreated(view, savedInstanceState);
        component().inject(this);
        viewPager.setAdapter(new TabAdapter(getFragmentManager()));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    tabLayout.setBackgroundColor(getResources().getColor(R.color.expensesColorPrimary));
                    eventBus.post(expensesEvent);
                } else {
                    tabLayout.setBackgroundColor(getResources().getColor(R.color.recipesColorPrimary));
                    eventBus.post(recipesEvent);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        return view;
    }

    public class TabAdapter extends FragmentPagerAdapter {

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return expensesFragment;
                case 1:
                    return recipesFragment;
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Despesas";
                case 1:
                    return "Receitas";
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return tabs;
        }
    }

}
