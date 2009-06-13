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

import java.awt.Color;
import java.awt.Graphics2D;
import org.jdesktop.swingx.painter.Painter;

/**
 *
 * @author wusel
 */
public class ModeBackgroundPainter implements Painter {

    @Override
    public void paint(Graphics2D g, Object object, int width, int height) {

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        g.setColor(new Color(150, 150, 150));
        g.drawLine(0, 0, 0, height);
        g.drawLine(width - 1, 0, width - 1, height);
    }
}
