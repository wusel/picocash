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

import java.util.HashMap;
import java.util.Map;
import picocash.model.impl.Account;

/**
 *
 * @author wusel
 */
public class ModePanelSelectionContainer {

    private final Map constraints = new HashMap();

    public void addConstraint(Map constraint) {
        this.constraints.putAll(constraint);
    }

    public int getInt(Constraints key) {
        return constraints.get(key) != null ? (Integer) constraints.get(key) : 0;
    }

    public Account getAccount(Constraints key) {
        return constraints.get(key) != null ? (Account) constraints.get(key) : null;
    }
}
