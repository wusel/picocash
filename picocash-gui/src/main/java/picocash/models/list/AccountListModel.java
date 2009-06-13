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

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import picocash.model.impl.Account;

/**
 *
 * @author wusel
 */
public class AccountListModel extends DefaultListModel {

    private final List<Account> accounts = new ArrayList<Account>();

    public AccountListModel(List<Account> accounts) {
        for (Account account : accounts) {
            insertAccount(account);
        }
    }


    public void insertAccount(Account account) {
        if (this.accounts.add(account)) {
            fireIntervalAdded(this, accounts.size() - 1, accounts.size());
        }
    }

    public void setAccount(Account account) {
        int index = accounts.indexOf(account);
        if (index != -1) {
            this.accounts.set(index, account);
            fireContentsChanged(this, index, index);
        } else {
            throw new RuntimeException("account not found in listModel [" + account + "]");
        }
    }

    public void removeAccount(Account account) {
        int index = this.accounts.indexOf(account);
        if (this.accounts.remove(account)) {
            fireIntervalRemoved(this, index, index);
        }
    }

    @Override
    public Account getElementAt(int index) {
        return this.accounts.get(index);
    }

    @Override
    public int getSize() {
        return this.accounts.size();
    }

    @Override
    public void clear() {
        this.accounts.clear();
        super.clear();
    }


}
