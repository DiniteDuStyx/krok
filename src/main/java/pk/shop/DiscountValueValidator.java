package pk.shop;

/**
 * Created by Martyna on 2016-05-13.
 */
public class DiscountValueValidator {

    public static void validatePercentageDiscountValue(double value) {
        if(value < 0 || value >= 1) {
            throw new RuntimeException();
        }
    }
}
