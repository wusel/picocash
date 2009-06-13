/*
 * This file is part of mogo.
 * 
 * mogo is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * mogo is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with mogo.  If not, see <http://www.gnu.org/licenses/>.
 * and open the template in the editor.
 * 
 * Copyright 2008 Daniel Wasilew
 */
package picocash.database.connector;

import java.util.List;
import picocash.model.impl.Account;
import picocash.model.impl.Category;
import picocash.model.impl.Payee;
import picocash.model.impl.Transaction;

/**
 *
 * @author wusel
 */
public interface IPicocashPersistenceManager {

    public List<Account> getAllAccounts();

    public List<Transaction> getAllTransactions();

    public List<Transaction> getAllTransactionsForAccount(Account account);

    public List<Transaction> getAllTransactionsForCategory(Category category);

    public List<Transaction> getAllTransactionsForPayee(Payee payee);

    public List<Transaction> getAllPastTransactions();

    public List<Transaction> getAllPastTransactionsForAccount(Account account);

    public List<Transaction> getAllUpcomingTransactions();

    public List<Transaction> getAllUpcomingTransactionsForAccount(Account account);

    public List<Transaction> getAllTransactionsForAccount(Account account, long startDate, long endDate);

    public List<Payee> getAllPayees();

    public List<Category> getAllCategories();

    public List<Category> getAllCategoriesForRoot();

    public List<Category> getAllCategoriesForParent(Category parent);

    public void persist(Object object);

    public void delete(Object object);

    public void clear();

    public void edit(Object object);

    public void save();

    public void close();

    public void initDB() throws Exception;

    public void setDatabaseName(String string);
}
