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

import picocash.components.mode.buttons.item.MonthMenuItem;
import picocash.components.mode.buttons.item.YearMenuItem;
import picocash.model.impl.Account;

/**
 *
 * @author wusel
 */
public interface ModeButtonSelectionListener {

    public void selectionChanged(Account selectedAccount);

    public void selectionChanged(MonthMenuItem currentItem);

    public void selectionChanged(YearMenuItem currentItem);
}
