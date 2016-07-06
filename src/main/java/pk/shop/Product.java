package pk.shop;

public class Product implements Cloneable {
    private String name;
    private double price;
    private Category category;


    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.category = Category.ALL;
    }

    public Product(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    //chyba moze być taki getter?
    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public boolean belongsTo(Category category) {
        return this.category == category || this.category.belongsTo(category);
    }

    @Override
    public Product clone() {
        return new Product(this.getName(), this.getPrice(), this.getCategory());
    }

}
