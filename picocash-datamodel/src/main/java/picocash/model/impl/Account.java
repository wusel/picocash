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
package picocash.model.impl;

import java.beans.PropertyChangeSupport;
import picocash.model.NameAndIconable;
import picocash.model.TransactionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author wusel
 */
public final class Account extends PropertyChangeSupport implements NameAndIconable, TransactionListener {

    private static final Log log = LogFactory.getLog(Account.class);
    private String name;
    private String accountNumber;
    private String bank;
    private String comment;
    private Money withDraw = new Money(0, 0);
    private Money startbalance = new Money(0, 0);
    private Money balance = new Money(0, 0);
    private String icon;
    private long id;

    public Account() {
        super("Account");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getIcon() {
        return this.icon;
    }

    @Override
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Money getWithDraw() {
        return withDraw;
    }

    public void setWithDraw(Money withDraw) {
        this.withDraw = withDraw;
    }

    public Money getStartbalance() {
        return startbalance;
    }

    public void setStartbalance(Money startbalance) {
        Money oldStartBalance = this.startbalance;
        this.startbalance = startbalance;


        Money diff = this.startbalance.substract(oldStartBalance);
        this.balance = getBalance().add(diff);

        firePropertyChange("startBalanceChanged", oldStartBalance, this.startbalance);
        firePropertyChange("balanceChanged", null, this.balance);

    }

    public Money getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if ((this.accountNumber == null) ? (other.accountNumber != null) : !this.accountNumber.equals(other.accountNumber)) {
            return false;
        }
        if ((this.bank == null) ? (other.bank != null) : !this.bank.equals(other.bank)) {
            return false;
        }
        if ((this.comment == null) ? (other.comment != null) : !this.comment.equals(other.comment)) {
            return false;
        }
        if (this.withDraw != other.withDraw && (this.withDraw == null || !this.withDraw.equals(other.withDraw))) {
            return false;
        }
        if (this.startbalance != other.startbalance && (this.startbalance == null || !this.startbalance.equals(other.startbalance))) {
            return false;
        }
        if (this.balance != other.balance && (this.balance == null || !this.balance.equals(other.balance))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.accountNumber != null ? this.accountNumber.hashCode() : 0);
        hash = 97 * hash + (this.bank != null ? this.bank.hashCode() : 0);
        hash = 97 * hash + (this.comment != null ? this.comment.hashCode() : 0);
        hash = 97 * hash + (this.withDraw != null ? this.withDraw.hashCode() : 0);
        hash = 97 * hash + (this.startbalance != null ? this.startbalance.hashCode() : 0);
        hash = 97 * hash + (this.balance != null ? this.balance.hashCode() : 0);
        return hash;
    }

    @Override
    public void transactionCommited(Transaction transaction) {
        if (this.equals(transaction.getFromAccount())) {
            this.balance = this.balance.substract(transaction.getAmount());
        } else {
            this.balance = this.balance.add(transaction.getAmount());
        }
        firePropertyChange("balanceChanged", null, this.balance);
    }

    @Override
    public void removedFromTransaction(Transaction transaction) {
        if (transaction.getStatus() == TransactionStatus.COMMITED) {
            if (this.equals(transaction.getFromAccount())) {
                this.balance = this.balance.add(transaction.getAmount());
            } else {
                this.balance = this.balance.substract(transaction.getAmount());
            }
            firePropertyChange("balanceChanged", null, this.balance);
        }
    }

    @Override
    public void addedToTransaction(Transaction transaction) {
        if (transaction.getStatus() == TransactionStatus.COMMITED) {
            transactionCommited(transaction);
        }
    }

    @Override
    public void amountChanged(Money diff) {
        this.balance = this.balance.substract(diff);
        firePropertyChange("balanceChanged", null, this.balance);
    }
}
