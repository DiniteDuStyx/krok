package pk.shop;

import java.util.*;

public class Basket {

    private List<Product> productList = new ArrayList<>();
    private List<DiscountPolicy> discountPolicies = Collections.singletonList(new NoDiscountPolicy());

    public void addProduct(Product product) {
        if(productList.contains(product)) {
            product = product.clone();
        }
        productList.add(product);
    }

    //enables calling method with unlimited number of argumenrs for example: basket.addProduct(telefon, budzik, krzeslo);
    public void addProduct(Product... products) {
        for(Product product: products)
            addProduct(product);
    }

    public void setDiscountPolicies(List<DiscountPolicy> discountPolicies) {
        this.discountPolicies = new ArrayList<>(discountPolicies);
        this.discountPolicies.add(new NoDiscountPolicy());
    }

    public void setDiscountPolicies(DiscountPolicy... discountPolicies) {
        setDiscountPolicies(Arrays.asList(discountPolicies));
    }


    private double total(DiscountPolicy discountPolicy) {
        List<Product> products = (discountPolicy == null ? productList : discountPolicy.applyDiscount(productList));
        double total = 0;
        for(Product product: products) {
            total += product.getPrice();
        }
        return total;
    }

    public double total() {
        return discountPolicies.stream().mapToDouble(this::total).min().getAsDouble();
    }

}
