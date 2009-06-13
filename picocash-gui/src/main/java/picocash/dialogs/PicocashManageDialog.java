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
package picocash.dialogs;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import picocash.components.NameAndIconCombobox;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdesktop.application.Action;

/**
 *
 * @author wusel
 */
public abstract class PicocashManageDialog extends DefaultPicocashDialog {

    private static final Log log = LogFactory.getLog(PicocashManageDialog.class);

    protected JTextField nameTF;

    protected NameAndIconCombobox iconsCB;

    public PicocashManageDialog(JFrame parent, boolean modal) {
        super(parent, modal);
        this.iconsCB = new NameAndIconCombobox();
    }

    public abstract JComponent getManagingComponent();

    @Override
    protected JComponent getComponentsToDisplay() {
        JPanel panel = new JPanel();
        FormLayout layout = new FormLayout("100dlu:grow,2dlu,30dlu,2dlu,30dlu",
                "p,2dlu,p,2dlu,p,2dlu,p,2dlu,p,2dlu,FILL:150dlu:grow,2dlu,p,2dlu,p");

        CellConstraints cc = new CellConstraints();

        panel.setLayout(layout);

        nameTF = new JTextField("");

        nameTF.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                add();
            }

        });
        
        panel.setLayout(layout);

        panel.add(nameTF, cc.xyw(1, 1, 3));
        panel.add(iconsCB, cc.xyw(1, 3, 3));
        panel.add(new JButton(getAction("add")), cc.xywh(5, 1, 1, 3));

        panel.add(new JSeparator(), cc.xyw(1, 5, 5));
        panel.add(new JButton(getAction("edit")), cc.xy(5, 7));
        panel.add(new JButton(getAction("remove")), cc.xy(5, 9));

        panel.add(getManagingComponent(), cc.xywh(1, 7, 3, 5));

        return panel;
    }

    @Action
    public void add() {
    }

    @Action
    public void edit() {
    }

    @Action
    public void remove() {
    }

}
