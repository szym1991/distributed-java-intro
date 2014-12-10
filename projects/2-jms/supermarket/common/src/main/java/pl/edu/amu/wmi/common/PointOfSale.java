package pl.edu.amu.wmi.common;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Szymon
 */
public class PointOfSale {

    private Map<Integer, Product> products;

    public PointOfSale() {
        products = new HashMap<>();
    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, Product> products) {
        this.products = products;
    }

    public void initProducts(Map<Integer, Product> products) {
        this.setProducts(products);
    }

    public void changePrice(Integer id, double price) {
        if (!products.isEmpty()) {
            Product product = products.get(id);
            product.setPrice(price);
            products.put(id, product);
        }
    }
}
