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
package picocash;

import picocash.components.mode.ModePanelSelectionListener;
import picocash.components.mode.ModePanelSelectionContainer;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EventObject;
import picocash.database.connector.IPicocashPersistenceManager;
import java.util.List;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import picocash.dialogs.impl.ManageCategoriesDialog;
import picocash.dialogs.DialogReturnStatus;
import picocash.dialogs.impl.ManageAccountDialog;
import picocash.dialogs.impl.ManagePayeesDialog;
import picocash.export.ModelExporter;
import picocash.export.ModelImporter;
import picocash.handler.PicocashIcons;
import picocash.model.helper.IDGenerator;
import picocash.model.impl.Account;
import picocash.model.impl.Category;
import picocash.model.impl.Payee;
import picocash.model.impl.Transaction;
import picocash.components.mode.Constraints;
import picocash.services.Services;
import picocash.services.ServiceDiscover;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jdesktop.application.Action;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.swingx.JXMultiSplitPane;
import org.jdesktop.swingx.JXPanel;
import picocash.components.FooterPanel;
import picocash.components.HeaderPanel;
import picocash.components.mode.ModePanel;
import picocash.components.panel.statistic.StatisticPanel;
import picocash.components.AccountPanel;
import picocash.components.panel.account.FutureAccountPanel;
import picocash.components.panel.account.PastAccountPanel;
import picocash.components.panel.account.StandardAccountPanel;
import picocash.dialogs.impl.ManageTransactionDialog;
import picocash.export.BackupFileFilter;

/**
 *
 * @author wusel
 */
public class Picocash extends SingleFrameApplication implements ModePanelSelectionListener {

    private static final Log log = LogFactory.getLog(Picocash.class);
    private JXPanel accountContainer;
    private boolean changesAvailable = false;
    private boolean accountSelected = false;
    private boolean accountsAvailable = false;
    private FooterPanel footerPanel;
    private ModePanel modePanel;
    private HeaderPanel headerPanel;
    private StatisticPanel statisticPanel;
    private boolean transactionSelected = false;
    private int dX;
    private int dY;
    private long lastClickOn;
    private static final long DOUBLE_CLICK_TIME = 250;
    private JXPanel mainPanel;
    private AccountPanel accountPanel;
    private JSplitPane splitPane;

