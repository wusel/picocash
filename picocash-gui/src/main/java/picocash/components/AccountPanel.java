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
 * Copyright 2009  Daniel Wasilew
 */
package picocash.components;

import java.awt.Color;
import picocash.renderer.list.TransactionPainterHighlighter;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXList;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.painter.MattePainter;
import org.jdesktop.swingx.painter.PinstripePainter;
import org.jdesktop.swingx.renderer.DefaultListRenderer;
import picocash.components.panel.account.TransactionProvider;
import picocash.model.impl.Account;
import picocash.model.impl.Transaction;

/**
 *
 * @author wusel
 */
public abstract class AccountPanel extends JXPanel {

    private static final Log log = LogFactory.getLog(AccountPanel.class);
    protected final Font mediumLabelFont = new Font(Font.DIALOG, Font.BOLD, 20);
    protected final Font smallLabelFont = new Font(Font.DIALOG, Font.BOLD, 12);
    protected final Font bigLabelFont = new Font(Font.DIALOG, Font.BOLD, 30);
    
    private final Color TRANSPARENT_LIST_BACKGROUND = new Color(0, 0, 0, 0);

    public AccountPanel() {
        init();
        setBackgroundPainter(new MattePainter(Color.WHITE));
    }

    protected abstract void init();

    protected JScrollPane createTransactionScrollPane(ListModel listModel) {
        final JXList transactionList = new JXList(listModel);
        transactionList.setBackground(TRANSPARENT_LIST_BACKGROUND);
        transactionList.setCellRenderer(new DefaultListRenderer(new TransactionProvider()));
        transactionList.setRolloverEnabled(true);
        transactionList.addHighlighter(new TransactionPainterHighlighter(HighlightPredicate.ALWAYS, new MattePainter(Color.WHITE)));
        transactionList.addHighlighter(new TransactionPainterHighlighter(HighlightPredicate.ROLLOVER_ROW, new PinstripePainter()));

        final JScrollPane scrollPane = new JScrollPane(transactionList);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        transactionList.setOpaque(true);
        scrollPane.setOpaque(true);

        return scrollPane;
    }

    protected JXLabel createLabel(String labelText, Font labelFont) {
        JXLabel label = new JXLabel(labelText);
        label.setFont(labelFont);
        label.setOpaque(false);
        return label;
    }

    public abstract void setData(Account account, List<Transaction> incomeTransactions, List<Transaction> expenseTransactions);
}

