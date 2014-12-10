package pl.edu.amu.wmi.common;

import com.rits.cloning.Cloner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Szymon
 */
public class Warehouse {

    private Map<Integer, Product> products;
    private List<PointOfSale> pointsOfSale;
    private final Cloner cloner;

    private static volatile Warehouse instance = null;

    public static Warehouse getInstance() {
        if (instance == null) {

            if (instance == null) {
                instance = new Warehouse();
            }
        }
        return instance;
    }

    private Warehouse() {
        products = new HashMap<>();
        pointsOfSale = new ArrayList<>();
        cloner = new Cloner();

        products.put(1, new Product(1, "Bike", 399.79));
        products.put(2, new Product(2, "TV", 2599.53));
        products.put(3, new Product(3, "Car", 62000.00));
        products.put(4, new Product(4, "Laptop", 2999.99));
    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public void registerPointOfSale(PointOfSale pointOfSale) {
        pointsOfSale.add(pointOfSale);
    }

    public void sendProducts() {
        for (PointOfSale pointOfSale : pointsOfSale) {
            pointOfSale.initProducts(cloner.deepClone(products));
        }
    }

    public void changePrice(Integer productId, Double price) {
        Product product = products.get(productId);
        if (product != null) {
            product.setPrice(price);
            products.put(productId, product);

            for (PointOfSale pointOfSale : pointsOfSale) {
                pointOfSale.changePrice(productId, price);
            }
        } else {
            System.out.println("There is no product with ID = " + productId);
        }
    }
}
