package pl.edu.amu.wmi.charityfleamarket.roles;

/**
 *
 * @author Szymon <szymon.apolinarski@gmail.com>
 */
public class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
