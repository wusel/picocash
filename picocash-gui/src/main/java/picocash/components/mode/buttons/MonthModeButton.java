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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import picocash.components.mode.Constraints;
import picocash.components.mode.buttons.item.MonthMenuItem;

/**
 *
 * @author wusel
 */
public class MonthModeButton extends ModeButton {

    private static final Log log = LogFactory.getLog(MonthModeButton.class);
    private List<MonthMenuItem> months = new ArrayList<MonthMenuItem>();
    private MonthMenuItem currentItem;

    public MonthModeButton() {
        init();
    }

    private void init() {
        for (int i = 0; i < 12; i++) {
            final MonthMenuItem monthMenuItem = new MonthMenuItem(i);
            monthMenuItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    setCurrentItem(monthMenuItem);
                }
            });
            months.add(monthMenuItem);
        }

        setCurrentItem(months.get(Calendar.getInstance().get(Calendar.MONTH)));
        setBackgroundPainter(new ModeBackgroundPainter());
    }

    public void setCurrentItem(MonthMenuItem currentItem) {
        this.currentItem = currentItem;
        setText(currentItem.getText());
        selectionChanged();
    }

    @Override
    public String getLayoutConstraints() {
        return "w 100lp:100lp:300lp";
    }

    @Override
    protected void fillPopupMenu(JPopupMenu popupMenu) {
        for (JMenuItem jMenuItem : months) {
            popupMenu.add(jMenuItem);
        }
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
        map.put(Constraints.MONTH, currentItem.getMonth());
        return map;
    }
}
