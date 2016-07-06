package pk.shop;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ProductFactoryTest {

    ProductFactory factory = new ProductFactory();

    @Test
    public void createProduct() throws Exception {
        Product budzik = factory.createProduct("budzik");

        assertThat(budzik.getPrice()).isEqualTo(80);
    }

    @Test
    public void createTelefon() {
        Product budzik = factory.createProduct("telefon");

        assertThat(budzik.getPrice()).isEqualTo(1000);
    }
}