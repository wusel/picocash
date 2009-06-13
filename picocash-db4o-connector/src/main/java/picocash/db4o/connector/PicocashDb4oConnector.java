/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package picocash.db4o.connector;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.Configuration;
import com.db4o.query.Predicate;
import picocash.database.connector.IPicocashPersistenceManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import picocash.model.helper.NameAndIconComparator;
import picocash.model.impl.Account;
import picocash.model.impl.Category;
import picocash.model.impl.Payee;
import picocash.model.impl.Transaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import picocash.model.helper.TransactionDateComparator;

/**
 *
 * @author wusel
 */
public class PicocashDb4oConnector implements IPicocashPersistenceManager {

    private static final Log log = LogFactory.getLog(PicocashDb4oConnector.class);
    private String databasName;
    private ObjectContainer database;

    @Override
    public List<Account> getAllAccounts() {
        final List<Account> result = new ArrayList<Account>(this.database.query(Account.class));
        Collections.sort(result, new NameAndIconComparator());
        return result;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return this.database.query(Transaction.class);
    }

    @Override
    public List<Transaction> getAllTransactionsForAccount(final Account account) {
        final List<Transaction> result = new ArrayList<Transaction>(this.database.query(new Predicate<Transaction>() {

            @Override
            public boolean match(Transaction candidate) {
                return (account.equals(candidate.getFromAccount()) || account.equals(candidate.getToAccount()));
            }
        }));
        Collections.sort(result, new TransactionDateComparator());
        return result;
    }

    @Override
    public List<Transaction> getAllPastTransactions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Transaction> getAllPastTransactionsForAccount(final Account account) {
        return this.database.query(new Predicate<Transaction>() {

            @Override
            public boolean match(Transaction candidate) {
                return (account.equals(candidate.getFromAccount()) || account.equals(candidate.getToAccount())) && candidate.getTransactionDate() <= System.currentTimeMillis();
            }
        });
    }

    @Override
    public List<Transaction> getAllUpcomingTransactions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Transaction> getAllUpcomingTransactionsForAccount(final Account account) {
        return this.database.query(new Predicate<Transaction>() {

            @Override
            public boolean match(Transaction candidate) {
                return (account.equals(candidate.getFromAccount()) || account.equals(candidate.getToAccount())) && candidate.getTransactionDate() > System.currentTimeMillis();
            }
        });
    }

    @Override
    public List<Payee> getAllPayees() {
        final List<Payee> result = new ArrayList<Payee>(this.database.query(Payee.class));
        Collections.sort(result, new NameAndIconComparator());
        return result;
    }

    @Override
    public List<Category> getAllCategories() {
        final List<Category> result = new ArrayList<Category>(this.database.query(Category.class));
        Collections.sort(result, new NameAndIconComparator());
        return result;
    }

    @Override
    public List<Transaction> getAllTransactionsForAccount(final Account account, final long startDate, final long endDate) {
        final ObjectSet<Transaction> tmp = this.database.query(new Predicate<Transaction>() {

            @Override
            public boolean match(Transaction arg0) {
                return (account.equals(arg0.getToAccount()) || account.equals(arg0.getFromAccount())) && arg0.getTransactionDate() >= startDate && arg0.getTransactionDate() <= endDate;
            }
        });

        return tmp;
    }

    @Override
    public List<Category> getAllCategoriesForRoot() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Category> getAllCategoriesForParent(Category parent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void persist(Object object) {
        checkDatabase();
        this.database.store(object);
    }

    @Override
    public void delete(Object object) {
        checkDatabase();
        this.database.delete(object);
    }

    @Override
    public void edit(Object object) {
        this.database.activate(object, 3);
        this.database.store(object);
    }

    @Override
    public void save() {
        checkDatabase();
        this.database.commit();
    }

    @Override
    public void close() {
        checkDatabase();
        this.database.rollback();
        this.database.close();
    }

    @Override
    public void initDB() throws Exception {
        if (this.databasName != null) {
            Configuration conf = Db4o.newConfiguration();
            conf.activationDepth(3);
            conf.updateDepth(1);
            this.database = Db4o.openFile(conf, databasName);
        }
    }

    @Override
    public void setDatabaseName(String string) {
        this.databasName = string;
    }

    private void checkDatabase() {
        if (this.database == null && this.database.ext().isClosed()) {
            try {
                initDB();
            } catch (Exception ex) {
                log.fatal("Exception while restarting database:", ex);
            }
        }
    }

    @Override
    public void clear() {
        checkDatabase();
        this.database.ext().backup(databasName + "_backup");
        this.database.close();
        if (!new File(databasName).delete()) {
            log.error("cant delete old database!");
        }
        try {
            initDB();
        } catch (Exception ex) {
            log.fatal("Exception while restarting database:", ex);
        }
    }

    @Override
    public List<Transaction> getAllTransactionsForCategory(final Category category) {
        return this.database.query(new Predicate<Transaction>() {

            @Override
            public boolean match(Transaction candidate) {
                return (category.equals(candidate.getCategory()));
            }
        });
    }

    @Override
    public List<Transaction> getAllTransactionsForPayee(final Payee payee) {
        return this.database.query(new Predicate<Transaction>() {

            @Override
            public boolean match(Transaction candidate) {
                return (payee.equals(candidate.getPayee()));
            }
        });
    }
}
