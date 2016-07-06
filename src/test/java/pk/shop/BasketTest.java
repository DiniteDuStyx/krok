package pk.shop;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketTest {

    ProductFactory factory = new ProductFactory();

    @Test
    public void basketWithNoProducts() {
        Basket basket = new Basket();

        assertThat(basket.total()).isEqualTo(0);
    }

    @Test
    public void basketWithTwoProducts() {
        Product telefon = factory.createProduct("telefon");
        Product budzik = factory.createProduct("budzik");
        Basket basket = new Basket();

        basket.addProduct(telefon);
        basket.addProduct(budzik);

        assertThat(basket.total()).isEqualTo(1080);
    }

    @Test
    public void basketWithDoubledProduct() {
        Product laptop = factory.createProduct("laptop");
        Basket basket = new Basket();

        basket.addProduct(laptop);
        basket.addProduct(laptop);

        assertThat(basket.total()).isEqualTo(6000);
    }

    @Test
    public void basketWithThreeDiscountPoliciesOnlyOneApplies() {
        Product tablet = new Product("Tablet", 2000, Category.POINTING_DEVICES);
        Product mouse = new Product("Mouse", 100, Category.POINTING_DEVICES);
        Basket basket = new Basket();
        basket.setDiscountPolicies(
                DiscountPolicyFactory.createCategoryDiscount(0.2, Category.PC),
                DiscountPolicyFactory.createCategoryDiscount(0.5, Category.POINTING_DEVICES),
                DiscountPolicyFactory.createGroupCategoryDiscount(0.1, Category.AMD, Category.INTEL));
        basket.addProduct(tablet);
        basket.addProduct(mouse);

        double result = basket.total();

        assertThat(result).isEqualTo(1050);
    }


    @Test
    public void basketWithEmptyDiscountPoliciesAdded() {
        Product tablet = new Product("Tablet", 2000, Category.POINTING_DEVICES);
        Product mouse = new Product("Mouse", 100, Category.POINTING_DEVICES);
        Basket basket = new Basket();
        basket.addProduct(tablet);
        basket.addProduct(mouse);
        basket.setDiscountPolicies();

        double result = basket.total();

        assertThat(result).isEqualTo(2100);
    }

    @Test
    public void basketWithThreeDiscountPolicies() {
        Product tablet = new Product("Tablet", 2000, Category.POINTING_DEVICES);
        Product mouse = new Product("Mouse", 100, Category.POINTING_DEVICES);
        Product cpu1 = new Product("CPU1", 600, Category.INTEL);
        Product cpu2 = new Product("CPU2", 400, Category.AMD);
        Product cpu3 = new Product("CPU3", 450, Category.AMD);
        Basket basket = new Basket();
        basket.setDiscountPolicies(
                DiscountPolicyFactory.createCategoryDiscount(0.2, Category.PC),
                DiscountPolicyFactory.createCategoryDiscount(0.2, Category.POINTING_DEVICES),
                DiscountPolicyFactory.createGroupCategoryDiscount(0.5, Category.AMD, Category.INTEL));
        basket.addProduct(tablet, mouse, cpu1, cpu2, cpu3);


        double result = basket.total();

        // 2100 + 1450 - 725 = 2825
        assertThat(result).isEqualTo(2825);
    }


}