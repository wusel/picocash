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
package picocash.components.panel.statistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import picocash.model.impl.Payee;
import picocash.model.impl.Transaction;

/**
 *
 * @author wusel
 */
public class ExpenseByPayeeStatistic extends AbstractPieStatisticComponent {

    private static final Log log = LogFactory.getLog(ExpenseByCategoryStatistic.class);
    private Payee unknownPayee = new Payee();

    public ExpenseByPayeeStatistic() {
        super();
        unknownPayee.setName("unknown");

    }

    @Override
    public void setData(List<Transaction> incomeTransactions, List<Transaction> expenseTransactions) {
        Map<Payee, Double> tmp = new HashMap<Payee, Double>();
        for (Transaction transaction : expenseTransactions) {
            Payee payee = transaction.getPayee() == null ? unknownPayee : transaction.getPayee();
            double amount = ((double) transaction.getAmount().getValue()) / 100;
            if (tmp.containsKey(payee)) {
                amount += tmp.get(payee);
            }

            tmp.put(payee, amount);
        }


        dataset.clear();
        int index = 0;
        for (Payee payee : tmp.keySet()) {
            this.dataset.insertValue(index++, payee.getName(), tmp.get(payee));
        }
    }

    @Override
    protected String getTitle() {
        return "Payee: Expense";
    }
}
