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

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import picocash.dialogs.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import picocash.model.NameAndIconable;
import picocash.models.combobox.IconComboBoxModel;
import picocash.renderer.list.NameAndIconListRenderer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author wusel
 */
public class ChangeNameAndIconDialog extends DefaultPicocashDialog {

    private static final Log log = LogFactory.getLog(ChangeNameAndIconDialog.class);
    private DialogReturnStatus status;
    private JTextField nameTF;
    private JComboBox iconComboBox;

    /**
     *
     * @param parent
     * @param modal
     */
    public ChangeNameAndIconDialog(JDialog parent, boolean modal, NameAndIconable object) {
        super(parent, modal);

        this.nameTF = new JTextField(object.getName());

        this.iconComboBox = new JComboBox(new IconComboBoxModel());
        this.iconComboBox.setRenderer(new NameAndIconListRenderer());
        this.iconComboBox.setSelectedItem(object.getIcon());

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                status = DialogReturnStatus.CANCEL;
            }
        });
        initGui();
        pack();
        setLocationRelativeTo(parent);
    }

    public String getNewName() {
        return nameTF.getText();
    }

    public String getNewIcon() {
        return (String) iconComboBox.getSelectedItem();
    }

    @Override
    protected JComponent getComponentsToDisplay() {
        JPanel panel = new JPanel();
        FormLayout layout = new FormLayout("p,2dlu,50dlu:g", "p,2dlu,p");
        panel.setLayout(layout);

        CellConstraints cc = new CellConstraints();

        panel.add(new JLabel("name"), cc.xy(1, 1));
        panel.add(new JLabel("icon"), cc.xy(1, 3));

        this.nameTF.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ok();
            }
        });

        panel.add(nameTF, cc.xy(3, 1));
        panel.add(iconComboBox, cc.xy(3, 3));
        return panel;
    }
}
