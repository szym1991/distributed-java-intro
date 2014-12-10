package pl.edu.amu.wmi.common;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Szymon
 */
public class Reporting {
    
    public static final Map<Integer, Double> products = new HashMap<>();

    public void updateReport(Integer id, double price) {
        System.out.println("Product was sold: Id = " + id + ", price = " + price);
        Double current = products.get(id);
        if(current != null)
            current += price;
        else
            current = price;
        products.put(id, current);
    }
}
