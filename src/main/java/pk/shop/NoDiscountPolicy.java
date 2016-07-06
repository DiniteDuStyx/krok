package pk.shop;

import java.util.List;

public class NoDiscountPolicy implements DiscountPolicy {
    @Override
    public List<Product> applyDiscount(List<Product> products) {
        return products;
    }
}
