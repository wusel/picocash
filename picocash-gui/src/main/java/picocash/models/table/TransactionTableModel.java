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

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import picocash.model.impl.Transaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdesktop.application.Application;

/**
 *
 * @author wusel
 */
public class TransactionTableModel extends AbstractTableModel {

    private static final Log log = LogFactory.getLog(TransactionTableModel.class);
    private List<Transaction> transactions;
    private static final String[] labels = new String[]{
        Application.getInstance().getContext().getResourceMap(Transaction.class).getString("date.text", ""),
        Application.getInstance().getContext().getResourceMap(Transaction.class).getString("fromAccount.text", ""),
        Application.getInstance().getContext().getResourceMap(Transaction.class).getString("toAccount.text", ""),
        Application.getInstance().getContext().getResourceMap(Transaction.class).getString("payee.text", ""),
        Application.getInstance().getContext().getResourceMap(Transaction.class).getString("category.text", ""),
        Application.getInstance().getContext().getResourceMap(Transaction.class).getString("amount.text", "")
    };

    public TransactionTableModel() {
        this.transactions = new ArrayList<Transaction>();
    }

    @Override
    public int getRowCount() {
        return transactions.size();
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
        switch (columnIndex) {
            case 0:
                return this.transactions.get(rowIndex).getTransactionDate();
            case 1:
                return this.transactions.get(rowIndex).getFromAccount();
            case 2:
                return this.transactions.get(rowIndex).getToAccount();
            case 3:
                return this.transactions.get(rowIndex).getPayee();
            case 4:
                return this.transactions.get(rowIndex).getCategory();
            case 5:
                return this.transactions.get(rowIndex).getAmount();
            default:
                throw new RuntimeException("unknown type");
        }
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = new ArrayList<Transaction>(transactions);
        fireTableDataChanged();
    }

    public void addTransaction(Transaction transaction) {
        if (transactions.add(transaction)) {
            fireTableRowsInserted(transactions.size(), transactions.size());
        } else {
            log.error("ooops cant insert the transaction");
        }
    }

    public void deleteTransaction(Transaction transaction) {
        int index = transactions.indexOf(transaction);
        if (this.transactions.remove(transaction)) {
            fireTableRowsDeleted(index, index);
        }
    }

    public void updateTransaction(Transaction transaction) {
        int index = transactions.indexOf(transaction);
        fireTableRowsUpdated(index, index);

    }

    public Transaction getTransaction(int index) {
        return transactions.get(index);
    }
}
