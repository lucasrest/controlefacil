package br.com.rest.controlefacil.domain.model;

import java.util.Arrays;
import java.util.List;

import br.com.rest.controlefacil.R;

/**
 * Created by LUCAS RODRIGUES on 02/12/2017.
 */

public class CategoryIcons {

    public static List<Integer> icons = Arrays.asList(
            R.drawable.ic_restaurant,
            R.drawable.ic_beach,
            R.drawable.ic_car,
            R.drawable.ic_city,
            R.drawable.ic_credit_card,
            R.drawable.ic_fitness,
            R.drawable.ic_giftcard,
            R.drawable.ic_home,
            R.drawable.ic_joypad,
            R.drawable.ic_school,
            R.drawable.ic_pizza,
            R.drawable.ic_plane,
            R.drawable.ic_other,
            R.drawable.ic_tshirt,
            R.drawable.ic_trending_up,
            R.drawable.ic_attachment,
            R.drawable.ic_bargraph,
            R.drawable.ic_bike,
            R.drawable.ic_book,
            R.drawable.ic_build,
            R.drawable.ic_bus,
            R.drawable.ic_business,
            R.drawable.ic_calculator,
            R.drawable.ic_cart,
            R.drawable.ic_cash,
            R.drawable.ic_child,
            R.drawable.ic_clock,
            R.drawable.ic_closed,
            R.drawable.ic_cloudy,
            R.drawable.ic_coffee,
            R.drawable.ic_cool,
            R.drawable.ic_cry,
            R.drawable.ic_info,
            R.drawable.ic_music,
            R.drawable.ic_trophy,
            R.drawable.ic_smoking,
            R.drawable.ic_pool
    );

    public static Integer getDefaultCategoryIcon(){
        return icons.get(12);
    }

    public static List<Category> getDefaultCategories(){
        return Arrays.asList(
                new Category(1L, "Lazer", R.drawable.ic_beach, 1),
                new Category(2L, "Cartão de Credito", R.drawable.ic_credit_card, 1),
                new Category(3L, "Carro", R.drawable.ic_car, 1),
                new Category(4L, "Aluguel", R.drawable.ic_home, 1),
                new Category(5L, "Estudos", R.drawable.ic_school, 1),
                new Category(6L, "Roupas", R.drawable.ic_tshirt, 1),
                new Category(7L, "Passagens", R.drawable.ic_bus, 1),
                new Category(8L, "Outros", R.drawable.ic_other, 1),
                new Category(9L, "Salario", R.drawable.ic_cash, 2),
                new Category(10L, "Investimentos", R.drawable.ic_trending_up, 2),
                new Category(11L, "Presentes", R.drawable.ic_giftcard, 2),
                new Category(12L, "Bonificações", R.drawable.ic_trophy, 2),
                new Category(13L, "Outros", R.drawable.ic_other, 2)
        );
    }

}
