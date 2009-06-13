package picocash.db4o.connector;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import picocash.model.impl.Account;
import picocash.model.impl.Money;
import picocash.model.impl.Transaction;
import static org.junit.Assert.*;

/**
 *
 * @author wusel
 */
public class PicocashDb4oConnectorTest {

    private final Money startBalance = new Money(100, 00);
    private final String accountNumber = "accountNumber";
    private PicocashDb4oConnector db4oConnector;
    private final String testBank = "testBank";
    private final String testComment = "testComment";

    public PicocashDb4oConnectorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.db4oConnector = new PicocashDb4oConnector();
        this.db4oConnector.setDatabaseName("unit.test.db");
        this.db4oConnector.initDB();

    }

    @After
    public void tearDown() {
        this.db4oConnector.close();
        new File("unit.test.db").delete();
    }

    private void closeAndReopenDB() {
        try {
            this.db4oConnector.save();
            this.db4oConnector.close();
            this.db4oConnector.initDB();
        } catch (Exception ex) {
            Logger.getLogger(PicocashDb4oConnectorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Account buildAccount() {
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBank(testBank);
        account.setComment(testComment);
        account.setStartbalance(startBalance);
        return account;
    }

    private Transaction buildTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAmount(new Money(50, 00));
        return transaction;
    }

    @Test
    public void saveAccount() {
        Account account = buildAccount();

        this.db4oConnector.persist(account);
        closeAndReopenDB();
        Account candidate = this.db4oConnector.getAllAccounts().get(0);

        assertEquals(1, this.db4oConnector.getAllAccounts().size());

        assertEquals(account, candidate);
    }

    @Test
    public void deleteAccount() {

        Account account = buildAccount();

        this.db4oConnector.persist(account);
        closeAndReopenDB();
        Account candidate = this.db4oConnector.getAllAccounts().get(0);

        assertEquals(1, this.db4oConnector.getAllAccounts().size());

        assertEquals(account, candidate);

        this.db4oConnector.delete(candidate);
        closeAndReopenDB();
        assertEquals(0, this.db4oConnector.getAllAccounts().size());
    }

    @Test
    public void editAccount() {

        Account account = buildAccount();

        this.db4oConnector.persist(account);

        closeAndReopenDB();

        Account candidate = this.db4oConnector.getAllAccounts().get(0);

        assertEquals(1, this.db4oConnector.getAllAccounts().size());

        assertEquals(account, candidate);
        candidate.setAccountNumber(accountNumber + "TEST");
        assertEquals(accountNumber + "TEST", candidate.getAccountNumber());

        this.db4oConnector.edit(candidate);
        closeAndReopenDB();
        candidate = this.db4oConnector.getAllAccounts().get(0);

        assertEquals(1, this.db4oConnector.getAllAccounts().size());
        assertEquals(accountNumber + "TEST", candidate.getAccountNumber());

    }

    @Test
    public void saveTransaction() {
        Transaction transaction = buildTransaction();

        this.db4oConnector.persist(transaction);

        closeAndReopenDB();

        assertEquals(1, this.db4oConnector.getAllTransactions().size());
        Transaction candidate = this.db4oConnector.getAllTransactions().get(0);
        assertEquals(transaction.getAmount(), candidate.getAmount());

    }

    @Test
    public void deleteTransaction() {
        Transaction transaction = buildTransaction();

        this.db4oConnector.persist(transaction);

        closeAndReopenDB();

        assertEquals(1, this.db4oConnector.getAllTransactions().size());
        Transaction candidate = this.db4oConnector.getAllTransactions().get(0);
        assertEquals(transaction.getAmount(), candidate.getAmount());

        this.db4oConnector.delete(candidate);


        closeAndReopenDB();

        assertEquals(0, this.db4oConnector.getAllTransactions().size());
    }

    @Test
    public void editTransaction() {

        Transaction transaction = buildTransaction();

        this.db4oConnector.persist(transaction);

        closeAndReopenDB();

        assertEquals(1, this.db4oConnector.getAllTransactions().size());
        Transaction candidate = this.db4oConnector.getAllTransactions().get(0);
        assertEquals(transaction.getAmount(), candidate.getAmount());
        candidate.setAmount(new Money(200, 00));
        this.db4oConnector.edit(candidate);

        closeAndReopenDB();
        candidate = this.db4oConnector.getAllTransactions().get(0);
        assertEquals(new Money(200, 00), candidate.getAmount());

    }

    @Test
    public void saveTransactionOnAccount() {
        Account accountFrom = buildAccount();
        accountFrom.setStartbalance(new Money(200, 00));

        Account accountTo = buildAccount();
        accountTo.setStartbalance(new Money(100, 00));

        Transaction transaction = buildTransaction();
        transaction.setFromAccount(accountFrom);
        transaction.setToAccount(accountTo);
        transaction.commit();
        transaction.setAmount(new Money(100, 00));

        assertEquals(new Money(100, 00), accountFrom.getBalance());
        assertEquals(new Money(200, 00), accountTo.getBalance());

        this.db4oConnector.persist(transaction);
        closeAndReopenDB();
        assertEquals(1, this.db4oConnector.getAllTransactions().size());
        assertEquals(2, this.db4oConnector.getAllAccounts().size());

        Account fromCandidate = this.db4oConnector.getAllTransactions().get(0).getFromAccount();
        Account toCandidate = this.db4oConnector.getAllTransactions().get(0).getToAccount();

        assertEquals(accountTo, toCandidate);
        assertEquals(accountFrom, fromCandidate);
    }

    @Test
    public void deleteTransactionOnAccount() {

        Account accountFrom = buildAccount();
        accountFrom.setStartbalance(new Money(200, 00));

        Account accountTo = buildAccount();
        accountTo.setStartbalance(new Money(100, 00));

        Transaction transaction = buildTransaction();
        transaction.setFromAccount(accountFrom);
        transaction.setToAccount(accountTo);
        transaction.commit();
        transaction.setAmount(new Money(100, 00));

        assertEquals(new Money(100, 00), accountFrom.getBalance());
        assertEquals(new Money(200, 00), accountTo.getBalance());

        this.db4oConnector.persist(transaction);

        closeAndReopenDB();


        assertEquals(1, this.db4oConnector.getAllTransactions().size());
        assertEquals(2, this.db4oConnector.getAllAccounts().size());

        this.db4oConnector.delete(this.db4oConnector.getAllTransactions().get(0));

        closeAndReopenDB();

        assertEquals(0, this.db4oConnector.getAllTransactions().size());
        assertEquals(2, this.db4oConnector.getAllAccounts().size());
    }

    @Test
    public void editTransactionOnAccount() {
        Account accountFrom = buildAccount();
        accountFrom.setStartbalance(new Money(200, 00));

        Account accountTo = buildAccount();
        accountTo.setStartbalance(new Money(100, 00));

        Transaction transaction = buildTransaction();
        transaction.setFromAccount(accountFrom);
        transaction.setToAccount(accountTo);
        transaction.commit();
        transaction.setAmount(new Money(100, 00));

        assertEquals(new Money(100, 00), accountFrom.getBalance());
        assertEquals(new Money(200, 00), accountTo.getBalance());

        this.db4oConnector.persist(transaction);
        closeAndReopenDB();
        assertEquals(1, this.db4oConnector.getAllTransactions().size());
        assertEquals(2, this.db4oConnector.getAllAccounts().size());

        Transaction candidate = this.db4oConnector.getAllTransactions().get(0);
        candidate.setAmount(new Money(200,00));
        Account fromCandidate = candidate.getFromAccount();
        Account toCandidate = candidate.getToAccount();

        assertEquals(new Money(0, 00), fromCandidate.getBalance());
        assertEquals(new Money(300, 00), toCandidate.getBalance());

        this.db4oConnector.edit(candidate);

        closeAndReopenDB();

        assertEquals(1, this.db4oConnector.getAllTransactions().size());
        assertEquals(2, this.db4oConnector.getAllAccounts().size());

        candidate = this.db4oConnector.getAllTransactions().get(0);
        candidate.setAmount(new Money(200,00));
        fromCandidate = candidate.getFromAccount();
        toCandidate = candidate.getToAccount();

        assertEquals(new Money(0, 00), fromCandidate.getBalance());
        assertEquals(new Money(300, 00), toCandidate.getBalance());

    }
}