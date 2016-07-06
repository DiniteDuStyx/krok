package pk.shop;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryTest {

    @Test
    public void shouldBelong() {
        Assertions.assertThat(Category.INTEL.belongsTo(Category.ELECTRONICS)).isTrue();
    }

    @Test
    public void shouldNotBelong() {
        Assertions.assertThat(Category.INTEL.belongsTo(Category.GAMES)).isFalse();
    }

    @Test
    public void shouldNotBelongToSelf() {
        Assertions.assertThat(Category.INTEL.belongsTo(Category.INTEL)).isFalse();
    }

}