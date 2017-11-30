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

import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.domain.enums.Category;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment extends Fragment {

    private final static int tabs = 2;//new String[]{getString(R.string.tab_expenses), getString(R.string.tab_recipes)};
    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.vp_container)
    ViewPager viewPager;


    public TabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        ButterKnife.bind(this, view);
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
                    tabLayout.setSelectedTabIndicatorColor(getResources().getColor(android.R.color.white));

                } else {
                    tabLayout.setBackgroundColor(getResources().getColor(R.color.recipesColorPrimary));

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
                    return CategoryFragment.newInstance(Category.EXPENSES);
                case 1:
                    return CategoryFragment.newInstance(Category.RECIPES);
                default:
                    return null;
            }

        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Desepesas";
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
