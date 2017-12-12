package br.com.rest.controlefacil.ui.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import br.com.rest.controlefacil.ControleFacilApplication;
import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.domain.event.CategorySelectedEvent;
import br.com.rest.controlefacil.domain.model.Category;
import br.com.rest.controlefacil.ui.activity.category.CategoryActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static br.com.rest.controlefacil.ControleFacilApplication.getAppComponent;

/**
 * Created by LUCAS RODRIGUES on 03/12/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    @Inject
    List<Category> categories;
    @Inject
    EventBus eventBus;
    @Inject
    CategorySelectedEvent categorySelectedEvent;

    public static final int SCREEN_CATEGORY_ACTIVITY = 0;
    public static final int SCREEN_RELEASE_ACTIVITY = 1;

    private CategoryActivity activity;
    //0 -> CategoryActivity, 1 -> ReleaseActivity
    private int screen;

    public void setScreen(int screen) {
        this.screen = screen;
    }

    public CategoryAdapter() {
        screen = 0;
        getAppComponent().inject(this);
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setActivity(CategoryActivity activity) {
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            final Category category = categories.get(position);
            Integer color;
            if (category.getCategoryType() == 1) {
                color = R.color.expensesColorSecondaryDark;
            } else {
                color = R.color.recipesColorSecondaryDark;
            }
            holder.ivBackground.setImageResource(color);
            holder.ivIcon.setBackgroundResource(category.getIcon());
            holder.tvName.setText(category.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (screen == SCREEN_CATEGORY_ACTIVITY)
                        activity.callDialogCategory(category);
                    else{
                        categorySelectedEvent.setCategory(category);
                        eventBus.post(categorySelectedEvent);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_background)
        CircleImageView ivBackground;
        @BindView(R.id.iv_icon)
        CircleImageView ivIcon;
        @BindView(R.id.tv_name)
        AppCompatTextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
