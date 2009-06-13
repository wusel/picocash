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

import java.util.List;
import org.apache.commons.discovery.ResourceClassIterator;
import org.apache.commons.discovery.resource.classes.DiscoverClasses;
import org.apache.commons.discovery.tools.DiscoverSingleton;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author wusel
 */
public class ServiceDiscover {

    private static final Log log = LogFactory.getLog(ServiceDiscover.class);

    public static Object getSingleton(Class<?> clazz) {
        return DiscoverSingleton.find(clazz);
    }

    public static Class<?> getInstance(Class<?> clazz) {
        return null;
    }

    public static List<Object> getAll(Class<?> clazz) {
        DiscoverClasses clss = new DiscoverClasses();

        ResourceClassIterator iter = clss.findResourceClasses(clazz.getName());
        while (iter.hasNext()) {

            Object object = iter.nextResourceClass().loadClass();
            log.debug(object);

        }
        return null;
    }

}
