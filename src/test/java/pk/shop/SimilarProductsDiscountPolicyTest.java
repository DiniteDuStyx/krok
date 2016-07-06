package pk.shop;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SimilarProductsDiscountPolicyTest {

    @Test
    public void testApplyDiscount1() {
        DiscountPolicy discountPolicy = new SimilarProductsDiscountPolicy();
        List<Product> products = Arrays.asList(
                new Product("i5Core", 10, Category.INTEL),
                new Product("OpenOffice", 20, Category.OFFICE),
                new Product("Computer", 100, Category.COMPUTERS),
                new Product("WOW", 123.54, Category.PC),
                new Product("WOT", 57.97, Category.XBOX),
                new Product("WOT2", 57.97, Category.XBOX));

        products = discountPolicy.applyDiscount(products);

        Assertions.assertThat(products).extracting(Product::getPrice).containsExactly(10.0, 20.0, 100.0, 111.19, 52.17, 52.17);
    }

    @Test
    public void testApplyDiscount2() {
        DiscountPolicy discountPolicy = new SimilarProductsDiscountPolicy();
        List<Product> products = Arrays.asList(
                new Product("i5Core", 10, Category.ALL),
                new Product("OpenOffice", 20, Category.ELECTRONICS),
                new Product("Computer", 100, Category.OFFICE),
                new Product("WOW", 123.54, Category.PC),
                new Product("WOT", 57.97, Category.GAMES),
                new Product("WOT2", 57.97, Category.XBOX));

        products = discountPolicy.applyDiscount(products);

        Assertions.assertThat(products).extracting(Product::getPrice).containsExactly(9.0, 18.0, 90.0, 111.19, 52.17, 52.17);
    }

    @Test
    public void testApplyDiscount3() {
        DiscountPolicy discountPolicy = new SimilarProductsDiscountPolicy();
        List<Product> products = Arrays.asList(
                new Product("i5Core", 10, Category.PAPER), //20
                new Product("OpenOffice", 20, Category.OFFICE), //20
                new Product("Computer", 100, Category.OFFICE), //20
                new Product("WOW", 123.54, Category.DRAWING), //20
                new Product("WOT", 57.97, Category.POINTING_DEVICES), //20
                new Product("WOT2", 57.97, Category.XBOX),
                new Product("OpenOffice", 20, Category.ELECTRONICS), //10
                new Product("Computer2", 100, Category.OFFICE), //20
                new Product("WOW", 200, Category.PAPER), //20
                new Product("WOT", 50, Category.GAMES)); //10

        products = discountPolicy.applyDiscount(products);

        Assertions.assertThat(products).extracting(Product::getPrice).
                containsExactly(8.0, 16.0, 80.0, 98.83, 46.38, 57.97, 18.0, 80.0, 160.0, 45.0);
    }

    @Test
    public void testApplyDiscount4() {
        DiscountPolicy discountPolicy = new SimilarProductsDiscountPolicy();
        List<Product> products = Arrays.asList(
                new Product("i5Core1", 10, Category.ALL), //5
                new Product("OpenOffice2", 20, Category.OFFICE), //10
                new Product("Computer3", 100, Category.OFFICE), //50
                new Product("WOW4", 400, Category.ELECTRONICS), //200
                new Product("WOT5", 300, Category.COMPUTERS), //240
                new Product("WOT26", 50, Category.CPUS), //50
                new Product("OpenOffice7", 20, Category.AMD), //20
                new Product("Computer8", 100, Category.GAMES), //50
                new Product("WOW9", 200, Category.GAMES), //100
                new Product("WOT10", 60, Category.OFFICE), //30
                new Product("WOT211", 100, Category.ELECTRONICS), //50
                new Product("OpenOffice12", 20, Category.ELECTRONICS), //20
                new Product("Computer", 100, Category.ALL), //50
                new Product("WOW", 200, Category.GAMES), //100
                new Product("WOW", 200, Category.GAMES), //100
                new Product("WOT", 100, Category.OFFICE), //50
                new Product("WOT2", 100, Category.ELECTRONICS), //50
                new Product("OpenOffice", 20, Category.ELECTRONICS), //10
                new Product("Computer", 100, Category.ALL), //50
                new Product("WOW", 200, Category.PC), //160
                new Product("Computer", 100, Category.ALL), //50
                new Product("WOW", 200, Category.GAMES), //100
                new Product("WOT", 50, Category.GAMES)); //25

        products = discountPolicy.applyDiscount(products);

        Assertions.assertThat(products).extracting(Product::getPrice).
                containsExactly(5.0, 10.0, 50.0, 200.0, 240.0, 50.0, 20.0, 50.0, 100.0, 30.0, 50.0, 10.0, 50.0,
                        100.0, 100.0, 50.0, 50.0, 10.0, 50.0, 160.0, 50.0, 100.0, 25.0);
    }

    @Test
    public void testApplyDiscount5() {
        DiscountPolicy discountPolicy = new SimilarProductsDiscountPolicy();
        List<Product> products = Arrays.asList(
                new Product("i5Core", 10, Category.ALL),
                new Product("OpenOffice", 20, Category.ELECTRONICS),
                new Product("Computer", 100, Category.COMPUTERS),
                new Product("WOW", 123.54, Category.PC),
                new Product("WOT", 57.0, Category.AMD),
                new Product("WOT2", 60.0, Category.INTEL));

        products = discountPolicy.applyDiscount(products);

        Assertions.assertThat(products).extracting(Product::getPrice).containsExactly(10.0, 20.0, 100.0, 123.54, 57.0, 60.0);
    }
}