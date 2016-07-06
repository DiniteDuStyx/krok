package pk.shop;

import java.util.*;

import static smile.math.Math.round;

public class SimilarProductsDiscountPolicy implements DiscountPolicy {

    @Override
    public List<Product> applyDiscount(List<Product> products) {

        List<Product> resultProducts = new ArrayList<>(products);
        double[] discounts = new double[products.size()];

        for(Product product : products) {
            List<Product> matches = new CriteriaOr(
                    new CriteriaHasCategoryParent(product.getCategory().getParent()),
                    new CriteriaIsInCategory(product.getCategory().getParent())).meetCriteria(products);

            if(matches.size() < 3)
                continue;

            double discount = Math.min(Math.floor(matches.size() / 3) * 0.1, 0.5);

            for (Product innerProduct : matches) {
                discounts[products.indexOf(innerProduct)] = Math.max(discounts[products.indexOf(innerProduct)], discount);
            }
        }

        for(int i = 0; i < discounts.length; i++) {
            if(discounts[i] > 0) {
                Product p = resultProducts.get(i);
                resultProducts.set(i, new Product(p.getName(), round(p.getPrice() * (1.0 - discounts[i]),2), p.getCategory()));
            }
        }
        return resultProducts;
    }
}
