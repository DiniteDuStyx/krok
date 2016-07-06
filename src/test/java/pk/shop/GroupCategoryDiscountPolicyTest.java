package pk.shop;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GroupCategoryDiscountPolicyTest {

    List<Product> products;

    @Before
    public void setUp() throws Exception {
        products = Arrays.asList(new Product("i5Core", 10, Category.INTEL),
                new Product("OpenOffice", 20, Category.OFFICE),
                new Product("Computer", 100, Category.COMPUTERS),
                new Product("WOW", 123.54, Category.PC),
                new Product("WOT", 57.97, Category.XBOX),
                new Product("WOT2", 57.97, Category.XBOX));
    }

    @Test(expected = RuntimeException.class)
    public void noCategory() {
        new GroupCategoryDiscountPolicy(0.1);
    }

    @Test(expected = RuntimeException.class)
    public void oneCategory() {
        new GroupCategoryDiscountPolicy(0.1, Category.CPUS);
    }

    @Test
    public void threeCategories() {
        DiscountPolicy discountPolicy = new GroupCategoryDiscountPolicy(0.1, Category.CPUS, Category.PC, Category.XBOX);

        products = discountPolicy.applyDiscount(products);

        Assertions.assertThat(products).extracting(Product::getPrice).containsExactly(10.0, 20.0, 100.0, 111.19, 52.17, 52.17);
    }

    @Test
    public void threeCategoriesSecondConstructor() {
        DiscountPolicy discountPolicy = new GroupCategoryDiscountPolicy(0.2, new ArrayList<Category>(Arrays.asList(Category.ALL, Category.XBOX, Category.GRAPHIC_CARDS)));

        products = discountPolicy.applyDiscount(products);

        Assertions.assertThat(products).extracting(Product::getPrice).containsExactly(10.0, 20.0, 100.0, 123.54, 57.97, 57.97);
    }

    @Test
    public void threeCategoriesSecondConstructor2() {
        DiscountPolicy discountPolicy = new GroupCategoryDiscountPolicy(0.35, new ArrayList<Category>(Arrays.asList(Category.OFFICE, Category.XBOX, Category.COMPUTERS)));

        products = discountPolicy.applyDiscount(products);

        Assertions.assertThat(products).extracting(Product::getPrice).containsExactly(10.0, 13.0, 65.0, 123.54, 37.68, 37.68);
    }

}