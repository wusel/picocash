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
package picocash.components;

import java.awt.Color;
import java.awt.GradientPaint;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.painter.MattePainter;

/**
 *
 * @author wusel
 */
public class FooterPanel extends JXPanel {

    public FooterPanel() {
        init();
    }

    private void init() {
        setBackgroundPainter(new MattePainter(new GradientPaint(0, 0, new Color(167, 167, 167), 0, 20, new Color(123, 123, 123))));
    }
}
