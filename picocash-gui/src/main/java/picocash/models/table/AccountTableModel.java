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
package picocash.models.table;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import picocash.model.impl.Account;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdesktop.application.Application;

/**
 *
 * @author wusel
 */
public class AccountTableModel extends AbstractTableModel implements PropertyChangeListener {

    private static final Log log = LogFactory.getLog(AccountTableModel.class);
    private Vector<Account> accounts;
    private static final String[] labels = new String[]{
        Application.getInstance().getContext().getResourceMap(Account.class).getString("name.text", ""),
        Application.getInstance().getContext().getResourceMap(Account.class).getString("accountNumber.text", ""),
        Application.getInstance().getContext().getResourceMap(Account.class).getString("bank.text", ""),
        Application.getInstance().getContext().getResourceMap(Account.class).getString("balance.text", "")
    };

    public AccountTableModel(List<Account> accounts) {
        super();
        this.accounts = new Vector<Account>(accounts);
        for (Account account : accounts) {
            account.addPropertyChangeListener(this);
        }
    }

    public void addAccount(Account account) {
        if (this.accounts.add(account)) {
            account.addPropertyChangeListener(this);
            fireTableRowsInserted(this.accounts.size() - 1, this.accounts.size() - 1);
        }
    }

    public void removeAccount(Account account) {
        int index = this.accounts.indexOf(account);
        account.removePropertyChangeListener(this);
        if (this.accounts.remove(account)) {
            fireTableRowsDeleted(index, index);
        }
    }

    public Account getAccount(int selectedRow) {
        return accounts.get(selectedRow);
    }

    @Override
    public int getRowCount() {
        return this.accounts.size();
    }

    @Override
    public int getColumnCount() {
        return labels.length;
    }

    @Override
    public String getColumnName(int column) {
        return labels[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object erg = "";
        switch (columnIndex) {
            case 0:
                erg = accounts.get(rowIndex).getName();
                break;
            case 1:
                erg = accounts.get(rowIndex).getAccountNumber();
                break;
            case 2:
                erg = accounts.get(rowIndex).getBank();
                break;
            case 3:
                erg = accounts.get(rowIndex).getBalance();
                break;
            default:
                erg = null;
        }
        return erg;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        int index = accounts.indexOf((Account) evt.getSource());
        fireTableRowsUpdated(index, index);
    }

    public void clearTableModel() {
        this.accounts.clear();
        fireTableDataChanged();
    }
}
