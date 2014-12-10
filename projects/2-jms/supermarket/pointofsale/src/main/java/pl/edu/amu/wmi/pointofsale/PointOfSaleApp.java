package pl.edu.amu.wmi.pointofsale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.edu.amu.wmi.common.Product;
import pl.edu.amu.wmi.pointofsale.service.ProductSaleService;

/**
 *
 * @author Szymon
 */
public class PointOfSaleApp {
    
    public static final String EXIT = "EXIT";
    
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
        PointOfSaleHolder posHolder = (PointOfSaleHolder) context.getBean("pointOfSaleHolder");
        ProductSaleService saleService = (ProductSaleService) context.getBean("productSaleService");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String in = "";
        while (!in.equalsIgnoreCase(EXIT)) {
            System.out.println("---------------\ntype:\n 'show products' to show available products, \n 'sell product' to sell product");
            try {
                in = bufferedReader.readLine();
                if ("show products".equals(in)) {
                    for (Map.Entry<Integer, Product> productEntry : 
                            posHolder.getPointOfSale().getProducts().entrySet()) {
                        System.out.println("id: " + productEntry.getValue().getId() 
                                + " name: " + productEntry.getValue().getName()
                                + " price: " + productEntry.getValue().getPrice());
                    }
                }
                if ("sell product".equals(in)) {
                    System.out.println("Choose product ID to sell");
                    in = bufferedReader.readLine();
                    Product productToSell = posHolder.getPointOfSale().getProducts().get(Integer.parseInt(in));
                    if (productToSell != null) {
                        saleService.sellProduct(productToSell);
                        System.out.println("Product was sold");
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }
    }

}