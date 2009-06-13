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
 * Copyright 2009  Daniel Wasilew
 */


package picocash.models.list;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import picocash.model.impl.Transaction;

/**
 *
 * @author wusel
 */
public class TransactionListModel extends DefaultListModel{
    private static final Log log = LogFactoryImpl.getLog(TransactionListModel.class);
    
    private List<Transaction> transactions = new ArrayList<Transaction>();

    public TransactionListModel() {
    }

    public void addTransaction(Transaction transaction) {
        if (transactions.add(transaction)) {
            fireIntervalAdded(this, transactions.size() - 1, transactions.size());
        }
    }

    public void deleteTransaction(Transaction transaction) {
        int index = transactions.indexOf(transaction);
        if (transactions.remove(transaction)) {
            fireIntervalRemoved(this, index, index);
        }
    }

    @Override
    public Object getElementAt(int index) {
        return transactions.get(index);
    }

    @Override
    public int getSize() {
        return transactions.size();
    }

    public void setTransaction(Transaction transaction) {
        int index = transactions.indexOf(transaction);
        fireContentsChanged(this, index, index);
    }

    @Override
    public void clear() {
        int index = this.transactions.size();
        this.transactions.clear();
        fireIntervalRemoved(this, 0, index);
    }


}
