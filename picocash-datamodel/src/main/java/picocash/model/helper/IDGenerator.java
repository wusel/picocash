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
package picocash.model.helper;

import java.util.Collection;
import picocash.model.Identifiable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author wusel
 */
public class IDGenerator {

    private static final Log log = LogFactory.getLog(IDGenerator.class);

    public static long generateID() {
        return System.currentTimeMillis() * (long) (Math.random() * 10000);
    }

    public static long createUniqueIDForCollection(Collection base) {
        long erg = generateID();
        for (Object identifiable : base) {
            if (identifiable instanceof Identifiable) {

                if (((Identifiable) identifiable).getId() == erg) {
                    erg = createUniqueIDForCollection(base);
                    break;
                }
            } else {
                throw new IllegalArgumentException("Non identifiable object found in collection [" + identifiable + "]");
            }
        }
        return erg;
    }

}
