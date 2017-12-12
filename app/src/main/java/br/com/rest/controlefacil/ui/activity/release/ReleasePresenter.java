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

public class ReleasePresenter implements ReleaseContract.Presenter {

    private ReleaseContract.View view;
    private CategoryDAO categoryDAO;
    private ReleaseDAO releaseDAO;
    private PeriodDAO periodDAO;

    public ReleasePresenter(ReleaseContract.View view) {
        this.view = view;
    }

    @Override
    public Category findFirstCategory() {
        return categoryDAO.findFirst();
    }

    @Override
    public Category findLastCategory() {
        return categoryDAO.findLastCategory();
    }

    @Override
    public Period findFirstPeriod() {
        return periodDAO.findFirst();
    }

    @Override
    public List<Release> findAll() {
        return releaseDAO.findAll();
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryDAO.findAll(br.com.rest.controlefacil.domain.enums.Category.EXPENSES);
    }

    @Override
    public List<Period> findAllPeriods() {
        return periodDAO.findAll();
    }

    @Override
    public String[] periods(List<Period> list) {
        String[] periods = new String[list.size()];
        for( int i = 0; i < list.size(); i++){
            periods[i] = list.get(i).getDescription();
        }
        return periods;
    }

    @Override
    public void save(Release release) {
        releaseDAO.save(release);
    }

    @Override
    public void save(Category category) {
        categoryDAO.save(category);
    }

    @Override
    public void set(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public void set(ReleaseDAO releaseDAO) {
        this.releaseDAO = releaseDAO;
    }

    @Override
    public void set(PeriodDAO periodDAO) {
        this.periodDAO = periodDAO;
    }
}
