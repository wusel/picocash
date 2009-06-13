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

import java.text.AttributedString;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdesktop.swingx.JXPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RectangleInsets;
import picocash.model.impl.Category;
import picocash.model.impl.Transaction;

/**
 *
 * @author wusel
 */
public class ExpenseByCategoryStatistic implements StatisticComponent {

    private static final Log log = LogFactory.getLog(ExpenseByCategoryStatistic.class);
    private JXPanel chartPanel;
    private DefaultPieDataset dataset;
    private String title;
    private Category unknownCategory = new Category();

    public ExpenseByCategoryStatistic() {
        unknownCategory.setName("unknown");
        init();
    }

    @Override
    public JXPanel getComponent() {
        return this.chartPanel;
    }

    @Override
    public void setData(List<Transaction> incomeTransactions, List<Transaction> expenseTransactions) {
        Map<Category, Double> tmp = new HashMap<Category, Double>();

        for (Transaction transaction : expenseTransactions) {
            Category cat = transaction.getCategory();
            if (cat == null) {
                cat = unknownCategory;
            }

            double amount = ((double) transaction.getAmount().getValue()) / 100;

            if (tmp.containsKey(cat)) {
                amount += tmp.get(cat);
            }

            tmp.put(cat, amount);
        }


        this.dataset.clear();
        int index = 0;
        for (Category category : tmp.keySet()) {
            this.dataset.insertValue(index++, category.getName(), tmp.get(category));
        }
    }

    private void init() {
        this.title = "Expense";
        this.dataset = new DefaultPieDataset();
        this.chartPanel = new JXPanel(new MigLayout("fill, insets 0 0 0 0"));
        final JFreeChart piechart = ChartFactory.createPieChart(title, dataset, true, true, Locale.getDefault());
        ((PiePlot)piechart.getPlot()).setLabelGenerator(null);

        piechart.setBorderVisible(false);
        piechart.setAntiAlias(true);
        ChartPanel chart = new ChartPanel(piechart, 300, 300, 200, 200, 400, 400, false, false, false, false, false, true);

        chart.getChart().getPlot().setBackgroundAlpha(0);
        chart.getChart().getPlot().setOutlineVisible(false);

        this.chartPanel.setOpaque(false);
        this.chartPanel.add(chart, "growx, aligny top");
    }
}
