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

import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import picocash.model.impl.Account;
import picocash.model.impl.Category;
import picocash.model.impl.Payee;
import picocash.model.impl.Transaction;

/**
 *
 * @author wusel
 */
public class ModelImporter {

    private final String fileName;
    private List<Account> accounts;
    private List<Category> categories;
    private List<Transaction> transactions;
    private List<Payee> payees;

    public ModelImporter(String fileName) {
        this.fileName = fileName;
    }

    public void read() throws Exception{
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();
        final XMLDefaultHandler xMLDefaultHandler = new XMLDefaultHandler();

        saxParser.parse(fileName,xMLDefaultHandler);
        this.accounts = xMLDefaultHandler.getAccounts();
        this.payees = xMLDefaultHandler.getPayees();
        this.categories = xMLDefaultHandler.getCategories();
        this.transactions = xMLDefaultHandler.getTransactions();
        
    }

    public List<Account> getAllAccounts() {
        return this.accounts;
    }

    public List<Category> getAllCategories() {
        return this.categories;
    }

    public List<Payee> getAllPayees() {
        return this.payees;
    }

    public List<Transaction> getAllTransactions() {
        return this.transactions;
    }
}
