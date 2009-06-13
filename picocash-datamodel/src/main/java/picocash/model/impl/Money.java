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
package picocash.model.impl;

import java.text.NumberFormat;
import java.text.ParseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Money implements Comparable<Money> {

    private static final Log log = LogFactory.getLog(Money.class);
    private long value;

    public Money(long value) {
        this.value = value;
    }

    public Money() {
        this.value = 0;
    }

    public Money(long value, long decimal) {
        this.value = value * 100 + ((value < 0) ? decimal * -1 : decimal);
    }

    public Money(String string) {
        try {
            this.value = (long) (NumberFormat.getNumberInstance().parse(string).doubleValue() * 100);
        } catch (ParseException ex) {
            log.debug("can't convert to long:", ex);
        }
    }

    public Money add(Money value) {
        return new Money(this.value + value.getValue());
    }

    public Money substract(Money value) {
        return new Money(this.value - value.getValue());
    }

    public Money multiply(long value) {
        return new Money(this.value * value);
    }

    public long getValue() {
        return this.value;
    }

    @Override
    public int compareTo(Money arg0) {
        return (int) (this.value - arg0.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Money other = (Money) obj;
        if (this.value != other.value) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.value ^ (this.value >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        double result = (double) value / 100;
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        return numberFormat.format(result);
    }
}