    @Override
    protected void initialize(String[] arg0) {
        ServiceDiscover.getAll(IPicocashPersistenceManager.class);
        Services.setSelectedPersistenceMan(ServiceDiscover.getSingleton(IPicocashPersistenceManager.class));

        Services.getSelectedPersistenceMan().setDatabaseName("test.mmoc");
        try {
            Services.getSelectedPersistenceMan().initDB();
        } catch (Exception ex) {
            log.fatal("Exception while restarting database:", ex);
            exit();
        }

        PicocashIcons.init();

        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        addExitListener(new ExitListener() {

            @Override
            public boolean canExit(EventObject event) {
                if (changesAvailable) {
                    int retValue = JOptionPane.showConfirmDialog(getMainFrame(), "Das sollte der Title sein", "und die Message", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (retValue == JOptionPane.OK_OPTION) {
                        save();
                    }
                    return retValue == JOptionPane.OK_OPTION || retValue == JOptionPane.CANCEL_OPTION;
                }
                return true;
            }

            @Override
            public void willExit(EventObject event) {
                Services.getSelectedPersistenceMan().close();
            }
        });
        super.initialize(arg0);
    }

    @Override
    protected void startup() {

        FrameView view = getMainView();
        view.getFrame().setUndecorated(true);
        final JPanel mainFrame = initMainFrame();

        view.setComponent(mainFrame);
        show(view);
        this.splitPane.setDividerLocation(0.7d);

    }

    @Override
    protected void ready() {
        super.ready();

        this.modePanel.setPersistenceManager(Services.getSelectedPersistenceMan());
    }

    private List<Transaction> getTransactionsForSelectedMonth(Account account, int year, int month) {
        log.debug("selected [" + account + "] on [" + year + "/" + month + "]");
        Calendar date = Calendar.getInstance();
        date.setLenient(false);
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, month);
        date.set(Calendar.DAY_OF_MONTH, date.getActualMinimum(Calendar.DAY_OF_MONTH));
        date.set(Calendar.HOUR_OF_DAY, date.getActualMinimum(Calendar.HOUR_OF_DAY));
        date.set(Calendar.MINUTE, date.getActualMinimum(Calendar.MINUTE));
        date.set(Calendar.SECOND, date.getActualMinimum(Calendar.SECOND));
        date.set(Calendar.MILLISECOND, date.getActualMinimum(Calendar.MILLISECOND));
        long startDate = date.getTimeInMillis();
        if (log.isTraceEnabled()) {
            log.trace("startDate [" + date.getTime() + "]");
        }
        date.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DAY_OF_MONTH));
        date.set(Calendar.HOUR_OF_DAY, date.getActualMaximum(Calendar.HOUR_OF_DAY));
        date.set(Calendar.MINUTE, date.getActualMaximum(Calendar.MINUTE));
        date.set(Calendar.SECOND, date.getActualMaximum(Calendar.SECOND));
        date.set(Calendar.MILLISECOND, date.getActualMaximum(Calendar.MILLISECOND));
        long endDate = date.getTimeInMillis();
        if (log.isTraceEnabled()) {
            log.trace("endDate [" + date.getTime() + "]");
        }
        final List<Transaction> transactions = Services.getSelectedPersistenceMan().getAllTransactionsForAccount(account, startDate, endDate);
        if (log.isTraceEnabled()) {
            final List<Transaction> allTransactionsForAccount = Services.getSelectedPersistenceMan().getAllTransactionsForAccount(account);
            for (Transaction transaction : allTransactionsForAccount) {
                final long transactionDate = transaction.getTransactionDate();
                Calendar test = Calendar.getInstance();
                test.setTimeInMillis(transactionDate);
                log.trace(test.getTime());
            }
            log.trace("current available transactions [" + transactions.size() + "]");
        }
        return transactions;
    }

    private JPanel initMainFrame() {
        this.accountPanel = new StandardAccountPanel();
        mainPanel = new JXPanel(new MigLayout("fill, insets 0 0 0 0", "[grow]", "[40!] 0 [31!] 0 [grow] 0 [20!]"));
        headerPanel = new HeaderPanel();
        modePanel = new ModePanel();
        accountContainer = new JXPanel(new MigLayout("fill, insets 0 0 0 0"));
        statisticPanel = new StatisticPanel();
        footerPanel = new FooterPanel();
        headerPanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent arg0) {
                Point clickPoint = new Point(arg0.getPoint());
                SwingUtilities.convertPointToScreen(clickPoint, getMainFrame());
                dX = clickPoint.x - getMainFrame().getX();
                dY = clickPoint.y - getMainFrame().getY();
            }

            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (System.currentTimeMillis() - lastClickOn < DOUBLE_CLICK_TIME) {
                    maximize();
                }
                lastClickOn = System.currentTimeMillis();
            }
        });

        headerPanel.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent arg0) {
                Point dragPoint = new Point(arg0.getPoint());
                SwingUtilities.convertPointToScreen(dragPoint, getMainFrame());
                getMainFrame().setLocation(dragPoint.x - dX, dragPoint.y - dY);
            }
        });

        modePanel.addModePanelSelectionListener(this);

        mainPanel.add(headerPanel, "aligny top, growx, h 40!, wrap");
        mainPanel.add(modePanel, "aligny top, growx, h 31!, wrap");

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, accountContainer, statisticPanel);
        splitPane.setDividerLocation(0.7d);
        JXPanel tmp = new JXPanel(new MigLayout("fill, insets 0 0 0 0"));
        tmp.add(splitPane, "grow");
        mainPanel.add(tmp, "growx, growy, wrap");

        mainPanel.add(footerPanel, "aligny bottom, growx, h 20!");


        return mainPanel;
    }

    @Action(enabledProperty = "changesAvailable")
    public void save() {
        Services.getSelectedPersistenceMan().save();
        setChangesAvailable(false);
    }

    @Action
    public void quit() {
        exit();
    }

    @Action
    public void undo() {
    }

    @Action
    public void redo() {
    }

    @Action
    public void newAccount() {
        ManageAccountDialog dialog = ManageAccountDialog.createNewAccountDialog(getMainFrame(), true);
        dialog.setVisible(true);
        if (dialog.getStatus() == DialogReturnStatus.OK) {
            log.debug("ManageAccountDialog [OK]");
            Account account = dialog.getAccount();
            account.setId(IDGenerator.createUniqueIDForCollection(Services.getSelectedPersistenceMan().getAllAccounts()));
            Services.getSelectedPersistenceMan().persist(account);
            setChangesAvailable(true);
        } else {
            log.debug("ManageAccountDialog [CANCEL]");
        }
    }

    @Action(enabledProperty = "accountSelected")
    public void editAccount() {
    }

    @Action(enabledProperty = "accountSelected")
    public void deleteAccount() {
    }

    @Action(enabledProperty = "transactionSelected")
    public void editTransaction() {
    }

    @Action
    public void newTransaction() {
        ManageTransactionDialog dialog = ManageTransactionDialog.createNewTransactionDialog(
                getMainFrame(),
                Services.getSelectedPersistenceMan().getAllCategories(),
                Services.getSelectedPersistenceMan().getAllPayees(),
                Services.getSelectedPersistenceMan().getAllAccounts());

        dialog.setVisible(true);
        if (dialog.getStatus() == DialogReturnStatus.OK) {
            log.trace("new Transaction [OK]");
            Services.getSelectedPersistenceMan().persist(dialog.getTransaction());
            setChangesAvailable(true);
        }
    }

    @Action(enabledProperty = "transactionSelected")
    public void deleteTransaction() {
    }

    @Action(enabledProperty = "accountsAvailable")
    public void exportAll() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new BackupFileFilter());

        int retValue = fileChooser.showSaveDialog(getMainFrame());

        if (retValue == JFileChooser.APPROVE_OPTION) {
            String fileName = fileChooser.getSelectedFile().getAbsolutePath();
            if (!fileName.endsWith(".mbck")) {
                fileName = fileName + ".mbck";
            }
            List<Account> allAccounts = Services.getSelectedPersistenceMan().getAllAccounts();
            List<Payee> allPayees = Services.getSelectedPersistenceMan().getAllPayees();
            List<Category> allCategories = Services.getSelectedPersistenceMan().getAllCategories();
            List<Transaction> allTransactions = Services.getSelectedPersistenceMan().getAllTransactions();

            ModelExporter exporter = new ModelExporter(fileName);
            exporter.setAccounts(allAccounts);
            exporter.setCategories(allCategories);
            exporter.setPayees(allPayees);
            exporter.setTransactions(allTransactions);
            try {
                exporter.export();
            } catch (Exception ex) {
                log.info("exception occured while exporting:", ex);
            }
        }
    }

    @Action
    public void importAll() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new BackupFileFilter());

        int retValue = fileChooser.showOpenDialog(getMainFrame());

        if (retValue == JFileChooser.APPROVE_OPTION) {
            Services.getSelectedPersistenceMan().clear();
            try {
                ModelImporter importer = new ModelImporter(fileChooser.getSelectedFile().getAbsolutePath());
                importer.read();
                List<Account> allAccounts = importer.getAllAccounts();
                List<Payee> allPayees = importer.getAllPayees();
                List<Category> allCategories = importer.getAllCategories();
                List<Transaction> allTransactions = importer.getAllTransactions();

                setChangesAvailable(true);

            } catch (Exception ex) {
                log.info("excpetion occurd while importing [" + fileChooser.getSelectedFile().getAbsolutePath() + "]", ex);
            }


        }
    }

    @Action
    public void manageCategories() {
        Vector<Category> categories = new Vector<Category>(Services.getSelectedPersistenceMan().getAllCategories());

        ManageCategoriesDialog dialog = new ManageCategoriesDialog(this.getMainFrame(), categories, true);
        dialog.setVisible(true);
        if (dialog.getStatus() == DialogReturnStatus.OK) {
            log.trace("ManageCategoriessDialog [OK]");
            log.trace("getting categories from Dialog");

            Vector<Category> oldCategories = new Vector<Category>(Services.getSelectedPersistenceMan().getAllCategories());

            if (log.isTraceEnabled()) {
                log.trace("old categories [" + oldCategories + "]");
            }

            Vector<Category> newCategories = dialog.getCategories();
            //delete oldCategories
            for (Category category : oldCategories) {
                if (!newCategories.contains(category)) {

                    List<Transaction> transactions = Services.getSelectedPersistenceMan().getAllTransactionsForCategory(category);
                    for (Transaction transaction : transactions) {
                        transaction.setCategory(null);
                    }

                    Services.getSelectedPersistenceMan().delete(category);
                    setChangesAvailable(true);

                    if (log.isTraceEnabled()) {
                        log.trace("old category [" + category.getName() + "] deleted");
                    }
                }
            }

            for (Category category : newCategories) {
                Services.getSelectedPersistenceMan().persist(category);
                setChangesAvailable(true);
            }

            if (log.isTraceEnabled()) {
                log.trace("new categories [" + newCategories + "]");
            }

        } else if (dialog.getStatus() == DialogReturnStatus.CANCEL) {
            log.trace("ManageCategoriessDialog [CANCEL]");
        } else {
            throw new IllegalStateException("ManageCategoriessDialog not closed with ReturnStatus");
        }
    }

    @Action
    public void managePayees() {
        Vector<Payee> payees = new Vector<Payee>(Services.getSelectedPersistenceMan().getAllPayees());

        ManagePayeesDialog dialog = new ManagePayeesDialog(getMainFrame(), payees, true);
        dialog.setVisible(true);
        if (dialog.getStatus() == DialogReturnStatus.OK) {
            log.debug("ManagePayeesDialog [OK]");
            Vector<Payee> oldPayees = new Vector<Payee>(Services.getSelectedPersistenceMan().getAllPayees());
            for (Payee payee : payees) {
                Services.getSelectedPersistenceMan().persist(payee);
                setChangesAvailable(true);
            }

            for (Payee payee : oldPayees) {
                if (!payees.contains(payee)) {
                    List<Transaction> transactions = Services.getSelectedPersistenceMan().getAllTransactionsForPayee(payee);
                    for (Transaction transaction : transactions) {
                        transaction.setPayee(null);
                    }
                    Services.getSelectedPersistenceMan().delete(payee);
                    setChangesAvailable(true);
                    if (log.isDebugEnabled()) {
                        log.debug("old payee [" + payee.getName() + "] deleted");
                    }
                }
            }

        } else if (dialog.getStatus() == DialogReturnStatus.CANCEL) {
            log.debug("ManagePayeesDialog [CANCEL]");
        } else {
            throw new IllegalStateException("ManagePayeesDialog not closed with ReturnStatus");
        }
    }

    @Action
    public void minimize() {
        getMainFrame().setState(Frame.ICONIFIED);
    }

    @Action
    public void maximize() {
        if (getMainFrame().getExtendedState() == JFrame.MAXIMIZED_BOTH) {
            getMainFrame().setExtendedState(JFrame.NORMAL);
        } else {
            getMainFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
    }

    public void setChangesAvailable(boolean changed) {
        boolean oldValue = this.changesAvailable;
        this.changesAvailable = changed;
        firePropertyChange("changesAvailable", oldValue, this.changesAvailable);
    }

    public boolean isChangesAvailable() {
        return changesAvailable;
    }

    public boolean isAccountSelected() {
        return accountSelected;
    }

    public void setAccountSelected(boolean accountSelected) {
        boolean oldValue = this.accountSelected;
        this.accountSelected = accountSelected;
        firePropertyChange("accountSelected", oldValue, this.accountSelected);
    }

    public boolean isAccountsAvailable() {
        return accountsAvailable;
    }

    public void setAccountsAvailable(boolean accountsAvailable) {
        boolean oldValue = this.accountsAvailable;
        this.accountsAvailable = accountsAvailable;
        firePropertyChange("accountsAvailable", oldValue, this.accountsAvailable);
    }

    public boolean isTransactionSelected() {
        return transactionSelected;
    }

    public void setTransactionSelected(boolean transactionSelected) {
        boolean oldValue = this.transactionSelected;
        this.transactionSelected = transactionSelected;
        firePropertyChange("transactionSelected", oldValue, this.transactionSelected);
    }

    @Override
    public void selectionChanged(ModePanelSelectionContainer container) {
        int year = container.getInt(Constraints.YEAR);
        int month = container.getInt(Constraints.MONTH);

        Account account = container.getAccount(Constraints.ACCOUNT);
        final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        final int currentMonth = Calendar.getInstance().get(Calendar.MONTH);


        if (year == currentYear && month == currentMonth) {
            log.trace("setting panel to current");
            this.accountContainer.remove(this.accountPanel);
            this.accountPanel = new StandardAccountPanel();
            this.accountContainer.add(this.accountPanel, "center, growx, growy");

        } else if (year < currentYear || (year == currentYear && month < currentMonth)) {
            log.trace("setting panel to past");
            this.accountContainer.remove(this.accountPanel);
            this.accountPanel = new PastAccountPanel();
            this.accountContainer.add(this.accountPanel, "center, growx, growy");
        } else {
            log.trace("setting panel to future");
            this.accountContainer.remove(this.accountPanel);
            this.accountPanel = new FutureAccountPanel();
            this.accountContainer.add(this.accountPanel, "center, growx, growy");
        }

        List<Transaction> transactions = getTransactionsForSelectedMonth(account, year, month);

        List<Transaction> incomeTransactions = new ArrayList<Transaction>();
        List<Transaction> expenseTransactions = new ArrayList<Transaction>();

        for (Transaction transaction : transactions) {
            if (account.equals(transaction.getFromAccount())) {
                expenseTransactions.add(transaction);
            }
            if (account.equals(transaction.getToAccount())) {
                incomeTransactions.add(transaction);
            }
        }
        this.accountPanel.setData(account, incomeTransactions, expenseTransactions);
        this.statisticPanel.setData(incomeTransactions, expenseTransactions);

        this.accountContainer.repaint();
    }

    public static void main(final String[] args) {
        launch(Picocash.class, args);
    }
}
