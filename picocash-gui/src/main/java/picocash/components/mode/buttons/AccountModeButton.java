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
package picocash.components.mode.buttons;

import java.util.Map;
import picocash.components.mode.buttons.item.AccountMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JPopupMenu;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.painter.MattePainter;
import picocash.components.mode.Constraints;
import picocash.components.mode.ModePanel;
import picocash.handler.PicocashIcons;
import picocash.model.impl.Account;

/**
 *
 * @author wusel
 */
public class AccountModeButton extends ModeButton {

    private static final Log log = LogFactory.getLog(AccountModeButton.class);
    private List<Account> accounts = new ArrayList<Account>();
    private Account selectedAccount;

    public AccountModeButton() {
        setBackgroundPainter(new MattePainter<JXButton>(ModePanel.PANEL_BACKGROUND_COLOR));
        setNameAndIcon();
    }

    @Override
    protected void fillPopupMenu(JPopupMenu popupMenu) {
        if (accounts.size() > 0) {
            for (Account account : accounts) {
                log.debug(account);
                final AccountMenuItem accountMenuItem = new AccountMenuItem(account);

                accountMenuItem.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        setSelectedAccount(accountMenuItem.getAccount());
                    }
                });
                popupMenu.add(accountMenuItem);
            }
        } else {
            popupMenu.add("no accounts available");
        }
    }

    public String getText() {
        return this.selectedAccount != null ? this.selectedAccount.getName() : "account";
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
        if (this.selectedAccount == null && this.accounts.size() > 0) {
            setSelectedAccount(accounts.get(0));
        }
    }

    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
        setNameAndIcon();
        selectionChanged();
    }

    private void setNameAndIcon() {
        setIcon(PicocashIcons.getIcon(this.selectedAccount != null ? this.selectedAccount.getIcon() : null));
        setText(getText());
    }

    @Override
    public String getLayoutConstraints() {
        return "w 200lp:200lp:300lp";
    }

    @Override
    protected void selectionChanged() {
        for (ModeButtonSelectionListener modeButtonSelectionListener : listeners) {
            modeButtonSelectionListener.selectionChanged(selectedAccount);
        }
    }

    @Override
    public Map<Constraints, Object> getConstraint() {
        Map<Constraints, Object> account = new HashMap<Constraints, Object>();
        account.put(Constraints.ACCOUNT, selectedAccount);
        return account;
    }
}
