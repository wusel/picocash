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
package picocash.services;

import picocash.database.connector.IPicocashPersistenceManager;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author wusel
 */
public class Services {

    private static final Log log = LogFactory.getLog(Services.class);

    private static final HashMap<Class, Object> singletonServices = new HashMap<Class, Object>();

    private static IPicocashPersistenceManager selectedPersistenceMan;

    public static void setSingletonService(Class clazz, Object service) {
        singletonServices.put(clazz, service);
    }

    public static Object getSingletonServices(Class<?> clazz) {
        return singletonServices.get(clazz);
    }

    /**
     * @return the selectedPersistenceMan
     */
    public static IPicocashPersistenceManager getSelectedPersistenceMan() {
        return selectedPersistenceMan;
    }

    /**
     * @param aSelectedPersistenceMan the selectedPersistenceMan to set
     */
    public static void setSelectedPersistenceMan(Object aSelectedPersistenceMan) {
        if(aSelectedPersistenceMan instanceof IPicocashPersistenceManager){
            log.info("setSelectededPerManager to ["+aSelectedPersistenceMan.getClass().getName()+"]");
        selectedPersistenceMan = (IPicocashPersistenceManager) aSelectedPersistenceMan;
        }else{
            throw new IllegalArgumentException("Object no instance of IpicocashPersistenceManager");
        }
    }

}
