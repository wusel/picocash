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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.JPopupMenu;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import picocash.handler.PicocashIcons;

/**
 *
 * @author wusel
 */
public abstract class ModeButton extends JXPanel {

    private static final Log log = LogFactory.getLog(ModeButton.class);
    private JXLabel iconLabel = new JXLabel();
    private JXLabel nameLabel = new JXLabel();
    private JXLabel minimizeLabel = new JXLabel();
    protected final List<ModeButtonSelectionListener> listeners = new ArrayList<ModeButtonSelectionListener>();

    public ModeButton() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill"));
        add(iconLabel);
        add(nameLabel, "push");
        add(minimizeLabel);

        minimizeLabel.setIcon(PicocashIcons.getSystemIcon("minimize-dark"));

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                JPopupMenu popupMenu = new JPopupMenu();
                fillPopupMenu(popupMenu);
                Dimension dimension = getSize();
                popupMenu.setSize(dimension.width, popupMenu.getHeight());
                popupMenu.show((Component) arg0.getSource(), -1, dimension.height + 3);
            }
        });
    }

    public void setText(String name) {
        this.nameLabel.setText(name);
        this.nameLabel.invalidate();

    }

    public void setIcon(Icon icon) {
        this.iconLabel.setIcon(icon);
        this.iconLabel.setText(null);
    }

    public abstract String getLayoutConstraints();

    protected abstract void fillPopupMenu(JPopupMenu popupMenu);

    public void addSelectionListener(ModeButtonSelectionListener listener){
        this.listeners.add(listener);
    }

    public void removeSelectionListener(ModeButtonSelectionListener listener){
        this.listeners.remove(listener);
    }

    protected abstract void selectionChanged();

    public abstract Map getConstraint();
}
