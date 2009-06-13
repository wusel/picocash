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
package picocash.models.list;

import java.util.Vector;
import javax.swing.DefaultListModel;
import picocash.model.impl.Payee;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author wusel
 */
public class PayeeListModel extends DefaultListModel {

    private static final Log log = LogFactory.getLog(PayeeListModel.class);

    private Vector<Payee> payees;

    public PayeeListModel(Vector<Payee> payees) {
        this.payees = payees;
    }

    public void addPayee(Payee payee) {
        if (payees.add(payee)) {
            fireIntervalAdded(this, payees.size() - 1, payees.size());
        }
    }

    public void deletePayee(Payee payee) {
        int index = payees.indexOf(payee);
        if (payees.remove(payee)) {
            fireIntervalRemoved(this, index, index);
        }
    }

    @Override
    public Object getElementAt(int index) {
        return payees.get(index);
    }

    @Override
    public int getSize() {
        return payees.size();
    }

    public void setPayee(Payee payee) {
        int index = payees.indexOf(payee);
        fireContentsChanged(this, index, index);
    }

}
