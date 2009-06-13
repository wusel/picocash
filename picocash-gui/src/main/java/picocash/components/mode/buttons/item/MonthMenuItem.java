package picocash.components.mode.buttons.item;

import java.text.DateFormatSymbols;
import javax.swing.JMenuItem;

public class MonthMenuItem extends JMenuItem {

    private final int month;

    public MonthMenuItem(int month) {
        super();
        this.month = month;
    }

    @Override
    public String getText() {
        return DateFormatSymbols.getInstance().getMonths()[month];
    }

    public int getMonth() {
        return month;
    }


}
