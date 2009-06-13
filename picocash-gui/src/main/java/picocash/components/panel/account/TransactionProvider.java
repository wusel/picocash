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

import java.util.Calendar;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.painter.MattePainter;
import org.jdesktop.swingx.renderer.CellContext;
import org.jdesktop.swingx.renderer.ComponentProvider;
import picocash.handler.PicocashIcons;
import picocash.model.impl.Transaction;

/**
 *
 * @author wusel
 */
public class TransactionProvider extends ComponentProvider<JXPanel> {

    private static final Log log = LogFactory.getLog(TransactionProvider.class);
    JXPanel panel = new JXPanel();
    private JXPanel transactionPanel;
    private JXLabel cateoryLabel;
    private JXLabel payeeLabel;
    private JXLabel amountLabel;
    private JXLabel dateLabel;
    private boolean showCategoryName = true;
    private boolean showPayeeName = true;

    @Override
    protected void format(CellContext context) {
    }

    @Override
    protected void configureState(CellContext context) {
        Transaction transaction = (Transaction) context.getValue();
        final Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(transaction.getTransactionDate());
        this.dateLabel.setText(calendar.get(Calendar.DAY_OF_MONTH) + "");

        this.amountLabel.setText(transaction.getAmount() + "");
        if (transaction.getCategory() != null) {
            this.cateoryLabel.setIcon(PicocashIcons.getIcon(transaction.getCategory().getIcon()));
            if (showCategoryName) {
                this.cateoryLabel.setText(transaction.getCategory().getName());
            } else {
                this.cateoryLabel.setText(null);
            }
        }
        if (transaction.getPayee() != null) {
            this.payeeLabel.setIcon(PicocashIcons.getIcon(transaction.getPayee().getIcon()));
            if (showPayeeName) {
                this.payeeLabel.setText(transaction.getPayee().getName());
            } else {
                this.payeeLabel.setText(null);
            }
        }

    }

    @Override
    protected JXPanel createRendererComponent() {
        this.transactionPanel = new JXPanel();
        this.transactionPanel.setLayout(new MigLayout("fill"));
//        this.transactionPanel.setOpaque(false);

        this.cateoryLabel = new JXLabel();
//        this.cateoryLabel.setOpaque(false);
        this.payeeLabel = new JXLabel();
//        this.payeeLabel.setOpaque(false);
        this.amountLabel = new JXLabel();
//        this.amountLabel.setOpaque(false);
        this.dateLabel = new JXLabel();
//        this.dateLabel.setOpaque(false);

        this.transactionPanel.add(dateLabel, "alignx left");
        this.transactionPanel.add(payeeLabel, "alignx left");
        this.transactionPanel.add(cateoryLabel, "alignx left, push");
        this.transactionPanel.add(amountLabel, "alignx right");

        return this.transactionPanel;
    }
}
