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
 * Copyright 2009 Daniel Wasilew
 */
package picocash.components.panel.account;

import picocash.components.AccountPanel;
import java.util.List;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import picocash.model.impl.Account;
import picocash.model.impl.Transaction;
import picocash.models.list.TransactionListModel;

/**
 *
 * @author wusel
 */
public class StandardAccountPanel extends AccountPanel {

    private JXLabel topHeaderLabel;
    private JXLabel topIncomeLabel;
    private JXLabel topExpenseLabel;
    private JXLabel topSumLabel;
    private JXLabel bottomHeaderLabel;
    private JXLabel bottomIncomeLabel;
    private JXLabel bottomExpenseLabel;
    private JXLabel bottomSumLabel;
    private TransactionListModel topIncomeModel;
    private TransactionListModel topExpenseModel;
    private TransactionListModel bottomIncomeModel;
    private TransactionListModel bottomExpenseModel;

    @Override
    protected void init() {
        topHeaderLabel = createLabel("Current", mediumLabelFont);
        topIncomeLabel = createLabel("income", smallLabelFont);
        topExpenseLabel = createLabel("expense", smallLabelFont);
        topSumLabel = createLabel("", bigLabelFont);

        bottomHeaderLabel = createLabel("Upcoming", mediumLabelFont);
        bottomIncomeLabel = createLabel("income", smallLabelFont);
        bottomExpenseLabel = createLabel("expense", smallLabelFont);
        bottomSumLabel = createLabel("", bigLabelFont);

        topIncomeModel = new TransactionListModel();
        topExpenseModel = new TransactionListModel();
        bottomIncomeModel = new TransactionListModel();
        bottomExpenseModel = new TransactionListModel();

        JScrollPane topIncomePanel = createTransactionScrollPane(topIncomeModel);
        JScrollPane topExpensePanel = createTransactionScrollPane(topExpenseModel);
        JScrollPane bottomIncomePanel = createTransactionScrollPane(bottomIncomeModel);
        JScrollPane bottomExpensePanel = createTransactionScrollPane(bottomExpenseModel);

        setLayout(new MigLayout("fill, insets 0 0 0 0", "", "[60%] [40%]"));

        final JXPanel topPanel = new JXPanel(new MigLayout("fill", "[50%] [50%]", "[] [] [grow 100]"));
        final JXPanel bottomPanel = new JXPanel(new MigLayout("fill", "[50%] [50%]", "[] [] [grow 100]"));

        topPanel.setOpaque(false);
        bottomPanel.setOpaque(false);
        
        topPanel.add(topHeaderLabel, "aligny top, wrap");
        topPanel.add(topIncomeLabel, "aligny top");
        topPanel.add(topExpenseLabel, "alignx right, aligny top, wrap");

        topPanel.add(topIncomePanel, "grow, aligny top, alignx left");
        topPanel.add(topExpensePanel, "grow, aligny top, alignx right");

        bottomPanel.add(bottomHeaderLabel, "aligny top, wrap");
        bottomPanel.add(bottomIncomeLabel, "alignx left, aligny top");
        bottomPanel.add(bottomExpenseLabel, "alignx right, aligny top, wrap");
        bottomPanel.add(bottomIncomePanel, "grow, aligny top, alignx left");
        bottomPanel.add(bottomExpensePanel, "grow, aligny top, alignx right");

        add(topPanel, "growx, growy, aligny top, alignx left, wrap");
        add(bottomPanel, "aligny top, alignx right, growx, growy");
    }

    @Override
    public void setData(Account account, List<Transaction> incomeTransactions, List<Transaction> expenseTransactions) {

        final long currentTime = System.currentTimeMillis();

        this.topIncomeModel.clear();
        this.topExpenseModel.clear();
        this.bottomIncomeModel.clear();
        this.bottomExpenseModel.clear();

        for (Transaction transaction : incomeTransactions) {
            if (transaction.getTransactionDate() <= currentTime) {
                this.topIncomeModel.addTransaction(transaction);
            } else {
                this.bottomIncomeModel.addTransaction(transaction);
            }
        }

        for (Transaction transaction : expenseTransactions) {
            if (transaction.getTransactionDate() <= currentTime) {
                this.topExpenseModel.addTransaction(transaction);
            } else {
                this.bottomExpenseModel.addTransaction(transaction);
            }
        }

        revalidate();

    }
}
