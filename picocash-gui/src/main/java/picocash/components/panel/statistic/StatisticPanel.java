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
package picocash.components.panel.statistic;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.painter.MattePainter;
import picocash.handler.PicocashIcons;
import picocash.model.impl.Transaction;

/**
 *
 * @author wusel
 */
public class StatisticPanel extends JXPanel {

    private List<StatisticComponent> components = new ArrayList<StatisticComponent>();
    private final MattePainter backgroundPainter = new MattePainter(Color.WHITE);

    public StatisticPanel() {
        addStatisticComponent(new IncomeByCategoryStatistic());
        addStatisticComponent(new ExpenseByCategoryStatistic());
        addStatisticComponent(new IncomeByPayeeStatistic());
        addStatisticComponent(new ExpenseByPayeeStatistic());

        init();
    }

    private void init() {
        setLayout(new MigLayout("fill, debug, insets 0 0 0 0"));

        for (StatisticComponent statisticComponent : components) {
            add(statisticComponent.getComponent(), "growx, aligny top");
        }
        final JXButton addButton = new JXButton(PicocashIcons.getIcon("add"));
        addButton.setBackgroundPainter(backgroundPainter);
        addButton.setBorderPainted(false);
        addButton.setOpaque(false);

        add(addButton, "newline, span, aligny bottom, alignx right");

        setBackgroundPainter(backgroundPainter);
    }

    public void setData(List<Transaction> incomeTransactions, List<Transaction> expenseTransactions) {
        for (StatisticComponent statisticComponent : components) {
            statisticComponent.setData(incomeTransactions, expenseTransactions);
        }
    }

    public void addStatisticComponent(StatisticComponent component) {
        this.components.add(component);

    }
}