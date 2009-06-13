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
package picocash.components.mode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.painter.MattePainter;
import org.jdesktop.swingx.painter.Painter;
import picocash.components.mode.buttons.ModeButton;
import picocash.components.mode.buttons.AccountModeButton;
import picocash.components.mode.buttons.ModeButtonSelectionListener;
import picocash.components.mode.buttons.MonthModeButton;
import picocash.components.mode.buttons.YearModeButton;
import picocash.components.mode.buttons.item.MonthMenuItem;
import picocash.components.mode.buttons.item.YearMenuItem;
import picocash.database.connector.IPicocashPersistenceManager;
import picocash.model.impl.Account;
import picocash.model.impl.Transaction;

/**
 *
 * @author wusel
 */
public class ModePanel extends JXPanel implements Painter, ModeButtonSelectionListener {

    private static final Log log = LogFactory.getLog(ModePanel.class);
    public final static Color PANEL_BACKGROUND_COLOR = new Color(240, 240, 240);
    private final List<ModePanelSelectionListener> modePanelListeners = new ArrayList<ModePanelSelectionListener>();
    private final Color borderColor = new Color(144, 144, 144);
    private JXPanel buttonsPanel = new JXPanel(new MigLayout("insets 0 0 0 0"));
    private List<ModeButton> buttons = new ArrayList<ModeButton>();
    private final MonthModeButton monthModeButton = new MonthModeButton();
    private final YearModeButton yearModeButton = new YearModeButton();
    private final AccountModeButton accountModeButton = new AccountModeButton();
    private IPicocashPersistenceManager persistenceManager;

    public ModePanel() {
        init();
        setBackgroundPainter(this);
    }

    private void addButton(ModeButton modeButton) {
        this.buttons.add(modeButton);
    }

    private void addButtonsToPanel() {
        for (ModeButton modeButton : buttons) {
            modeButton.addSelectionListener(this);
            this.buttonsPanel.add(modeButton, modeButton.getLayoutConstraints());
        }
        this.buttonsPanel.invalidate();
        revalidate();
    }

    private void init() {
        setLayout(new MigLayout("insets 1 1 1 1"));

        buttonsPanel.setBackgroundPainter(new MattePainter<JXButton>(PANEL_BACKGROUND_COLOR));

        addButton(accountModeButton);
        addButton(yearModeButton);
        addButton(monthModeButton);

        add(buttonsPanel, "growx");

        addButtonsToPanel();
    }

    @Override
    public void paint(Graphics2D g, Object object, int width, int height) {
        g.setColor(PANEL_BACKGROUND_COLOR);
        g.fillRect(0, 0, width, height);

        g.setColor(borderColor);
        g.drawRect(0, 0, width - 1, height - 1);
    }

    public void setPersistenceManager(IPicocashPersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
        updateData();
    }

    private void updateData() {
        this.accountModeButton.setAccounts(this.persistenceManager.getAllAccounts());
    }

    public void addModePanelSelectionListener(ModePanelSelectionListener listener) {
        this.modePanelListeners.add(listener);
    }

    public void removeModePanelSelectionListener(ModePanelSelectionListener listener) {
        this.modePanelListeners.remove(listener);
    }

    @Override
    public void selectionChanged(Account selectedAccount) {
        final List<Transaction> allTransactionsForAccount = this.persistenceManager.getAllTransactionsForAccount(selectedAccount);
        if (!allTransactionsForAccount.isEmpty()) {
            long startDate = allTransactionsForAccount.get(0).getTransactionDate();
            long endDate = allTransactionsForAccount.get(allTransactionsForAccount.size() - 1).getTransactionDate();

            this.yearModeButton.setStartAndEnd(startDate, endDate);
        }
        fireModePanelSelectionChanged();
    }

    @Override
    public void selectionChanged(MonthMenuItem currentItem) {
        fireModePanelSelectionChanged();
    }

    @Override
    public void selectionChanged(YearMenuItem currentItem) {
        fireModePanelSelectionChanged();
    }

    private void fireModePanelSelectionChanged() {
        ModePanelSelectionContainer selectionContainer = new ModePanelSelectionContainer();

        for (ModeButton modeButton : buttons) {
            selectionContainer.addConstraint(modeButton.getConstraint());
        }
        
        for (ModePanelSelectionListener modePanelSelectionListener : modePanelListeners) {
            modePanelSelectionListener.selectionChanged(selectionContainer);
        }
    }
}

