package picocash.model.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wusel
 */
public class AccountTest {

    public AccountTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreation() {
        Account account = new Account();
        final Money startBalance = new Money(100, 23);
        account.setStartbalance(startBalance);
        account.setAccountNumber("123456");
        account.setBank("testBank");
        account.setComment("no comment");

        assertEquals(startBalance, account.getStartbalance());
        assertEquals("123456", account.getAccountNumber());
        assertEquals("testBank", account.getBank());
        assertEquals("no comment", account.getComment());
        assertEquals(startBalance, account.getBalance());
    }

    @Test
    public void testSetStartBalance() {
        Account account = new Account();
        final Money startBalance = new Money(100, 23);
        final Money startBalance2 = new Money(100);
        final Money startBalance3 = new Money(0, 0);
        account.setStartbalance(startBalance);
        account.setAccountNumber("123456");
        account.setBank("testBank");
        account.setComment("no comment");

        assertEquals(startBalance, account.getBalance());
        account.setStartbalance(startBalance2);
        assertEquals(startBalance2, account.getBalance());
        account.setStartbalance(startBalance3);
        assertEquals(startBalance3, account.getBalance());
    }

    @Test
    public void addTransactions() {
        Account accountTo = new Account();
        final Money startBalance = new Money(100, 23);
        accountTo.setStartbalance(startBalance);
        accountTo.setAccountNumber("123456");
        accountTo.setBank("testBank");
        accountTo.setComment("no comment");


        Account accountFrom = new Account();
        final Money startBalance2 = new Money(100, 00);
        accountFrom.setStartbalance(startBalance2);

        Account accountFrom2 = new Account();
        final Money startBalance3 = new Money(50, 00);
        accountFrom2.setStartbalance(startBalance3);

        assertEquals(startBalance, accountTo.getBalance());
        assertEquals(startBalance2, accountFrom.getBalance());

        Transaction transactionOne = new Transaction();
        transactionOne.setAmount(new Money(100, 00));
        transactionOne.setFromAccount(accountFrom);
        transactionOne.setToAccount(accountTo);

        assertEquals(startBalance, accountTo.getBalance());
        assertEquals(startBalance2, accountFrom.getBalance());

        transactionOne.commit();

        assertEquals(new Money(0, 0), accountFrom.getBalance());
        assertEquals(new Money(200, 23), accountTo.getBalance());

        transactionOne.setFromAccount(accountFrom2);

        assertEquals(new Money(100, 00), accountFrom.getBalance());
        assertEquals(new Money(200, 23), accountTo.getBalance());
        assertEquals(new Money(-50, 0), accountFrom2.getBalance());
    }

    @Test
    public void deleteTransactions() {
        Account accountTo = new Account();
        final Money startBalance = new Money(100, 23);
        accountTo.setStartbalance(startBalance);
        accountTo.setAccountNumber("123456");
        accountTo.setBank("testBank");
        accountTo.setComment("no comment");


        Account accountFrom = new Account();
        final Money startBalance2 = new Money(100, 00);
        accountFrom.setStartbalance(startBalance2);

        assertEquals(startBalance, accountTo.getBalance());
        assertEquals(startBalance2, accountFrom.getBalance());

        Transaction transactionOne = new Transaction();
        transactionOne.setAmount(new Money(100, 00));
        transactionOne.setFromAccount(accountFrom);
        transactionOne.setToAccount(accountTo);

        transactionOne.commit();

        assertEquals(new Money(0, 0), accountFrom.getBalance());
        assertEquals(new Money(200, 23), accountTo.getBalance());

        transactionOne.delete();

        assertEquals(startBalance, accountTo.getBalance());
        assertEquals(startBalance2, accountFrom.getBalance());
    }

    @Test
    public void changeTransactions() {
        Account accountTo = new Account();
        final Money startBalance = new Money(100, 23);
        accountTo.setStartbalance(startBalance);
        accountTo.setAccountNumber("123456");
        accountTo.setBank("testBank");
        accountTo.setComment("no comment");

        Account accountFrom = new Account();
        final Money startBalance2 = new Money(100, 00);
        accountFrom.setStartbalance(startBalance2);

        assertEquals(startBalance, accountTo.getBalance());
        assertEquals(startBalance2, accountFrom.getBalance());

        Transaction transactionOne = new Transaction();
        transactionOne.setAmount(new Money(50, 00));
        transactionOne.setFromAccount(accountFrom);
        transactionOne.setToAccount(accountTo);

        assertEquals(startBalance, accountTo.getBalance());
        assertEquals(startBalance2, accountFrom.getBalance());

        transactionOne.commit();

        assertEquals(new Money(50, 0), accountFrom.getBalance());
        assertEquals(new Money(150, 23), accountTo.getBalance());

        transactionOne.setAmount(new Money(100, 00));
        
        assertEquals(new Money(0, 0), accountFrom.getBalance());
        assertEquals(new Money(200, 23), accountTo.getBalance());
        
        transactionOne.setAmount(new Money(200, 00));

        assertEquals(new Money(-100, 0), accountFrom.getBalance());
        assertEquals(new Money(300, 23), accountTo.getBalance());
    }

    @Test
    public void testEquals(){
        Account accountOne = new Account();
        accountOne.setBank("Test");
        accountOne.setAccountNumber("TestNumber");
        accountOne.setComment("dshdgahj");

        Account accountTwo = new Account();
        accountTwo.setBank("Test");
        accountTwo.setAccountNumber("TestNumber");
        accountTwo.setComment("dsd");

        assertFalse(accountOne.equals(accountTwo));
        accountTwo.setComment("dshdgahj");
        assertTrue(accountOne.equals(accountTwo));
    }
}