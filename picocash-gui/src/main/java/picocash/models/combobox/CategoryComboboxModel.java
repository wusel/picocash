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
 *     Copyright Daniel Wasilew, 01.07.2008
 */
package picocash.models.combobox;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import picocash.model.impl.Category;
import picocash.services.Services;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author wusel
 * 
 */
public class CategoryComboboxModel extends DefaultComboBoxModel {

    private static final Log log = LogFactory.getLog(CategoryComboboxModel.class);

    private List<Category> categories;

    /**
     * 
     */
    public void rebuildModel() {
        this.categories = Services.getSelectedPersistenceMan().getAllCategories();
    }

    @Override
    public Object getElementAt(int index) {
        return categories != null ? categories.get(index) : null;
    }

    @Override
    public int getSize() {
        return categories != null ? categories.size() : 0;
    }

}
