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
package picocash.models.list;

import java.util.Vector;
import javax.swing.DefaultListModel;
import picocash.model.impl.Category;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author wusel
 */
public class CategoryListModel extends DefaultListModel {

    private static final Log log = LogFactory.getLog(CategoryListModel.class);
    private Vector<Category> categories;

    public CategoryListModel(Vector<Category> categorys) {
        this.categories = categorys;
    }

    public void addCategory(Category category) {
        if (categories.add(category)) {
            fireIntervalAdded(this, categories.size() - 1, categories.size());
        }
    }

    public void deleteCategory(Category category) {
        int index = categories.indexOf(category);
        if (categories.remove(category)) {
            fireIntervalRemoved(this, index, index);
        }
    }

    @Override
    public Object getElementAt(int index) {
        return categories.get(index);
    }

    @Override
    public int getSize() {
        return categories.size();
    }

    public void setCategory(Category category) {
        int index = categories.indexOf(category);
        fireContentsChanged(this, index, index);
    }
}
