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
package picocash.dialogs;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.image.StackBlurFilter;
import org.jdesktop.swingx.painter.AbstractPainter;
import org.jdesktop.swingx.painter.Painter;
import org.jdesktop.swingx.painter.PinstripePainter;

/**
 *
 * @author wusel
 */
public abstract class DefaultPicocashDialog extends JDialog {

    private static final Log log = LogFactory.getLog(DefaultPicocashDialog.class);

    private DialogReturnStatus status;

    public DefaultPicocashDialog(JFrame parent, boolean modal) {
        super(parent, modal);
    }

    public DefaultPicocashDialog(JDialog parent, boolean modal) {
        super(parent, modal);
    }

    @Action
    public void ok() {
        status = DialogReturnStatus.OK;
        dispose();
    }

    @Action
    public void cancel() {
        status = DialogReturnStatus.CANCEL;
        dispose();
    }

    public DialogReturnStatus getStatus() {
        return status;
    }

    public void setStatus(DialogReturnStatus status) {
        this.status = status;
    }

    protected void initGui() {
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                cancel();
            }

        });
        
        FormLayout layout = new FormLayout("2dlu,p:g,2dlu,30dlu,2dlu,30dlu,2dlu", "2dlu,FILL:p:g,2dlu,p,2dlu,p,2dlu");
        CellConstraints cc = new CellConstraints();

        setLayout(layout);
        add(getComponentsToDisplay(), cc.xyw(2, 2, 5));
        add(new JSeparator(), cc.xyw(2, 4, 5));
        JXButton cancelButton = new JXButton(getAction("cancel"));
        JXButton okButton = new JXButton(getAction("ok"));
        add(cancelButton, cc.xy(4, 6));
        add(okButton, cc.xy(6, 6));
    }

    /**
     *
     * @return the Component wich is displayed in the Dialog
     */
    protected abstract JComponent getComponentsToDisplay();

    protected javax.swing.Action getAction(String action) {
        return Application.getInstance(picocash.Picocash.class).getContext().getActionMap(DefaultPicocashDialog.class, this).get(action);
    }

    protected String getText(String key, Object... obj){
        return Application.getInstance().getContext().getResourceMap(DefaultPicocashDialog.class).getString(key, obj);
    }

}
