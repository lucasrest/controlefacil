package br.com.rest.controlefacil.ui.adapter;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import br.com.rest.controlefacil.R;
import br.com.rest.controlefacil.domain.event.SelectedIconEvent;
import br.com.rest.controlefacil.domain.model.CategoryIcons;
import butterknife.BindView;
import butterknife.ButterKnife;

import static br.com.rest.controlefacil.ControleFacilApplication.getAppComponent;

/**
 * Created by LUCAS RODRIGUES on 02/12/2017.
 */

public class CategoryIconsAdapter extends RecyclerView.Adapter<CategoryIconsAdapter.ViewHolder> {

    @Inject
    EventBus eventBus;
    @Inject
    SelectedIconEvent selectedIconEvent;
    private List<Integer> icons;

    public CategoryIconsAdapter() {
        icons = CategoryIcons.icons;
        getAppComponent().inject(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_icons, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.ivIcon.setBackgroundResource(icons.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedIconEvent.setIcon(icons.get(position));
                eventBus.post(selectedIconEvent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return icons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_icon)
        AppCompatImageView ivIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
