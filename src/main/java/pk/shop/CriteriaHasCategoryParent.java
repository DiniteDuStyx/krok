package pk.shop;

import java.util.ArrayList;
import java.util.List;

public class CriteriaHasCategoryParent implements Criteria {
    private Category category;

    public CriteriaHasCategoryParent(Category category) {
        this.category = category;
    }

    @Override
    public List<Product> meetCriteria(List<Product> products) {
        List<Product> result = new ArrayList<>();

        for(Product product : products) {
            if(product.getCategory().getParent() == category) {
                result.add(product);
            }
        }
        return result;
    }
}
