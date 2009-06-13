/*
 *     This file is part of picocash.
 * 
 *     picocash is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     picocash is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with picocash.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *     Copyright Daniel Wasilew, 24.06.2008
 */
package picocash.renderer.tree;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import picocash.model.impl.Category;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author wusel
 * 
 */
@Deprecated
public class CategoryTreeRenderer extends DefaultTreeCellRenderer {

    private static final Log log = LogFactory.getLog(CategoryTreeRenderer.class);

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        JLabel comp = (JLabel) super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        if (value instanceof Category) {
            comp.setText(((Category) value).getName());
        }
        return comp;

    }

}
