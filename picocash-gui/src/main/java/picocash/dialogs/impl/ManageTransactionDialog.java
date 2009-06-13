/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package picocash.dialogs.impl;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.util.Date;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import picocash.components.NameAndIconCombobox;
import picocash.dialogs.DefaultPicocashDialog;
import picocash.model.impl.Account;
import picocash.model.impl.Category;
import picocash.model.impl.Payee;
import picocash.model.impl.Transaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdesktop.swingx.JXDatePicker;
import picocash.model.impl.Money;

/**
 *
 * @author wusel
 */
public class ManageTransactionDialog extends DefaultPicocashDialog {

    public static final ManageTransactionDialog createNewTransactionDialog(JFrame parent, List<Category> categories, List<Payee> payees, List<Account> accounts) {
        return new ManageTransactionDialog(parent, true, null, categories, payees, accounts);
    }

    public static final ManageTransactionDialog createEditTransactionDialog(JFrame parent, Transaction transaction, List<Category> categories, List<Payee> payees, List<Account> accounts) {
        return new ManageTransactionDialog(parent, true, transaction, categories, payees, accounts);
    }
    
    private static final Log log = LogFactory.getLog(ManageTransactionDialog.class);
    private Transaction transaction;
    private JTextField descriptionTextField;
    private NameAndIconCombobox categoryCombobox;
    private NameAndIconCombobox payeeCombobox;
    private NameAndIconCombobox fromAccountCombobox;
    private NameAndIconCombobox toAccountCombobox;
    private JXDatePicker dateTextField;
    private JTextField amountTextfield;
    private List<Category> categories;
    private List<Account> accounts;
    private List<Payee> payees;

    private ManageTransactionDialog(JFrame parent, boolean modal, Transaction transaction, List<Category> categories, List<Payee> payees, List<Account> accounts) {
        super(parent, modal);
        this.transaction = transaction;
        this.categories = categories;
        this.payees = payees;
        this.accounts = accounts;
        initComponents();
        initGui();
        pack();
        setLocationRelativeTo(parent);
    }

    @Override
    protected JComponent getComponentsToDisplay() {
        JPanel panel = new JPanel();
        FormLayout layout = new FormLayout("p,2dlu,p:g", "p,2dlu,p,2dlu,p,2dlu,p,2dlu,p,2dlu,p,2dlu,p");
        CellConstraints cc = new CellConstraints();
        panel.setLayout(layout);

        panel.add(new JLabel(getText("transaction.label.description.text", "")), cc.xy(1, 1));
        panel.add(this.descriptionTextField, cc.xy(3, 1));
        panel.add(new JLabel(getText("transaction.label.date.text", "")), cc.xy(1, 3));
        panel.add(this.dateTextField, cc.xy(3, 3));
        panel.add(new JLabel(getText("transaction.label.fromAccount.text", "")), cc.xy(1, 5));
        panel.add(this.fromAccountCombobox, cc.xy(3, 5));
        panel.add(new JLabel(getText("transaction.label.toAccount.text", "")), cc.xy(1, 7));
        panel.add(this.toAccountCombobox, cc.xy(3, 7));
        panel.add(new JLabel(getText("transaction.label.category.text", "")), cc.xy(1, 9));
        panel.add(this.categoryCombobox, cc.xy(3, 9));
        panel.add(new JLabel(getText("transaction.label.payee.text", "")), cc.xy(1, 11));
        panel.add(this.payeeCombobox, cc.xy(3, 11));
        panel.add(new JLabel(getText("transaction.label.amount.text", "")), cc.xy(1, 13));
        panel.add(this.amountTextfield, cc.xy(3, 13));

        return panel;

    }

    private void initComponents() {
        this.descriptionTextField = new JTextField();
        this.categoryCombobox = new NameAndIconCombobox(this.categories);
        this.payeeCombobox = new NameAndIconCombobox(this.payees);
        this.fromAccountCombobox = new NameAndIconCombobox(this.accounts);
        this.toAccountCombobox = new NameAndIconCombobox(this.accounts);
        this.dateTextField = new JXDatePicker();
        this.amountTextfield = new JTextField();

        if (this.transaction != null) {
            this.descriptionTextField.setText(this.transaction.getComment());
            this.categoryCombobox.setSelectedItem(this.transaction.getCategory());
            this.payeeCombobox.setSelectedItem(this.transaction.getPayee());
            this.fromAccountCombobox.setSelectedItem(this.transaction.getFromAccount());
            this.toAccountCombobox.setSelectedItem(this.transaction.getToAccount());
            this.dateTextField.setDate(new Date(this.transaction.getTransactionDate()));
            this.amountTextfield.setText(this.transaction.getAmount().toString());
        }
    }

    @Override
    public void ok() {
        if (this.transaction == null) {
            this.transaction = new Transaction();
        }


        this.transaction.setComment(descriptionTextField.getText());
        this.transaction.setAmount(new Money(amountTextfield.getText()));
        this.transaction.setCategory((Category) categoryCombobox.getSelectedItem());
        this.transaction.setTransactionDate(dateTextField.getDate().getTime());
        this.transaction.setPayee((Payee) payeeCombobox.getSelectedItem());

        if(fromAccountCombobox.getSelectedItem()!=null)this.transaction.setFromAccount((Account) fromAccountCombobox.getSelectedItem());
        if(toAccountCombobox.getSelectedItem()!=null)this.transaction.setToAccount((Account) toAccountCombobox.getSelectedItem());

        super.ok();
    }

    @Override
    public void cancel() {
        super.cancel();
    }

    public Transaction getTransaction() {
        return transaction;
    }
}
