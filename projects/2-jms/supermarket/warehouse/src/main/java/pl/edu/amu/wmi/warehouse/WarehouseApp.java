package pl.edu.amu.wmi.warehouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import pl.edu.amu.wmi.common.Product;
import pl.edu.amu.wmi.common.Warehouse;
import pl.edu.amu.wmi.warehouse.service.ProductListService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Szymon
 */
public class WarehouseApp {

    public static final String EXIT = "exit";

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
        Warehouse warehouse = Warehouse.getInstance();
        ProductListService productListService = (ProductListService) context.getBean("productListService");
        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String in = "";
        while (!in.equalsIgnoreCase(EXIT)) {
            try {
                System.out.println("---------------\ntype: \n'send product list' to send full product list to POSs, \n'change price' to change product's price");
                in = bufferedReader.readLine();
                if ("send product list".equals(in)) {
                    productListService.sendProductList(warehouse.getProducts());
                }
                if ("change price".equals(in)) {
                    System.out.println("Products:");
                    for (Entry<Integer, Product> productEntry : warehouse.getProducts().entrySet()) {
                        System.out.println("id: " + productEntry.getValue().getId() 
                                + " name: " + productEntry.getValue().getName()
                                + " price: " + productEntry.getValue().getPrice());
                    }
                    System.out.println("\nChoose product ID to change price: ");
                    in = bufferedReader.readLine();
                    Product chosen = warehouse.getProducts().get(Integer.parseInt(in));
                    if (chosen != null) {
                        System.out.print("Type new price: ");
                        in = bufferedReader.readLine();
                        chosen.setPrice(Double.parseDouble(in));
                        productListService.changeProductPrice(chosen);
                        System.out.println("Price change completed");
                    } else {
                        System.out.println("Product with given ID not found");
                    }
                }
            } catch (IOException ex) {
                System.err.println("Wrong input given");
            }
            
        }
        
    }
}
