package pk.shop;

public class DiscountPolicyFactory {
    public static DiscountPolicy createCategoryDiscount(double value, Category category) {
        return new CategoryDiscountPolicy(category, value);
    }

    public static DiscountPolicy createGroupCategoryDiscount(double value, Category... categories) {
        return new GroupCategoryDiscountPolicy(value, categories);
    }

    public static DiscountPolicy createSimilarProductsDiscount() {
        return new SimilarProductsDiscountPolicy();
    }
}
