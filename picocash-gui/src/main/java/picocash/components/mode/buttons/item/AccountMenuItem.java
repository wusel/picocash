package picocash.components.mode.buttons.item;

import javax.swing.Icon;
import javax.swing.JMenuItem;
import picocash.handler.PicocashIcons;
import picocash.model.impl.Account;

public class AccountMenuItem extends JMenuItem {

    private final Account account;

    public AccountMenuItem(Account account) {
        super();
        this.account = account;
    }

    @Override
    public Icon getIcon() {
        return PicocashIcons.getIcon(this.account.getIcon());
    }

    @Override
    public String getText() {
        return this.account != null ? this.account.getName() : "";
    }

    public Account getAccount() {
        return account;
    }
}
