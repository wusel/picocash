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

import picocash.model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author wusel
 */
public class Category implements  NameAndIconable, Comparable<Category> {

    private static final Log log = LogFactory.getLog(Category.class);

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Category) {
            return getId() == ((Category) obj).getId();
        }
        return false;

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (getName() != null ? getName().hashCode() : 0);
        hash = 67 * hash + (getIcon() != null ? getIcon().hashCode() : 0);
        return hash;
    }

    public int compareTo(Category o) {
        return (int) (getId() - o.getId());
    }

    @Override
    public String toString() {
        return getName();
    }
    private String name;
    private String icon;
    private long id;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getIcon() {
        return this.icon;
    }

    @Override
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }
}
