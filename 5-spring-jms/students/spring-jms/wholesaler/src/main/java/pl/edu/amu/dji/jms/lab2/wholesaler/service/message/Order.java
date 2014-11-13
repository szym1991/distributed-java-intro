/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.dji.jms.lab2.wholesaler.service.message;

/**
 *
 * @author s362601
 */
public class Order {
    private int quantity;
    private String retailerID;

    
    public Order(int quantity, String retailerID) {
        this.quantity = quantity;
        this.retailerID = retailerID;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRetailerID() {
        return retailerID;
    }

    public void setRetailerID(String retailerID) {
        this.retailerID = retailerID;
    }
    
    
}
