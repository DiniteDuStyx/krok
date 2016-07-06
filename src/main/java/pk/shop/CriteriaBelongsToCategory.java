package pk.shop;

import java.util.ArrayList;
import java.util.List;

public class CriteriaBelongsToCategory implements Criteria {
    private Category category;

    public CriteriaBelongsToCategory(Category category) {
        this.category = category;
    }

    @Override
    public List<Product> meetCriteria(List<Product> products) {

        List<Product> result = new ArrayList<>();

        for(Product product : products) {
            if(product.belongsTo(category)) {
                result.add(product);
            }
        }
        return result;
    }
}
