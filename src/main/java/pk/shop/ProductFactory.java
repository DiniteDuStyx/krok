package pk.shop;

public class ProductFactory {

    public Product createProduct(String name) {
        double price = 0;
        switch(name) {
            case "telefon":
                price = 1000;
                break;
            case "budzik":
                price = 80;
                break;
            case "laptop":
                price = 3000;
                break;
            case "myszka":
                price = 120;
                break;
            case "klawoatura":
                price = 220;
                break;
            case "plytaCD":
                price = 2;
                break;
            case "piseak":
                price = 5;
                break;
            case "szmatka":
                price = 3;
                break;
        }
        if(price == 0) {
            return null;
        }
        return new Product(name, price);
    };
}
