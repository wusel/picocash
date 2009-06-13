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
 * Copyright 2008 Daniel Wasilew
 */
package picocash.models.table;

import java.util.Comparator;
import picocash.model.impl.Transaction;

/**
 *
 * @author wusel
 */
public class DateSorter implements Comparator<Transaction> {

    @Override
    public int compare(Transaction arg0, Transaction arg1) {
        return (int) (arg0.getTransactionDate() - arg1.getTransactionDate());
    }
}
