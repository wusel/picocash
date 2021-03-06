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
package picocash.models.combobox;

import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import picocash.handler.PicocashIcons;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author wusel
 */
public class IconComboBoxModel extends DefaultComboBoxModel {

    private static final Log log = LogFactory.getLog(IconComboBoxModel.class);
    private Vector<String> icons = new Vector<String>();

    public IconComboBoxModel() {
        for (String string : PicocashIcons.getIcons()) {
            icons.add(string);
        }
    }

    @Override
    public int getSize() {
        return icons.size();
    }

    @Override
    public Object getElementAt(int index) {
        return icons.get(index);
    }
}
