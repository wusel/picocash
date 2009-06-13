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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import picocash.components.NameAndIconCombobox;
import picocash.dialogs.DefaultPicocashDialog;
import picocash.model.impl.Account;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import picocash.model.impl.Money;

/**
 *
 * @author wusel
 */
public class ManageAccountDialog extends DefaultPicocashDialog {

    public static ManageAccountDialog createNewAccountDialog(JFrame parent, boolean modal) {
        return new ManageAccountDialog(parent, modal, null, true);
    }

    public static ManageAccountDialog createEditAccountDialog(JFrame parent, Account account, boolean modal) {
        return new ManageAccountDialog(parent, modal, account, false);
    }
    private static final Log log = LogFactory.getLog(ManageAccountDialog.class);
    private final boolean showStartbalance;
    private Account account;
    private JTextField nameTextField;
    private JTextField bankTextField;
    private JTextField accountNumberTextField;
    private NameAndIconCombobox iconComboBox;
    private JTextField startBalanceTextfield;
    private JTextField withdrawAtTextfield;

    private ManageAccountDialog(JFrame parent, boolean modal, Account account, boolean showStartBalance) {
        super(parent, modal);
        this.showStartbalance = showStartBalance;
        this.account = account;
        initComponents();
        initGui();
        pack();
        setLocationRelativeTo(parent);
    }

    @Override
    protected JComponent getComponentsToDisplay() {

        FormLayout layout = new FormLayout("p,2dlu,80dlu:g,2dlu", "p,2dlu,p,2dlu,p,2dlu,p,2dlu,p,2dlu,p");
        CellConstraints cc = new CellConstraints();

        JPanel panel = new JPanel(layout);

        panel.add(new JLabel(getText("accountDialog.label.name.text", "")), cc.xy(1, 1));
        panel.add(new JLabel(getText("accountDialog.label.icon.text", "")), cc.xy(1, 3));
        panel.add(new JLabel(getText("accountDialog.label.bank.text", "")), cc.xy(1, 5));
        panel.add(new JLabel(getText("accountDialog.label.accountNumber.text", "")), cc.xy(1, 7));

        panel.add(new JLabel(getText("accountDialog.label.withdrawAt.text", "")), cc.xy(1, 11));

        panel.add(nameTextField, cc.xy(3, 1));
        panel.add(iconComboBox, cc.xy(3, 3));
        panel.add(bankTextField, cc.xy(3, 5));
        panel.add(accountNumberTextField, cc.xy(3, 7));
        if (showStartbalance) {
            panel.add(new JLabel(getText("accountDialog.label.balance.text", "")), cc.xy(1, 9));
            panel.add(startBalanceTextfield, cc.xy(3, 9));
        }
        panel.add(withdrawAtTextfield, cc.xy(3, 11));

        if (!showStartbalance) {
            layout.removeRow(9);
        }
        return panel;
    }

    private void initComponents() {
        nameTextField = new JTextField();
        bankTextField = new JTextField();
        accountNumberTextField = new JTextField();

        iconComboBox = new NameAndIconCombobox();

        startBalanceTextfield = new JTextField();
        withdrawAtTextfield = new JTextField();

        if (this.account != null) {
            nameTextField.setText(this.account.getName());
            iconComboBox.setSelectedItem(account.getIcon());
            bankTextField.setText(this.account.getBank());
            accountNumberTextField.setText(this.account.getAccountNumber());
            iconComboBox.setSelectedItem(this.account.getIcon());
            startBalanceTextfield.setText(this.account.getStartbalance().toString());
            withdrawAtTextfield.setText(this.account.getWithDraw().toString());
        }
    }

    @Override
    public void ok() {
        if (this.account == null) {
            this.account = new Account();
        }
        this.account.setName(nameTextField.getText());
        this.account.setBank(bankTextField.getText());
        this.account.setAccountNumber(accountNumberTextField.getText());
        if (iconComboBox.getSelectedItem() != null) {
            this.account.setIcon(iconComboBox.getSelectedItem().toString());
        }
        this.account.setWithDraw(new Money(withdrawAtTextfield.getText()));
        this.account.setStartbalance(new Money(startBalanceTextfield.getText()));

        super.ok();
    }

    public Account getAccount() {
        return account;
    }
}
