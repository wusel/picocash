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

import java.util.List;
import java.util.Locale;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import picocash.model.impl.Transaction;

/**
 *
 * @author wusel
 */
public abstract class AbstractPieStatisticComponent implements StatisticComponent {
    private JXPanel chartPanel;
    protected DefaultPieDataset dataset;
    private String title;
    private boolean wrap;

    public AbstractPieStatisticComponent() {
        init();
    }

    @Override
    public JXPanel getComponent() {
        return this.chartPanel;
    }

    @Override
    public abstract void setData(List<Transaction> incomeTransactions, List<Transaction> expenseTransactions);

    protected abstract String getTitle();
    
    private final void init() {
        this.title = getTitle();
        this.dataset = new DefaultPieDataset();
        this.chartPanel = new JXPanel(new MigLayout("fill, insets 0 0 0 0"));
        final JFreeChart piechart = ChartFactory.createPieChart(title, dataset, true, true, Locale.getDefault());
        ((PiePlot) piechart.getPlot()).setLabelGenerator(null);
        piechart.setBorderVisible(false);
        piechart.setAntiAlias(true);
        ChartPanel chart = new ChartPanel(piechart, 300, 300, 200, 200, 400, 400, false, false, false, false, false, true);
        chart.getChart().getPlot().setBackgroundAlpha(0);
        chart.getChart().getPlot().setOutlineVisible(false);
        this.chartPanel.setOpaque(false);
        this.chartPanel.add(chart, "growx, aligny top");
    }

    @Override
    public boolean isWrapBefore() {
        return wrap;
    }

    @Override
    public void setWrapBefor(boolean wrap) {
        this.wrap = wrap;
    }

    

}
