package picocash.components.mode.buttons.item;

import javax.swing.JMenuItem;

public class YearMenuItem extends JMenuItem {

    private int year;

    public YearMenuItem(int year) {
        super();
        this.year = year;
    }

    @Override
    public String getText() {
        return year + "";
    }

    public int getYear() {
        return year;
    }


}
