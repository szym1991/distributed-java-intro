package pl.edu.amu.wmi.charityfleamarket.roles;

import static pl.edu.amu.wmi.charityfleamarket.roles.Items.values;

/**
 *
 * @author Szymon
 */
public enum Items {

    żelazko,
    piłka,
    pralka,
    lodówka,
    samochód,
    rower,
    komputer;

    private static int counter = 0;
    
    public static String getRandomName() {
        return values()[(int) (Math.random() * values().length)].toString() + counter++;
    }
}
