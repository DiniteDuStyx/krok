package pk.shop;

import java.util.List;

public interface Criteria {
    public List<Product> meetCriteria(List<Product> products);
}
