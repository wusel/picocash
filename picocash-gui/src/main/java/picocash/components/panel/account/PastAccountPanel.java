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

import java.awt.Color;
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
public class PastAccountPanel extends AccountPanel{


    private JXLabel currentLabel;
    private JXLabel sumLabel;
    private JXPanel currentPanel;
    private TransactionListModel transactionIncomeModel;
    private TransactionListModel transactionExpenseModel;
    private JXLabel incomeLabel;
    private JXLabel expenseLabel;

    @Override
    protected void init() {
        incomeLabel = new JXLabel("income");
        expenseLabel = new JXLabel("expense");

        currentLabel = new JXLabel("current");

        expenseLabel.setFont(smallLabelFont);
        incomeLabel.setFont(smallLabelFont);

        currentLabel.setFont(mediumLabelFont);

        sumLabel = new JXLabel();
        sumLabel.setFont(bigLabelFont);
        sumLabel.setForeground(Color.GRAY);

        transactionIncomeModel = new TransactionListModel();
        transactionExpenseModel = new TransactionListModel();

        JScrollPane incomeScrollPane = createTransactionScrollPane(transactionIncomeModel);
        JScrollPane expenseScrollPane = createTransactionScrollPane(transactionExpenseModel);
        setLayout(new MigLayout("fill, insets 0 0 0 0"));
        

        this.currentPanel = new JXPanel(new MigLayout("fill", "[50%] [50%]", "[] [] [grow]"));
        this.currentPanel.setOpaque(false);

        this.currentPanel.add(currentLabel, "alignx left,aligny top, wrap");
        this.currentPanel.add(incomeLabel, "alignx left, aligny top");
        this.currentPanel.add(expenseLabel, "alignx right, aligny top, wrap");
        this.currentPanel.add(incomeScrollPane,"growy, growx, aligny top, alignx right");
        this.currentPanel.add(expenseScrollPane, "growy, growx, aligny top, alignx right");

        add(currentPanel, "aligny top, growx, growy");

    }

    @Override
    public void setData(Account account, List<Transaction> incomeTransactions, List<Transaction> expenseTransactions) {

        final long currentTime = System.currentTimeMillis();

        this.transactionExpenseModel.clear();
        this.transactionIncomeModel.clear();

        for (Transaction transaction : incomeTransactions) {
            if (transaction.getTransactionDate() <= currentTime) {
                this.transactionIncomeModel.addTransaction(transaction);
            } else {
                throw new RuntimeException("should not happen");
            }
        }

        for (Transaction transaction : expenseTransactions) {
            if (transaction.getTransactionDate() <= currentTime) {
                this.transactionExpenseModel.addTransaction(transaction);
            } else {
                throw new RuntimeException("should not happen");
            }
        }

        revalidate();
    }
}
