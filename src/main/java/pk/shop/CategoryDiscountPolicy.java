package pk.shop;

import java.util.ArrayList;
import java.util.List;

import static smile.math.Math.round;

public class CategoryDiscountPolicy implements DiscountPolicy{

    private Category category;
    private double value;

    public CategoryDiscountPolicy(Category category, double value) {
        DiscountValueValidator.validatePercentageDiscountValue(value);
        this.category = category;
        this.value = value;
    }

    @Override
    public List<Product> applyDiscount(List<Product> products) {
        List<Product> matchedProducts = new CriteriaBelongsToCategory(category).meetCriteria(products);
        List<Product> resultProducts = new ArrayList<>(products);

        for(Product product: matchedProducts) {
            resultProducts.set(resultProducts.indexOf(product),
                    new Product(product.getName(), round(product.getPrice() * (1.0 - value),2), product.getCategory()));
        }
        return resultProducts;
    }





}
