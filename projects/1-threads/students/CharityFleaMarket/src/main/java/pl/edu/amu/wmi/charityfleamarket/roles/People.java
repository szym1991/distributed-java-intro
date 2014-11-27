package pl.edu.amu.wmi.charityfleamarket.roles;

/**
 *
 * @author Szymon
 */
public enum People {

    Andrzej,
    Stefan,
    Zdzisław,
    Zygmunt,
    Jerzy,
    Władysław;

    private static int counter = 0;
    
    public static String getRandomName() {
        return values()[(int) (Math.random() * values().length)].toString() + " " + counter++;
    }
}
