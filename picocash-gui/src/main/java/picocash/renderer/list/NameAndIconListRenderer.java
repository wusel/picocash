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
 * Copyright 2008 Daniel Wasilew
 */
package picocash.renderer.list;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import picocash.handler.PicocashIcons;
import picocash.model.NameAndIconable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author wusel
 */
public class NameAndIconListRenderer extends DefaultListCellRenderer {

    private static final Log log = LogFactory.getLog(NameAndIconListRenderer.class);

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof NameAndIconable) {
            NameAndIconable nameAndIconable = (NameAndIconable) value;
            label.setText(nameAndIconable.getName());
            label.setIcon(PicocashIcons.getIcon(nameAndIconable.getIcon()));
        } else {
            label.setIcon(PicocashIcons.getIcon(value + ""));
        }
        return label;
    }
}
