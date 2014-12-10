package pl.edu.amu.wmi.pointofsale;

import pl.edu.amu.wmi.common.PointOfSale;

/**
 *
 * @author Szymon
 */
public class PointOfSaleHolder {
    
    private PointOfSale pointOfSale;
    
    public PointOfSaleHolder() {
        this.pointOfSale = new PointOfSale();
    }
    public PointOfSale getPointOfSale() {
        return pointOfSale;
    }
    
    public void setPointOfSale(PointOfSale pointOfSale) {
        this.pointOfSale = pointOfSale;
    }
}
