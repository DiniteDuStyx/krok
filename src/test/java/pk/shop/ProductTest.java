package pk.shop;

import org.junit.Test;

import static pk.shop.Category.INTEL;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    public static final int DUMMYPRICE = 10;
    public static final String DUMMYNAME = "Core_i5";

    @Test
    public void canGetCategory() {
        Product p1 = new Product(DUMMYNAME, DUMMYPRICE, INTEL);
        assertThat(p1.getCategory()).isEqualTo(INTEL);
    }

    @Test
    public void belongsToAssignedCategory() {
        Product p1 = new Product(DUMMYNAME, DUMMYPRICE, INTEL);
        assertThat(p1.belongsTo(INTEL)).isTrue();
    }

    @Test
    public void belongsToAll() {
        Product p1 = new Product(DUMMYNAME, DUMMYPRICE, INTEL);
        assertThat(p1.belongsTo(Category.ALL)).isTrue();
    }
}