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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author wusel
 */
public class PlannedTransaction extends Transaction{

    private static final Log log = LogFactory.getLog(PlannedTransaction.class);
    
    private long intervall;

    private long firstOccurence;

    private long lastOccurence;

    /**
     * @return the intervall
     */
    public long getIntervall() {
        return intervall;
    }

    /**
     * @param intervall the intervall to set
     */
    public void setIntervall(long intervall) {
        this.intervall = intervall;
    }

    /**
     * @return the firstOccurence
     */
    public long getFirstOccurence() {
        return firstOccurence;
    }

    /**
     * @param firstOccurence the firstOccurence to set
     */
    public void setFirstOccurence(long firstOccurence) {
        this.firstOccurence = firstOccurence;
    }

    /**
     * @return the lastOccurence
     */
    public long getLastOccurence() {
        return lastOccurence;
    }

    /**
     * @param lastOccurence the lastOccurence to set
     */
    public void setLastOccurence(long lastOccurence) {
        this.lastOccurence = lastOccurence;
    }
}
