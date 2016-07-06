package pk.shop;

import java.util.List;

public interface DiscountPolicy {
    List<Product> applyDiscount(List<Product> products);
}
