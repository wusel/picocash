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
 * Copyright 2009 Daniel Wasilew
 */
package picocash.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu.Separator;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdesktop.application.Application;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.painter.MattePainter;
import org.jdesktop.swingx.painter.Painter;
import picocash.Picocash;
import picocash.handler.PicocashIcons;

/**
 *
 * @author wusel
 */
public class HeaderPanel extends JXPanel implements Painter {

    private static final Log log = LogFactory.getLog(HeaderPanel.class);
    private final String PICOCASH = "picocash";
    private final String SETTINGS = "settings";
    private final String ACCOUNT = "account";
    private final String TRANSACTION = "transaction";
    private final Color TRANSPARENT = new Color(0, 0, 0, 0);
    private final GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(167, 167, 167), 0, 40, new Color(123, 123, 123));
    private final Font picocashFont = new Font(Font.DIALOG, Font.BOLD, 28);
    private final Font textFont = new Font(Font.DIALOG, Font.BOLD, 12);
    private final Color borderColor = new Color(144, 144, 144);

    public HeaderPanel() {
        init();

    }

    private JMenu createMenu(String text) {
        JMenu testMenu = new JMenu(text);
        testMenu.setFont(textFont);
        testMenu.setForeground(Color.WHITE);
        return testMenu;
    }

    private Action getAction(String name) {
        return Application.getInstance(Picocash.class).getContext().getActionMap().get(name);
    }

    private void init() {
        setLayout(new MigLayout("fill"));
        setBackgroundPainter(this);


        final JXLabel picocashLabel = createLabel(PICOCASH, picocashFont);

        final JMenu accountLabel = createMenu(ACCOUNT);
        accountLabel.add(getAction("newAccount"));
        accountLabel.add(getAction("editAccount"));
        accountLabel.add(getAction("deleteAccount"));

        final JMenu transactionLabel = createMenu(TRANSACTION);
        transactionLabel.add(getAction("newTransaction"));
        transactionLabel.add(getAction("editTransaction"));
        transactionLabel.add(getAction("deleteTransaction"));

        final JMenu settingsLabel = createMenu(SETTINGS);
        settingsLabel.add(getAction("managePayees"));
        settingsLabel.add(getAction("manageCategories"));
        settingsLabel.add(new Separator());
        settingsLabel.add(getAction("importAll"));
        settingsLabel.add(getAction("exportAll"));

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(0, 0, 0, 0));
        menuBar.add(accountLabel);
        menuBar.add(transactionLabel);
        menuBar.add(settingsLabel);

        final JXButton closeButton = new JXButton(getAction("quit"));
        closeButton.setBackgroundPainter(new MattePainter<JXButton>(TRANSPARENT));
        closeButton.setText(null);
        closeButton.setIcon(PicocashIcons.getSystemIcon("close"));
        closeButton.setFocusable(false);

        final JXButton minimizeButton = new JXButton(getAction("minimize"));
        minimizeButton.setBackgroundPainter(new MattePainter<JXButton>(TRANSPARENT));
        minimizeButton.setIcon(PicocashIcons.getSystemIcon("minimize"));
        minimizeButton.setText(null);
        minimizeButton.setFocusable(false);

        final JXButton maximizeButton = new JXButton(getAction("maximize"));
        maximizeButton.setBackgroundPainter(new MattePainter<JXButton>(TRANSPARENT));
        maximizeButton.setIcon(PicocashIcons.getSystemIcon("maximize"));
        maximizeButton.setText(null);
        maximizeButton.setFocusable(false);

        add(picocashLabel, "dock west, gapleft 10!");
        add(menuBar, "");
        add(minimizeButton, "gapleft push, split 3, w 12!, h 12!, top, gaptop 3!");
        add(maximizeButton, "w 12!, h 12!, top right, gaptop 3!");
        add(closeButton, " w 12!, h 12!, top right, gapright 10!, gaptop 3!");
    }

    private JXLabel createLabel(String text, Font font) {
        final JXLabel picocashLabel = new JXLabel(text);
        picocashLabel.setFont(font);
        picocashLabel.setForeground(Color.WHITE);
        return picocashLabel;
    }

    @Override
    public void paint(Graphics2D g, Object object, int width, int height) {
        g.setPaint(gradientPaint);
        g.fillRect(0, 0, width, height);
        g.setColor(borderColor);
        g.drawRect(0, 0, width - 1, height - 1);
    }
}
