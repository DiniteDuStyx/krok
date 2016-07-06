package pk.shop;

import java.util.List;

public class CriteriaOr implements Criteria{
    private Criteria criteria1;
    private Criteria criteria2;

    public CriteriaOr(Criteria criteria1, Criteria criteria2) {
        this.criteria1 = criteria1;
        this.criteria2 = criteria2;
    }

    @Override
    public List<Product> meetCriteria(List<Product> products) {
        List<Product> matchesCriteria1 = criteria1.meetCriteria(products);
        List<Product> matchesCriteria2 = criteria2.meetCriteria(products);

        for(Product product : matchesCriteria2) {
            if(!matchesCriteria1.contains(product)) {
                matchesCriteria1.add(product);
            }
        }
        return matchesCriteria1;
    }
}
