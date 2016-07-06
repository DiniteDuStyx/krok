package pk.shop;

import java.util.ArrayList;
import java.util.List;

public class CriteriaIsInCategory implements Criteria {

    private Category category;

    public CriteriaIsInCategory(Category category) {
        this.category = category;
    }

    @Override
    public List<Product> meetCriteria(List<Product> products) {

        List<Product> result = new ArrayList<>();

        for(Product product : products) {
            if(product.getCategory() == category) {
                result.add(product);
            }
        }
        return result;
    }
}
