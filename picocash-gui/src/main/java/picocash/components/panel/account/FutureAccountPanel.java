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
import java.awt.Color;
import java.util.List;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.painter.MattePainter;
import picocash.model.impl.Account;
import picocash.model.impl.Transaction;
import picocash.models.list.TransactionListModel;

/**
 *
 * @author wusel
 */
public class FutureAccountPanel extends AccountPanel {

    private JXLabel upcomingLabel;
    private JXLabel bottomSumLabel;
    private JXPanel upcomingPanel;
    private TransactionListModel upcomingTransactionIncomeModel;
    private TransactionListModel upcomingTransactionExpenseModel;
    private JXLabel bottomIncomeLabel;
    private JXLabel bottomExpenseLabel;

    @Override
    protected void init() {
        setBackgroundPainter(new MattePainter(Color.WHITE));

        bottomIncomeLabel = new JXLabel("income");
        bottomExpenseLabel = new JXLabel("expense");

        upcomingLabel = new JXLabel("upcoming");

        bottomExpenseLabel.setFont(smallLabelFont);
        bottomIncomeLabel.setFont(smallLabelFont);

        upcomingLabel.setFont(mediumLabelFont);

        bottomSumLabel = new JXLabel();
        bottomSumLabel.setFont(bigLabelFont);
        bottomSumLabel.setForeground(Color.GRAY);

        upcomingTransactionIncomeModel = new TransactionListModel();
        upcomingTransactionExpenseModel = new TransactionListModel();

        JScrollPane bottomIncomeScrollPane = createTransactionScrollPane(upcomingTransactionIncomeModel);
        JScrollPane bottomExpenseScrollPane = createTransactionScrollPane(upcomingTransactionExpenseModel);

        setLayout(new MigLayout("fill, insets 0 0 0 0"));

        this.upcomingPanel = new JXPanel(new MigLayout("fill", "[50%] [50%]", "[] [] [grow]"));

        this.upcomingPanel.setOpaque(false);

        this.upcomingPanel.add(upcomingLabel, "alignx left,aligny top, wrap");
        this.upcomingPanel.add(bottomIncomeLabel, "alignx left, aligny top");
        this.upcomingPanel.add(bottomExpenseLabel, "alignx right, aligny top, wrap");
        this.upcomingPanel.add(bottomIncomeScrollPane,"growy, growx, aligny top, alignx right");
        
        this.upcomingPanel.add(bottomExpenseScrollPane, "growy, growx, aligny top, alignx right");

        add(upcomingPanel, "aligny top, growx, growy");
    }

    @Override
    public void setData(Account account, List<Transaction> incomeTransactions, List<Transaction> expenseTransactions) {

        final long currentTime = System.currentTimeMillis();

        this.upcomingTransactionExpenseModel.clear();
        this.upcomingTransactionIncomeModel.clear();

        for (Transaction transaction : incomeTransactions) {
            if (transaction.getTransactionDate() <= currentTime) {
                throw new RuntimeException("should not happen");
            } else {
                this.upcomingTransactionIncomeModel.addTransaction(transaction);
            }
        }

        for (Transaction transaction : expenseTransactions) {
            if (transaction.getTransactionDate() <= currentTime) {
                throw new RuntimeException("should not happen");
            } else {
                this.upcomingTransactionExpenseModel.addTransaction(transaction);
            }
        }

        revalidate();
    }
}
