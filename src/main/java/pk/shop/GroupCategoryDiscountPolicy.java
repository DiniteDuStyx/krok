package pk.shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static smile.math.Math.round;


public class GroupCategoryDiscountPolicy implements DiscountPolicy {

    List<Category> categories = new ArrayList<>();
    double value;

    public GroupCategoryDiscountPolicy(double value, Category... categories) {
        this(value, Arrays.asList(categories));
    }

    public GroupCategoryDiscountPolicy(double value, List<Category> categories) {
        if(categories == null || categories.size() < 2) {
            throw new RuntimeException();
        }
        DiscountValueValidator.validatePercentageDiscountValue(value);
        this.categories.addAll(categories);
        this.value = value;
    }

    @Override
    public List<Product> applyDiscount(List<Product> products) {
        int counter = 0;
        List<Product> matchedProducts = new ArrayList<>();
        for(Category category : categories) {
            if(matchedProducts.addAll(new CriteriaIsInCategory(category).meetCriteria(products)))
                counter++;
        }

        if(counter < 2)
            return products;

        List<Product> returnProducts = new ArrayList<>(products);
        for(Product product: matchedProducts) {
            returnProducts.set(returnProducts.indexOf(product),
                    new Product(product.getName(), round(product.getPrice() * (1.0 - value),2), product.getCategory()));
        }
        return returnProducts;
    }
}
