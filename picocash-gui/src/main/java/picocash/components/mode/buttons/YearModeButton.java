/*
 * This file is part of picocash.
 * 
 * picocash is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * picocash is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with picocash.  If not, see <http://www.gnu.org/licenses/>.
 * and open the template in the editor.
 * 
 * Copyright 2009  Daniel Wasilew
 */
package picocash.components.mode.buttons;

import java.util.Map;
import picocash.components.mode.buttons.item.YearMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.swing.JPopupMenu;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import picocash.components.mode.Constraints;

/**
 *
 * @author wusel
 */
public class YearModeButton extends ModeButton {

    private static final Log log = LogFactory.getLog(YearModeButton.class);
    private List<YearMenuItem> items = new ArrayList<YearMenuItem>();
    private YearMenuItem currentItem;
    private long start;
    private long end;

    public YearModeButton() {
        this(System.currentTimeMillis(), System.currentTimeMillis());
    }

    public YearModeButton(long startDate, long endDate) {
        this.start = startDate;
        this.end = endDate;
        init();
    }

    @Override
    protected void fillPopupMenu(JPopupMenu popupMenu) {
        for (YearMenuItem yearItem : items) {
            popupMenu.add(yearItem);
        }
    }

    public void setCurrentItem(YearMenuItem currentItem) {
        this.currentItem = currentItem;
        setText(currentItem.getText());
        selectionChanged();
    }

    public void setStartAndEnd(long start, long end) {
        this.start = start;
        this.end = end;
        init();
    }

    @Override
    public String getLayoutConstraints() {
        return "w 100lp:100lp:300lp";
    }

    private void init() {
        Calendar startDate = Calendar.getInstance();
        startDate.setTimeInMillis(start);
        Calendar endDate = Calendar.getInstance();
        endDate.setTimeInMillis(end);

        this.items.clear();

        int startYear = startDate.get(Calendar.YEAR);
        int endYear = endDate.get(Calendar.YEAR);
        log.trace("creating Items from [" + startYear + "] to [" + endYear + "]");
        for (int i = startYear; i < endYear + 1; i++) {
            final YearMenuItem yearItem = new YearMenuItem(i);
            yearItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    setCurrentItem((YearMenuItem) arg0.getSource());
                }
            });
            this.items.add(yearItem);
            if (i == Calendar.getInstance().get(Calendar.YEAR)) {
                setCurrentItem(yearItem);
            }
        }
        setBackgroundPainter(new ModeBackgroundPainter());
    }

    @Override
    protected void selectionChanged() {
        for (ModeButtonSelectionListener modeButtonSelectionListener : listeners) {
            modeButtonSelectionListener.selectionChanged(currentItem);
        }
    }

    @Override
    public Map<Constraints, Object> getConstraint() {
        Map<Constraints, Object> map = new HashMap<Constraints, Object>();
        map.put(Constraints.YEAR, currentItem.getYear());
        return map;
    }
}
