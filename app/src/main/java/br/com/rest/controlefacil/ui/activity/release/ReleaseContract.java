package br.com.rest.controlefacil.ui.activity.release;

import java.util.List;

import br.com.rest.controlefacil.domain.dao.CategoryDAO;
import br.com.rest.controlefacil.domain.dao.PeriodDAO;
import br.com.rest.controlefacil.domain.dao.ReleaseDAO;
import br.com.rest.controlefacil.domain.model.Category;
import br.com.rest.controlefacil.domain.model.Period;
import br.com.rest.controlefacil.domain.model.Release;

/**
 * Created by LUCAS RODRIGUES on 04/12/2017.
 */

public interface ReleaseContract {

    interface View {
        boolean validateForm();
        void showToast(String message);
    }

    interface Presenter {
        Category findFirstCategory();
        Period findFirstPeriod();
        List<Release> findAll();
        List<Category> findByCategoryName(String name);
        List<Category> findAllCategories();
        List<Period> findAllPeriods();
        String[] periods(List<Period> list);
        void save(Release release);
        void save(Category category);
        void set(CategoryDAO categoryDAO);
        void set(ReleaseDAO releaseDAO);
        void set(PeriodDAO periodDAO);
    }
}
