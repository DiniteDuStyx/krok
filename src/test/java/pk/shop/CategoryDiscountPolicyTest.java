package pk.shop;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class CategoryDiscountPolicyTest {
    List<Product> products;

    @Before
    public void createProducts() {
        products = Arrays.asList(new Product("i5Core", 10, Category.INTEL),
                new Product("OpenOffice", 20, Category.OFFICE),
                new Product("Computer", 100, Category.COMPUTERS));
    }

    @Test
    public void lowersPriceOfProductsBelongingToCategory() {
        DiscountPolicy discountPolicy = new CategoryDiscountPolicy(Category.COMPUTERS, 0.5);

        products = discountPolicy.applyDiscount(products);

        Assertions.assertThat(products).extracting(Product::getPrice).containsExactly(5.0, 20.0, 50.0);
    }

    @Test
    public void lowersPriceOfProductsBelongingToCategory2() {
        List<Product> products2 = Arrays.asList(new Product("i5Core", 10, Category.INTEL),
                new Product("OpenOffice", 20, Category.OFFICE),
                new Product("Computer", 100, Category.COMPUTERS),
                new Product("WOW", 123.54, Category.GAMES),
                new Product("WOT", 57.97, Category.GAMES));

        DiscountPolicy discountPolicy = new CategoryDiscountPolicy(Category.ALL, 0.2);

        products = discountPolicy.applyDiscount(products2);

        Assertions.assertThat(products).extracting(Product::getPrice).containsExactly(8.0, 16.0, 80.0, 98.83, 46.38);
    }

    @Test(expected = RuntimeException.class)
    public void exceptionWhenValueOfDiscountIsOne() {
        DiscountPolicy discountPolicy = new CategoryDiscountPolicy(Category.COMPUTERS, 1);
    }

    @Test(expected = RuntimeException.class)
    public void exceptionWhenValueOfDiscountIsNegative() {
        DiscountPolicy discountPolicy = new CategoryDiscountPolicy(Category.COMPUTERS, -0.5);
    }

    @Test
    public void priceOfProductsNotChanged() {
        DiscountPolicy discountPolicy = new CategoryDiscountPolicy(Category.COMPUTERS, 0.0);

        products = discountPolicy.applyDiscount(products);

        Assertions.assertThat(products).extracting(Product::getPrice).containsExactly(10.0, 20.0, 100.0);
    }

    @Test(expected = RuntimeException.class)
    public void exceptionWhenValueOfDiscountBiggerThanOne() {
        DiscountPolicy discountPolicy = new CategoryDiscountPolicy(Category.COMPUTERS, 1.2);
    }

//    @Test
//    public void noExceptionWhenNoProducts() {
//        DiscountPolicy discountPolicy = new CategoryDiscountPolicy(Category.COMPUTERS, 0.7);
//
//        products = discountPolicy.applyDiscount(products);
//
//        assertThat(products.size()).isEquals(0);
//    }




}