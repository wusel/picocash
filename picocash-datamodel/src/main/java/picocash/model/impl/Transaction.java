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

import picocash.model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author wusel
 */
public class Transaction implements NameAndIconable {

    private static final Log log = LogFactory.getLog(Transaction.class);
    private TransactionType type;
    private TransactionStatus status;
    private Money amount;
    private Category category;
    private long transactionDate;
    private String comment;
    private Account fromAccount;
    private Account toAccount;
    private Payee payee;
    private String name;
    private String icon;
    private long id;

    public Transaction() {
    }

    @Override
    public String getName() {
        return comment;
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

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money newValue) {
        Money oldValue = this.amount;
        fireAmountChanged(oldValue, newValue);
        this.amount = newValue;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(long transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account account) {
        if (this.fromAccount != null) {
            this.fromAccount.removedFromTransaction(this);
        }
        this.fromAccount = account;
        this.fromAccount.addedToTransaction(this);
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        if (this.toAccount != null) {
            this.toAccount.removedFromTransaction(this);
        }
        this.toAccount = toAccount;
        this.toAccount.addedToTransaction(this);
    }

    public Payee getPayee() {
        return payee;
    }

    public void setPayee(Payee payee) {
        this.payee = payee;
    }

    public void commit() {
        if (this.status == TransactionStatus.COMMITED) {
            throw new RuntimeException("transaction already commited!");
        }
        this.status = TransactionStatus.COMMITED;
        this.toAccount.transactionCommited(this);
        this.fromAccount.transactionCommited(this);
    }

    public void delete() {
        if (this.toAccount != null) {
            this.toAccount.removedFromTransaction(this);
        }

        if (this.fromAccount != null) {
            this.fromAccount.removedFromTransaction(this);
        }

    }

    private void fireAmountChanged(Money oldValue, Money newValue) {

        if (this.status == TransactionStatus.COMMITED) {
            if (this.toAccount != null) {
                this.toAccount.amountChanged(newValue.substract(oldValue).multiply(-1));
            }

            if (this.fromAccount != null) {
                this.fromAccount.amountChanged(newValue.substract(oldValue));
            }
        }
    }


}
