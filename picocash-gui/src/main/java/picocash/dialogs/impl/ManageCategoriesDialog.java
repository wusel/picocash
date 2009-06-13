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
package picocash.dialogs.impl;

import javax.swing.JComponent;
import picocash.dialogs.*;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import picocash.model.impl.Category;
import picocash.model.helper.IDGenerator;
import picocash.models.list.CategoryListModel;
import picocash.renderer.list.NameAndIconListRenderer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author wusel
 */
public class ManageCategoriesDialog extends PicocashManageDialog {

    private static final Log log = LogFactory.getLog(ManageCategoriesDialog.class);

    private Vector<Category> categories;

    private CategoryListModel categoryListModel;

    private JList categoryList;

    private NameAndIconListRenderer renderer;

    public ManageCategoriesDialog(JFrame parent, Vector<Category> categories, boolean modal) {
        super(parent, modal);
        this.categories = categories;
        this.categoryListModel = new CategoryListModel(categories);
        this.renderer = new NameAndIconListRenderer();
        this.categoryList = new JList(categoryListModel);
        this.categoryList.setCellRenderer(renderer);
        this.categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        initGui();
        pack();
        setLocationRelativeTo(parent);
    }

    @Override
    public void ok() {
        super.ok();
    }

    @Override
    public void cancel() {
        this.categories = null;
        super.cancel();
    }

    @Override
    public void add() {
        if (!nameTF.getText().trim().equals("")) {

            Category category = new Category();
            if (checkNewCategoryName()) {
                log.debug("adding category [" + nameTF.getText() + "] to copy of categories");
                category.setName(nameTF.getText());
                if (iconsCB.getSelectedItem() != null) {
                    category.setIcon(iconsCB.getSelectedItem().toString());
                }
                category.setId(IDGenerator.createUniqueIDForCollection(categories));
                categoryListModel.addCategory(category);
                //categoryTree.repaint();
                nameTF.setText(null);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("name [" + nameTF.getText() + "] already taken!");
                }
            }

        }
    }

    @Override
    public void remove() {
        if (categoryList.getSelectedIndex() != -1) {

            Category cat = (Category) categoryList.getSelectedValue();

            boolean hasChilds = false;
            for (Category category : categories) {
                if (cat.equals(category)) {
                    hasChilds = true;
                    break;
                }
            }
            if (hasChilds) {
            }
            categoryListModel.deleteCategory(cat);
        }

    }

    @Override
    public void edit() {
        if (categoryList.getSelectedIndex() != -1) {

            Category cat = (Category) categoryList.getSelectedValue();
            ChangeNameAndIconDialog dialog = new ChangeNameAndIconDialog(this, true, cat);
            dialog.setVisible(true);

            if (dialog.getStatus() == DialogReturnStatus.OK) {
                cat.setName(dialog.getNewName());
                cat.setIcon(dialog.getNewIcon());
                categoryListModel.setCategory(cat);
            }

        }
    }

    /**
     * @return the categories
     */
    public Vector<Category> getCategories() {
        return categories;
    }

    private boolean checkNewCategoryName() {
        String newName = nameTF.getText();
        boolean contains = false;
        for (Category category : categories) {
            if (category.getName().equals(newName)) {
                contains = true;
                break;
            }
        }
        return !contains;
    }

    @Override
    public JComponent getManagingComponent() {
        return new JScrollPane(categoryList);
    }

}
