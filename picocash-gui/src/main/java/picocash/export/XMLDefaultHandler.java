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
package picocash.export;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import picocash.model.impl.Account;
import picocash.model.impl.Category;
import picocash.model.impl.Payee;
import picocash.model.impl.Transaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import picocash.model.impl.Money;

/**
 *
 * @author wusel
 */
public class XMLDefaultHandler extends DefaultHandler {

    private static final Log log = LogFactory.getLog(XMLDefaultHandler.class);
    private Map<String, Account> accounts = new HashMap<String, Account>();
    private Map<String, Category> categories = new HashMap<String, Category>();
    private Map<String, Payee> payees = new HashMap<String, Payee>();
    private List<Transaction> transactions = new ArrayList<Transaction>();

    @Override
    public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void endElement(String arg0, String arg1, String arg2) throws SAXException {
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals(ImportExportConstants.ELEMENT_ACCOUNT)) {
            final Account account = createAccount(attributes);
            accounts.put(String.valueOf(account.getId()), account);
        } else if (qName.equals(ImportExportConstants.ELEMENT_PAYEE)) {
            final Payee payee = createPayee(attributes);
            payees.put(String.valueOf(payee.getId()), payee);
        } else if (qName.equals(ImportExportConstants.ELEMENT_CATEGORY)) {
            final Category category = createCategory(attributes);
            categories.put(String.valueOf(category.getId()), category);
        } else if (qName.equals(ImportExportConstants.ELEMENT_TRANSACTION)) {
            transactions.add(createTransaction(attributes));
        } else {
            log.debug("URI [" + uri + "] localName [" + localName + "] qName [ " + qName + "] Attributes [" + attributes.getLength() + "]");
        }
    }

    private Account createAccount(Attributes attributes) {
        Account account = new Account();
        for (int i = 0; i < attributes.getLength(); i++) {
            String name = attributes.getLocalName(i);
            if (name.equals(ImportExportConstants.NAME_ICON_ATTRIBUTES.ATTRIBUTE_ICON)) {
                account.setIcon(attributes.getValue(i));
            } else if (name.equals(ImportExportConstants.NAME_ICON_ATTRIBUTES.ATTRIBUTE_ID)) {
                account.setId(Long.parseLong(attributes.getValue(i)));
            } else if (name.equals(ImportExportConstants.NAME_ICON_ATTRIBUTES.ATTRIBUTE_NAME)) {
                account.setName(attributes.getValue(i));
            } else if (name.equals(ImportExportConstants.ACCOUNT_ATTRIBUTES.ATTRIBUTE_ACCOUNT_NUMBER)) {
                account.setAccountNumber(attributes.getValue(i));
            } else if (name.equals(ImportExportConstants.ACCOUNT_ATTRIBUTES.ATTRIBUTE_BALANCE)) {
            } else if (name.equals(ImportExportConstants.ACCOUNT_ATTRIBUTES.ATTRIBUTE_BANK)) {
                account.setBank(attributes.getValue(i));
            } else if (name.equals(ImportExportConstants.ACCOUNT_ATTRIBUTES.ATTRIBUTE_WITHDRAW)) {
                account.setWithDraw(new Money(attributes.getValue(i)));
            } else if (name.equals(ImportExportConstants.ACCOUNT_ATTRIBUTES.ATTRIBUTE_START_BALANCE)) {
                account.setStartbalance(new Money(attributes.getValue(i)));
            } else {
                throw new RuntimeException("unknown Attribute on category element");
            }
        }

        return account;
    }

    private Payee createPayee(Attributes attributes) {
        Payee payee = new Payee();
        for (int i = 0; i < attributes.getLength(); i++) {
            String name = attributes.getLocalName(i);
            if (name.equals(ImportExportConstants.NAME_ICON_ATTRIBUTES.ATTRIBUTE_ICON)) {
                payee.setIcon(attributes.getValue(i));
            } else if (name.equals(ImportExportConstants.NAME_ICON_ATTRIBUTES.ATTRIBUTE_ID)) {
                payee.setId(Long.parseLong(attributes.getValue(i)));
            } else if (name.equals(ImportExportConstants.NAME_ICON_ATTRIBUTES.ATTRIBUTE_NAME)) {
                payee.setName(attributes.getValue(i));
            } else {
                throw new RuntimeException("unknown Attribute on payee element");
            }
        }
        return payee;
    }

    private Category createCategory(Attributes attributes) {
        Category category = new Category();
        for (int i = 0; i < attributes.getLength(); i++) {
            String name = attributes.getLocalName(i);
            if (name.equals(ImportExportConstants.NAME_ICON_ATTRIBUTES.ATTRIBUTE_ICON)) {
                category.setIcon(attributes.getValue(i));
            } else if (name.equals(ImportExportConstants.NAME_ICON_ATTRIBUTES.ATTRIBUTE_ID)) {
                category.setId(Long.parseLong(attributes.getValue(i)));
            } else if (name.equals(ImportExportConstants.NAME_ICON_ATTRIBUTES.ATTRIBUTE_NAME)) {
                category.setName(attributes.getValue(i));
            } else {
                throw new RuntimeException("unknown Attribute on category element");
            }
        }
        return category;
    }

    private Transaction createTransaction(Attributes attributes) {
        Transaction transaction = new Transaction();
        for (int i = 0; i < attributes.getLength(); i++) {
            String name = attributes.getLocalName(i);
            if (name.equals(ImportExportConstants.NAME_ICON_ATTRIBUTES.ATTRIBUTE_ICON)) {
                transaction.setIcon(attributes.getValue(i));
            } else if (name.equals(ImportExportConstants.NAME_ICON_ATTRIBUTES.ATTRIBUTE_ID)) {
                transaction.setId(Long.parseLong(attributes.getValue(i)));
            } else if (name.equals(ImportExportConstants.NAME_ICON_ATTRIBUTES.ATTRIBUTE_NAME)) {
                transaction.setName(attributes.getValue(i));
            } else if (name.equals(ImportExportConstants.TRANSACTION_ATTRIBUTES.ATTRIBUTE_AMOUNT)) {
                transaction.setAmount(new Money(attributes.getValue(i)));
            } else if (name.equals(ImportExportConstants.TRANSACTION_ATTRIBUTES.ATTRIBUTE_CATEGORY)) {
                transaction.setCategory(this.categories.get(attributes.getValue(i)));
            } else if (name.equals(ImportExportConstants.TRANSACTION_ATTRIBUTES.ATTRIBUTE_COMMENT)) {
                transaction.setComment(attributes.getValue(i));
            } else if (name.equals(ImportExportConstants.TRANSACTION_ATTRIBUTES.ATTRIBUTE_DATE)) {
                transaction.setTransactionDate(Long.parseLong(attributes.getValue(i)));
            } else if (name.equals(ImportExportConstants.TRANSACTION_ATTRIBUTES.ATTRIBUTE_FROM_ACCOUNT)) {
                transaction.setFromAccount(this.accounts.get(attributes.getValue(i)));
            } else if (name.equals(ImportExportConstants.TRANSACTION_ATTRIBUTES.ATTRIBUTE_PAYEE)) {
                transaction.setPayee(this.payees.get(attributes.getValue(i)));
            } else if (name.equals(ImportExportConstants.TRANSACTION_ATTRIBUTES.ATTRIBUTE_TO_ACCOUNT)) {
                transaction.setToAccount(this.accounts.get(attributes.getValue(i)));
            } else {
                throw new RuntimeException("unknown Attribute on category element");
            }
        }
        return transaction;
    }

    public List<Account> getAccounts() {
        return new ArrayList<Account>(accounts.values());
    }

    public List<Category> getCategories() {
        return new ArrayList<Category>(categories.values());
    }

    public List<Payee> getPayees() {
        return new ArrayList<Payee>(payees.values());
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
