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

import picocash.dialogs.*;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import picocash.model.impl.Payee;
import picocash.model.helper.IDGenerator;
import picocash.models.list.PayeeListModel;
import picocash.renderer.list.NameAndIconListRenderer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author wusel
 */
public class ManagePayeesDialog extends PicocashManageDialog {

    private static final Log log = LogFactory.getLog(ManagePayeesDialog.class);

    private Vector<Payee> payees;

    private PayeeListModel listModel;

    private NameAndIconListRenderer renderer;

    private JList payeeList;

    public ManagePayeesDialog(JFrame parent, Vector<Payee> payees, boolean modal) {
        super(parent, modal);
        this.payees = payees;
        this.listModel = new PayeeListModel(payees);
        this.renderer = new NameAndIconListRenderer();

        this.payeeList = new JList(listModel);
        this.payeeList.setCellRenderer(renderer);
        this.payeeList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        initGui();
        pack();
        setLocationRelativeTo(parent);
    }

    @Override
    public void add() {

        if (!nameTF.getText().trim().equals("")) {
            if (checkNewName(nameTF.getText())) {
                Payee payee = new Payee();
                payee.setName(nameTF.getText());

                payee.setId(IDGenerator.createUniqueIDForCollection(payees));
                if (iconsCB.getSelectedItem() != null) {
                    payee.setIcon(iconsCB.getSelectedItem().toString());
                }
                listModel.addPayee(payee);
                nameTF.setText(null);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("name [" + nameTF.getText() + "] already taken");
                }
            }

        }
    }

    @Override
    public void edit() {
        if (payeeList.getSelectedIndex() != -1) {
            Payee payee = (Payee) payeeList.getSelectedValue();
            ChangeNameAndIconDialog dialog = new ChangeNameAndIconDialog(this, true, payee);
            dialog.setVisible(true);
            if (dialog.getStatus() == DialogReturnStatus.OK) {
                payee.setName(dialog.getNewName());
                payee.setIcon(dialog.getNewIcon());
                listModel.setPayee(payee);
            }
        }
    }

    @Override
    public void remove() {
        if (payeeList.getSelectedIndex() != -1) {
            Payee payee = (Payee) payeeList.getSelectedValue();
            listModel.deletePayee(payee);
        }
    }

    @Override
    public JComponent getManagingComponent() {
        return new JScrollPane(payeeList);
    }

    private boolean checkNewName(String newName) {
        boolean erg = true;
        for (Payee payee : payees) {
            if (payee.getName().equals(newName)) {
                erg = false;
                break;
            }
        }

        return erg;
    }

    @Override
    public void cancel() {
        this.payees = null;
        super.cancel();
    }

}
